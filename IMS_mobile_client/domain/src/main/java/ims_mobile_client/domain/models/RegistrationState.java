package ims_mobile_client.domain.models;

public class RegistrationState {
    public final long regExpiresSec;
    public final int regStatusCode;
    public final String regStatusText;

    public RegistrationState(long regExpiresSec, int regStatusCode, String regStatusText) {
        this.regExpiresSec = regExpiresSec;
        this.regStatusCode = regStatusCode;
        this.regStatusText = regStatusText;
    }
}
