package ims_mobile_client.presentation.models;

import ims_mobile_client.domain.models.CallState;

public class CallInfo {

    private int id;
    private String sipUriFrom;
    private String sipUriTo;
    private boolean isVideoCall;
    private boolean isConference = false;
    private long beginTime = -1;
    private long endTime = -1;

    // Active call properties
    private int callId = -1;
    private boolean isOnHold = false;
    private boolean voiceMuted = false;
    private boolean videoMuted = false;


    // Call state
    private boolean isActive = false;
    private CallState callState = CallState.UNKNOWN;


    public CallInfo(int id, String sipUriFrom, String sipUriTo, boolean isVideoCall) {
        this.id = id;
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.isVideoCall = isVideoCall;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSipUriFrom() {
        return sipUriFrom;
    }

    public void setSipUriFrom(String sipUriFrom) {
        this.sipUriFrom = sipUriFrom;
    }

    public String getSipUriTo() {
        return sipUriTo;
    }

    public void setSipUriTo(String sipUriTo) {
        this.sipUriTo = sipUriTo;
    }

    public boolean isVideoCall() {
        return isVideoCall;
    }

    public void setVideoCall(boolean videoCall) {
        isVideoCall = videoCall;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isConference() {
        return isConference;
    }

    public void setConference(boolean conference) {
        isConference = conference;
    }

    public boolean isOnHold() {
        return isOnHold;
    }

    public void setOnHold(boolean onHold) {
        isOnHold = onHold;
    }

    public boolean isVoiceMuted() {
        return voiceMuted;
    }

    public void setVoiceMuted(boolean voiceMuted) {
        this.voiceMuted = voiceMuted;
    }

    public boolean isVideoMuted() {
        return videoMuted;
    }

    public void setVideoMuted(boolean videoMuted) {
        this.videoMuted = videoMuted;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public CallState getCallState() {
        return callState;
    }

    public void setCallState(CallState callState) {
        this.callState = callState;
    }
}
