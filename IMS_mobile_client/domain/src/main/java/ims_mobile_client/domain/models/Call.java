package ims_mobile_client.domain.models;

public class Call {

    private int id;
    private String sipUriFrom;
    private String sipUriTo;
    private boolean isVideo;
    private long beginTime;
    private long endTime;
    private boolean isActive;
    private String stateType;
    private String stateText;
    private int statusCode;
    private String statusText;


    public Call(int id, String sipUriFrom, String sipUriTo, boolean isVideo, long beginTime, long endTime, boolean isActive, String stateType, String stateText, int statusCode, String statusText) {
        this.id = id;
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.isVideo = isVideo;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.stateType = stateType;
        this.stateText = stateText;
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public Call(int id, String sipUriFrom, String sipUriTo, boolean isVideo, long beginTime, long endTime) {
        this.id = id;
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.isVideo = isVideo;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isActive = false;
        this.stateType = null;
        this.stateText = null;
        this.statusCode = -1;
        this.statusText = null;
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

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
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

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
