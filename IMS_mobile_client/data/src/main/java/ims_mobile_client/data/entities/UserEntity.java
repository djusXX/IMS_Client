package ims_mobile_client.data.entities;

public class UserEntity {

    private int id;
    private String name;
    private String displayName;
    private String realm;
    private String pcscf;
    private long lastLogged;

    public UserEntity(int id, String displayName, String name, String realm, String pcscf, long lastLogged) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.realm = realm;
        this.pcscf = pcscf;
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

    public String getPcscf() {
        return pcscf;
    }

    public void setPcscf(String pcscf) {
        this.pcscf = pcscf;
    }

    public long getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(long lastLogged) {
        this.lastLogged = lastLogged;
    }
}
