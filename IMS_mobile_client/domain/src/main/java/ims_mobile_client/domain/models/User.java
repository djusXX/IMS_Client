package ims_mobile_client.domain.models;

public class User {

    private int id;
    private String name;
    private String displayName;
    private String realm;
    private String pcscf;
    private long lastLogged;    // expiration time of user's registration (in UTC)
    private String regState;

    // User Presence
    private String userStatusType;
    private String userStatusActivity;
    private String userStatusText;
    private String note;
    private String rpidId;

    public User(int id, String name, String displayName, String realm, String pcscf, long lastLogged, String regState, String userStatusType, String userStatusActivity, String userStatusText, String note, String rpidId) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.lastLogged = lastLogged;
        this.regState = regState;
        this.userStatusType = userStatusType;
        this.userStatusActivity = userStatusActivity;
        this.userStatusText = userStatusText;
        this.note = note;
        this.rpidId = rpidId;
    }

    public User(int id, String name, String displayName, String realm, String pcscf, long lastLogged) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.realm = realm;
        this.pcscf = pcscf;
        this.lastLogged = lastLogged;
        this.regState = null;
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

    public String getRegState() {
        return regState;
    }

    public void setRegState(String regState) {
        this.regState = regState;
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

    public static class Info {
        private final int id;
        private final boolean isDefault;
        private final String sipUri;
        private final boolean isRegUriConfigured;
        private final boolean registrationIsActive;
        private final int expirationInterval;
        private final int lastRegCode;
        private final String lastRegText;
        private final int failedRegCause;
        private final boolean presenceStatusIsOnline;
        private final String presenceStatusText;

        public Info(int id, boolean isDefault, String sipUri, boolean isRegUriConfigured, boolean registrationIsActive, int expirationInterval, int lastRegCode, String lastRegText, int failedRegCause, boolean presenceStatusIsOnline, String presenceStatusText) {
            this.id = id;
            this.isDefault = isDefault;
            this.sipUri = sipUri;
            this.isRegUriConfigured = isRegUriConfigured;
            this.registrationIsActive = registrationIsActive;
            this.expirationInterval = expirationInterval;
            this.lastRegCode = lastRegCode;
            this.lastRegText = lastRegText;
            this.failedRegCause = failedRegCause;
            this.presenceStatusIsOnline = presenceStatusIsOnline;
            this.presenceStatusText = presenceStatusText;
        }

        public int getId() {
            return id;
        }

        public boolean isDefault() {
            return isDefault;
        }

        public String getSipUri() {
            return sipUri;
        }

        public boolean isRegUriConfigured() {
            return isRegUriConfigured;
        }

        public boolean isRegistrationIsActive() {
            return registrationIsActive;
        }

        public int getExpirationInterval() {
            return expirationInterval;
        }

        public int getLastRegCode() {
            return lastRegCode;
        }

        public String getLastRegText() {
            return lastRegText;
        }

        public int getFailedRegCause() {
            return failedRegCause;
        }

        public boolean isPresenceStatusIsOnline() {
            return presenceStatusIsOnline;
        }

        public String getPresenceStatusText() {
            return presenceStatusText;
        }
    }
}
