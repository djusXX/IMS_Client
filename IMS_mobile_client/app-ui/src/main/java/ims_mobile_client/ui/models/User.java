package ims_mobile_client.ui.models;

public class User {

    private String name;
    private String displayName;
    private String realm;
    private String pcscf;

    public User(String name, String displayName, String realm, String pcscf) {
        this.name = name;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRealm() {
        return realm;
    }

    public void setPcscf(String pcscf) {
        this.pcscf = pcscf;
    }
}
