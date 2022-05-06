package ims_mobile_client.localDataSource.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calls_table")
public class LocalCall {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String sipUriFROM;
    private String sipUriTO;
    private boolean isVideo;
    private long beginTime;
    private long endTime;

    public LocalCall(int id, String sipUriFROM, String sipUriTO, boolean isVideo, long beginTime, long endTime) {
        this.id = id;
        this.sipUriFROM = sipUriFROM;
        this.sipUriTO = sipUriTO;
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

    public String getSipUriFROM() {
        return sipUriFROM;
    }

    public void setSipUriFROM(String sipUriFROM) {
        this.sipUriFROM = sipUriFROM;
    }

    public String getSipUriTO() {
        return sipUriTO;
    }

    public void setSipUriTO(String sipUriTO) {
        this.sipUriTO = sipUriTO;
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
