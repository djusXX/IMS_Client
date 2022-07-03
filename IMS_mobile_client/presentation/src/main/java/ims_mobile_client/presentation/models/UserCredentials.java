package ims_mobile_client.presentation.models;

public class UserCredentials {
    private String name;
    private String password;
    private String displayName;
    private String realm;
    private String pcscf;

    public UserCredentials(String name, String password, String displayName, String realm, String pcscf) {
        this.name = name;
        this.password = password;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSipUri() {
        return "sip:" + name + "@" + realm;
    }
}
