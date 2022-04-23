package ims_mobile_client.domain.entities;

public class Call {

    private int id;
    private String sipUriFrom;
    private String sipUriTo;
    private boolean isVideo;
    private long beginTime;
    private long endTime;

    public Call(int id, String sipUriFrom, String sipUriTo, boolean isVideo, long beginTime, long endTime) {
        this.id = id;
        this.sipUriFrom = sipUriFrom;
        this.sipUriTo = sipUriTo;
        this.isVideo = isVideo;
        this.beginTime = beginTime;
        this.endTime = endTime;
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
}
