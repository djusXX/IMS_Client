package ims_mobile_client.presentation.models;

public class UserRegistration {
    private long regExpiresSec;
    private int regStatusCode;
    private String regStatusText;

    public UserRegistration() {
        this.setRegExpiresSec(-1);
        this.setRegStatusCode(-1);
        this.setRegStatusText("NOT_SET");
    }

    public UserRegistration(long regExpiresSec, int regStatusCode, String regStatusText) {
        this.setRegExpiresSec(regExpiresSec);
        this.setRegStatusCode(regStatusCode);
        this.setRegStatusText(regStatusText);
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

    public String getRegStatusText() {
        return regStatusText;
    }

    public void setRegStatusText(String regStatusText) {
        this.regStatusText = regStatusText;
    }
}
