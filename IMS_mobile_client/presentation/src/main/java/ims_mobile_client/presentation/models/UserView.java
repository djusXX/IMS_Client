package ims_mobile_client.presentation.models;

public class UserView {

    private int id;
    private String name;
    private String password;
    private String displayName;
    private String realm;
    private String pcscf;
    private long lastLogged;    // UTC time of last logout
    private String regStatus;

    // User Presence
    private String userStatusType;
    private String userStatusActivity;
    private String userStatusText;
    private String note;
    private String rpidId;

    public UserView(int id, String name, String password, String displayName, String realm, String pcscf, long lastLogged, String regStatus, String userStatusType, String userStatusActivity, String userStatusText, String note, String rpidId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.lastLogged = lastLogged;
        this.regStatus = regStatus;
        this.userStatusType = userStatusType;
        this.userStatusActivity = userStatusActivity;
        this.userStatusText = userStatusText;
        this.note = note;
        this.rpidId = rpidId;
    }

    public UserView(int id, String name, String password, String displayName, String realm, String pcscf, long lastLogged) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.lastLogged = lastLogged;
        this.regStatus = null;
        this.userStatusType = null;
        this.userStatusActivity = null;
        this.userStatusText = null;
        this.note = null;
        this.rpidId = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public long getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(long lastLogged) {
        this.lastLogged = lastLogged;
    }

    public String getSipUri() {
        return "sip:" + name + "@" + realm;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getUserStatusType() {
        return userStatusType;
    }

    public void setUserStatusType(String userStatusType) {
        this.userStatusType = userStatusType;
    }

    public String getUserStatusActivity() {
        return userStatusActivity;
    }

    public void setUserStatusActivity(String userStatusActivity) {
        this.userStatusActivity = userStatusActivity;
    }

    public String getUserStatusText() {
        return userStatusText;
    }

    public void setUserStatusText(String userStatusText) {
        this.userStatusText = userStatusText;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRpidId() {
        return rpidId;
    }

    public void setRpidId(String rpidId) {
        this.rpidId = rpidId;
    }

    public String getPcscf() {
        return pcscf;
    }

    public void setPcscf(String pcscf) {
        this.pcscf = pcscf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
