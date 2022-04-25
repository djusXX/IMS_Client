package ims_mobile_client.localDataSource.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_users_table")
public class LocalUser {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName;
    private String userName;
    private String realm;
    private String pcscf;
    private long lastLogged;
    private String usrSipUri;

    public LocalUser(int id, String displayName, String userName, String realm, String pcscf, long lastLogged) {
        this.id = id;
        this.displayName = displayName;
        this.userName = userName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.lastLogged = lastLogged;
        this.usrSipUri = "sip:" + userName + "@" + realm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUsrSipUri() {
        return usrSipUri;
    }

    public void setUsrSipUri(String usrSipUri) {
        this.usrSipUri = usrSipUri;
    }
}
