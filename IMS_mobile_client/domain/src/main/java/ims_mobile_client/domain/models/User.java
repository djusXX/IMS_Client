package ims_mobile_client.domain.models;

public class User {

    // Basic info
    private int id;
    private String name;
    private String password;
    private String displayName;
    private String realm;
    private String pcscf;

    private UserLoggedStatus userLoggedStatus = UserLoggedStatus.UNKNOWN;
    private PresenceState presenceState = null;

    public User(String name, String password, String displayName, String realm, String pcscf) {
        this.name = name;
        this.password = password;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
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

    public String getSipUri() {
        return "sip:" + name + "@" + realm;
    }

    public UserLoggedStatus getRegistrationState() { return userLoggedStatus; }

    public void updateRegistrationState(UserLoggedStatus userLoggedStatus) {
        this.userLoggedStatus = userLoggedStatus;
    }

    public PresenceState getPresenceState() {
        return presenceState;
    }

    public void updatePresenceState(String type, String activity, String text, String note) {
        this.presenceState = new PresenceState(type, activity, text, note);
    }
}

