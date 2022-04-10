package com.example.presentation.models;

import com.example.domain.entities.UserEntity;

public class LocalUser {

    public int id;
    public String displayName;
    public String userName;
    public String realm;
    public String pcscf;

    public LocalUser(String displayName, String userName, String realm, String pcscf) {
        this.displayName = displayName;
        this.userName = userName;
        this.realm = realm;
        this.pcscf = pcscf;
    }

    public LocalUser(UserEntity userEntity) {
        this.id = userEntity.id;
        this.displayName = userEntity.displayName;
        this.userName = userEntity.userName;
        this.realm = userEntity.realm;
        this.pcscf = userEntity.pcscf;
    }

    public UserEntity toUserEntity() {
        UserEntity ret = new UserEntity();
        ret.id = id;
        ret.displayName = displayName;
        ret.userName = userName;
        ret.realm = realm;
        ret.pcscf = pcscf;
        ret.usrSipUri = getSipUri();
        ret.lastLogged = true;
        return ret;
    }

    public String getSipUri() {
        return "sip:" + userName + "@" + realm;
    }

    public String pcscfGetHost() { return pcscf.substring(0,pcscf.indexOf(":")); }

    public int pcscfGetPort() {
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        int port = Integer.parseInt(portStr);
        return port;
    }
}
