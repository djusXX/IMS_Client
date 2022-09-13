package ims_mobile_client.pjsua2IMS;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserRegistrationStatus;

public interface P2IHelper {
    P2IAccount getCurrentAccount();

    void setCurrentAccount(P2IAccount currentAccount);

    P2ICall getCurrentCall();

    void setCurrentCall(P2ICall currentCall);

    UserRegistrationStatus getLastRegStatus();

    void setLastRegStatus(UserRegistrationStatus lastRegStatus);

    void setRegStatus(int code, long expiration);

    void startSipStack();

    void stopSipStack();

    void restartStack();

    void registerUser(User user);
}
