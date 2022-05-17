package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AudioMedia;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.CallMediaInfoVector;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.OnCallMediaStateParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.VideoPreview;
import org.pjsip.pjsua2.VideoWindow;
import org.pjsip.pjsua2.pjmedia_type;
import org.pjsip.pjsua2.pjsua2;
import org.pjsip.pjsua2.pjsua_call_media_status;

public class P2ICall extends Call {
    private static final String TAG = P2ICall.class.getSimpleName();

    private VideoWindow videoWindow;
    private VideoPreview videoPreview;
    private final Endpoint endpointRef;

    public P2ICall(Account acc, int call_id, Endpoint endpointRef) {
        super(acc, call_id);
        this.endpointRef = endpointRef;
        videoWindow = null;
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
        // TODO: notify
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
                    endpointRef.audDevManager().getCaptureDevMedia().startTransmit(audioMedia);
                    audioMedia.startTransmit(endpointRef.audDevManager().getPlaybackDevMedia());
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

        // TODO: notify
    }
}
