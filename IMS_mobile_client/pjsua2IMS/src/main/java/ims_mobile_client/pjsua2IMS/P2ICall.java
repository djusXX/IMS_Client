package ims_mobile_client.pjsua2IMS;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.util.Log;
import android.view.Surface;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AudDevManager;
import org.pjsip.pjsua2.AudioMedia;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.CallMediaInfoVector;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.CallSetting;
import org.pjsip.pjsua2.CallVidSetStreamParam;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.Media;
import org.pjsip.pjsua2.OnCallMediaStateParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.VideoPreview;
import org.pjsip.pjsua2.VideoPreviewOpParam;
import org.pjsip.pjsua2.VideoWindow;
import org.pjsip.pjsua2.VideoWindowHandle;
import org.pjsip.pjsua2.pjmedia_type;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_role_e;
import org.pjsip.pjsua2.pjsip_status_code;
import org.pjsip.pjsua2.pjsua2;
import org.pjsip.pjsua2.pjsua_call_flag;
import org.pjsip.pjsua2.pjsua_call_media_status;
import org.pjsip.pjsua2.pjsua_call_vid_strm_op;

public class P2ICall extends Call {
    private static final String TAG = P2ICall.class.getSimpleName();

    private final P2IAccount account;
    private boolean localHold = false;
    private boolean localMute = false;
    private boolean localVideoMute = false;
    private long connectTimestamp = 0;
    private ToneGenerator toneGenerator;
    private boolean videoCall = false;
    private boolean frontCamera = true;

    private VideoWindow videoWindow;
    private VideoPreview videoPreview;

    /**
     * Incoming Call
     * @param account
     * @param call_id
     */
    public P2ICall(P2IAccount account, int call_id) {
        super(account, call_id);
        this.account = account;
        videoWindow = null;
        videoPreview = null;
    }

    /**
     * Outgoing Call
     * @param account
     */
    public P2ICall(P2IAccount account) {
        super(account);
        this.account = account;
    }

    public CallInfo getUpdatedCallInfo() {
        CallInfo callInfo = null;
        try {
            callInfo = this.getInfo();
        } catch (Exception e) {
            Log.e(TAG, "Call.getInfo() FAILED: " + e);
        }
        return callInfo;
    }

    @Override
    public void onCallState(OnCallStateParam prm) {
        try {
            CallInfo callInfo = getInfo();
            int callId = callInfo.getId();
            int callState = callInfo.getState();

            if (callState == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                checkAndStopLocalRingBackTone();
                stopIncomingVideoFeed();
                stopPreviewVideoFeed();
                account.removeCall(callId);
            }
            else if (callState == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                checkAndStopLocalRingBackTone();
                connectTimestamp = System.currentTimeMillis();
                if (videoCall) setVideoMute(false);
            }
            else if (callState == pjsip_inv_state.PJSIP_INV_STATE_EARLY) {
                int statusCode = callInfo.getLastStatusCode();
                if (statusCode == pjsip_status_code.PJSIP_SC_RINGING && callInfo.getRole() == pjsip_role_e.PJSIP_ROLE_UAC) {
                    checkAndStopLocalRingBackTone();
                    toneGenerator = new ToneGenerator(AudioManager.STREAM_VOICE_CALL, 100);
                    toneGenerator.startTone(ToneGenerator.TONE_SUP_RINGTONE);
                }
                else if (statusCode == pjsip_status_code.PJSIP_SC_PROGRESS) {
                    checkAndStopLocalRingBackTone();
                }
            }

            if (callState == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) { delete(); }

        } catch (Exception e) {
            Log.e(TAG, "Call.onCallState() FAILED: " + e);
        }
    }

    private void setVideoMute(boolean videoMute) {
        try {
            vidSetStream(videoMute
                            ? pjsua_call_vid_strm_op.PJSUA_CALL_VID_STRM_STOP_TRANSMIT
                            : pjsua_call_vid_strm_op.PJSUA_CALL_VID_STRM_START_TRANSMIT,
                            new CallVidSetStreamParam());

            localVideoMute = videoMute;
        } catch (Exception e) {
            Log.e(TAG, "setVideoMute() FAILED: " + e);
        }
    }

    public void startPreviewVideoFeed(Surface surface) {
        if (videoPreview == null) return;

        VideoWindowHandle videoWindowHandle = new VideoWindowHandle();
        videoWindowHandle.getHandle().setWindow(surface);
        VideoPreviewOpParam videoPreviewOpParam = new VideoPreviewOpParam();
        videoPreviewOpParam.setWindow(videoWindowHandle);
        try {
            videoPreview.start(videoPreviewOpParam);
        } catch (Exception ex) {
            Log.e(TAG, "videoPreview.start() FAILED: ", ex);
        }
    }

    public void stopPreviewVideoFeed() {
        VideoPreview videoPreview = getVideoPreview();
        if (videoPreview == null) return;
        try {
            videoPreview.stop();
        } catch (Exception e) {
            Log.e(TAG, "videoPreview.stop() FAILED: " + e);
        }
    }

    public VideoPreview getVideoPreview() {
        return videoPreview;
    }

    public void setVideoPreview(VideoPreview videoPreview) {
        this.videoPreview = videoPreview;
    }

    public void setIncomingVideoFeed(Surface surface) {
        if (videoWindow == null) return;

        VideoWindowHandle videoWindowHandle = new VideoWindowHandle();
        videoWindowHandle.getHandle().setWindow(surface);
        try {
            videoWindow.setWindow(videoWindowHandle);
            setVideoMute(localVideoMute);
        } catch (Exception ex) {
            Log.e(TAG, "setIncomingVideoFeed() FAILED", ex);
        }
    }

    public void stopIncomingVideoFeed() {
        VideoWindow videoWindow = getVideoWindow();
        if (videoWindow == null) return;
        videoWindow.delete();
    }

    public VideoWindow getVideoWindow() {
        return videoWindow;
    }

    public void setVideoWindow(VideoWindow videoWindow) {
        this.videoWindow = videoWindow;
    }

    public void setVideoMedia(VideoWindow videoWindow) {
        this.videoWindow = videoWindow;
    }


    private void checkAndStopLocalRingBackTone() {
        if (toneGenerator == null) return;

        toneGenerator.stopTone();
        toneGenerator.release();
        toneGenerator = null;
    }

    @Override
    public void onCallMediaState(OnCallMediaStateParam prm) {
        CallInfo callInfo = getUpdatedCallInfo();
        if (callInfo == null) {
            return;
        }

        CallMediaInfoVector callMediaInfos = callInfo.getMedia();
        int idx = 0;
        for(CallMediaInfo callMediaInfo : callMediaInfos) {
            int type = callMediaInfo.getType();
            int status = callMediaInfo.getStatus();
            int incomingVideoWindowId = callMediaInfo.getVideoIncomingWindowId();

            if (type == pjmedia_type.PJMEDIA_TYPE_AUDIO
                    && (status == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE
                        || status == pjsua_call_media_status.PJSUA_CALL_MEDIA_LOCAL_HOLD)) {
                try {
                    AudioMedia audioMedia = getAudioMedia(idx);
                    if (audioMedia != null) {
                        audioMedia.adjustRxLevel((float) 1.5);
                        audioMedia.adjustTxLevel((float) 1.5);
                        account.getHelper().getAudDevManager().getCaptureDevMedia().startTransmit(audioMedia);
                        audioMedia.startTransmit(account.getHelper().getAudDevManager().getPlaybackDevMedia());
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Connecting media ports FAILED: " + e);
                }
            }
            else if (type == pjmedia_type.PJMEDIA_TYPE_VIDEO
                    && status == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE
                    && incomingVideoWindowId != pjsua2.INVALID_ID) {
                videoWindow = new VideoWindow(incomingVideoWindowId);
                videoPreview = new VideoPreview(callMediaInfo.getVideoCapDev());
            }
            idx++;
        }
    }

    private void handleVideoMedia(CallMediaInfo mediaInfo) {
        if (videoWindow != null) {
            videoWindow.delete();
        }
        if (videoPreview != null) {
            videoPreview.delete();
        }
        videoWindow = new VideoWindow(mediaInfo.getVideoIncomingWindowId());
    }

    public boolean isVideoCall() { return videoCall; }

    public boolean isLocalVideoMute() {
        return localVideoMute;
    }

    public boolean isFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(boolean frontCamera) {
        this.frontCamera = frontCamera;
    }

    private void setMediaParams(CallOpParam param) {
        CallSetting callSetting = param.getOpt();
        callSetting.setAudioCount(1);
        callSetting.setVideoCount(videoCall ? 1 : 0);
    }

    public long getConnectTimestamp() {
        return connectTimestamp;
    }

    public void acceptIncomingCall() {
        CallOpParam param = new CallOpParam();
        param.setStatusCode(pjsip_status_code.PJSIP_SC_OK);
        setMediaParams(param);
        if (!videoCall) {
            CallSetting callSetting = param.getOpt();
            callSetting.setFlag(pjsua_call_flag.PJSUA_CALL_INCLUDE_DISABLED_MEDIA);
        }
        try {
            answer(param);
        } catch (Exception e) {
            Log.e(TAG, "Failed to answer incoming call", e);
        }
    }

    public void sendBusyHereToIncomingCall() {
        CallOpParam param = new CallOpParam();
        param.setStatusCode(pjsip_status_code.PJSIP_SC_BUSY_HERE);

        try {
            answer(param);
        } catch (Exception e) {
            Log.e(TAG, "Failed to send busy here", e);
        }
    }

    public void declineIncomingCall() {
        CallOpParam param = new CallOpParam();
        param.setStatusCode(pjsip_status_code.PJSIP_SC_DECLINE);

        try {
            answer(param);
        } catch (Exception e) {
            Log.e(TAG, "Failed to decline incoming call", e);
        }
    }

    public void hangUp() {
        CallOpParam param = new CallOpParam();
        param.setStatusCode(pjsip_status_code.PJSIP_SC_DECLINE);

        try {
            hangup(param);
        } catch (Exception e) {
            Log.e(TAG, "Failed to hangUp call", e);
        }
    }

    public void setMute(boolean mute) {
        if (localMute == mute) return;

        CallInfo callInfo;
        try {
            callInfo = getInfo();
        } catch (Exception e) {
            Log.e(TAG, "setMute.getInfo() FAILED", e);
            return;
        }

        CallMediaInfoVector callMediaInfos = callInfo.getMedia();
        int idx = 0;
        for(CallMediaInfo callMediaInfo : callMediaInfos) {
            int type = callMediaInfo.getType();
            int status = callMediaInfo.getStatus();

            if (type == pjmedia_type.PJMEDIA_TYPE_AUDIO
                    && status == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE) {
                try {
                    AudioMedia audioMedia = getAudioMedia(idx);
                    if (audioMedia != null) {
                        if (mute)
                            account.getHelper().getAudDevManager().getCaptureDevMedia().stopTransmit(audioMedia);
                        else
                            account.getHelper().getAudDevManager().getCaptureDevMedia().startTransmit(audioMedia);

                        localMute = mute;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "setting local MUTE FAILED: " + e);
                }
            }
            idx++;
        }
    }

    public boolean isLocalMute() {
        return localMute;
    }

    public void toggleMute() {
        setMute(!localMute);
    }

    public void setHold(boolean hold) {
        if (localHold == hold) return;

        CallOpParam param = new CallOpParam();
        try {
            if (hold) {
                Log.d(TAG, "holding call");
                setHold(param);
            } else {
                Log.d(TAG, "un-holding call ");
                setMediaParams(param);
                CallSetting opt = param.getOpt();
                opt.setFlag(pjsua_call_flag.PJSUA_CALL_UNHOLD);
                reinvite(param);
            }
            localHold = hold;
        } catch (Exception e) {
            String operation = hold ? "hold" : "unhold";
            Log.e(TAG, "Error occurred. FAILED to " + operation + " call", e);
        }
    }

    public void toggleHold() {
        setHold(!localHold);
    }

    public boolean isLocalHold() {
        return localHold;
    }

    @Override
    public void makeCall(String remoteSipUri, CallOpParam prm) throws java.lang.Exception {
        setMediaParams(prm);
        if (!videoCall) {
            CallSetting callSetting = prm.getOpt();
            callSetting.setFlag(pjsua_call_flag.PJSUA_CALL_INCLUDE_DISABLED_MEDIA);
        }
        super.makeCall(remoteSipUri, prm);
    }

    private void handleAudioMedia(Media media) {
        AudioMedia audioMedia = AudioMedia.typecastFromMedia(media);
        if (audioMedia == null) return;
        try {
            AudDevManager audDevManager = account.getHelper().getAudDevManager();
            try {
                audioMedia.adjustRxLevel((float) 1.5);
                audioMedia.adjustTxLevel((float) 1.5);
            } catch (Exception e) {
                Log.e(TAG, "Error adjusting levels in AudioMedia", e);
            }

            audioMedia.startTransmit(audDevManager.getPlaybackDevMedia());
            audDevManager.getCaptureDevMedia().startTransmit(audioMedia);
        } catch (Exception e) {
            Log.e(TAG, "Error while connecting audio media to sound device", e);
        }
    }
    
    
    
    
    
    
    
}
