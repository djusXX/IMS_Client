package ims_mobile_client.domain.entities;

public class User {

    private int id;
    private String name;
    private String displayName;
    private String realm;
    private long lastLogged;    // UTC time of last logout

    public User(int id, String name, String displayName, String realm, long lastLogged) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.realm = realm;
        this.lastLogged = lastLogged;
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
}
