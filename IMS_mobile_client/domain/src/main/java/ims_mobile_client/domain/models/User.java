package ims_mobile_client.domain.models;

public class User {

    // Basic info
    private int id;
    private String name;
    private String password;
    private String displayName;
    private String realm;
    private String pcscf;

    // registrar info
    private long regExpiresSec;
    private int regStatusCode;
    private String regStatusText;

    // Presence info
    private String presenceStatusType;
    private String presenceStatusActivity;
    private String presenceStatusText;
    private String presenceNote;
    private String presenceRpidId;

    public User(int id, String name, String password, String displayName, String realm, String pcscf, long regExpiresSec, int regStatusCode, String regStatusText, String presenceStatusType, String presenceStatusActivity, String presenceStatusText, String note, String presenceRpidId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.regExpiresSec = regExpiresSec;
        this.regStatusCode = regStatusCode;
        this.regStatusText = regStatusText;
        this.presenceStatusType = presenceStatusType;
        this.presenceStatusActivity = presenceStatusActivity;
        this.presenceStatusText = presenceStatusText;
        this.presenceNote = note;
        this.presenceRpidId = presenceRpidId;
    }

    public User(int id, String name, String password, String displayName, String realm, String pcscf, long regExpiresSec, int regStatusCode) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.regExpiresSec = regExpiresSec;
        this.regStatusCode = regStatusCode;
        this.regStatusText = null;
        this.presenceStatusType = null;
        this.presenceStatusActivity = null;
        this.presenceStatusText = null;
        this.presenceNote = null;
        this.presenceRpidId = null;
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

    public long getRegExpiresSec() {
        return regExpiresSec;
    }

    public void setRegExpiresSec(long regExpiresSec) {
        this.regExpiresSec = regExpiresSec;
    }

    public int getRegStatusCode() {
        return regStatusCode;
    }

    public void setRegStatusCode(int regStatusCode) {
        this.regStatusCode = regStatusCode;
    }

    public String getSipUri() {
        return "sip:" + name + "@" + realm;
    }

    public String getRegStatusText() {
        return regStatusText;
    }

    public void setRegStatusText(String regStatusText) {
        this.regStatusText = regStatusText;
    }

    public String getPresenceStatusType() {
        return presenceStatusType;
    }

    public void setPresenceStatusType(String presenceStatusType) {
        this.presenceStatusType = presenceStatusType;
    }

    public String getPresenceStatusActivity() {
        return presenceStatusActivity;
    }

    public void setPresenceStatusActivity(String presenceStatusActivity) {
        this.presenceStatusActivity = presenceStatusActivity;
    }

    public String getPresenceStatusText() {
        return presenceStatusText;
    }

    public void setPresenceStatusText(String presenceStatusText) {
        this.presenceStatusText = presenceStatusText;
    }

    public String getPresenceNote() {
        return presenceNote;
    }

    public void setPresenceNote(String presenceNote) {
        this.presenceNote = presenceNote;
    }

    public String getPresenceRpidId() {
        return presenceRpidId;
    }

    public void setPresenceRpidId(String presenceRpidId) {
        this.presenceRpidId = presenceRpidId;
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
