package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.AccountInfo;
import org.pjsip.pjsua2.AudDevManager;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.MediaConfig;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.UaConfig;
import org.pjsip.pjsua2.VidDevManager;
import org.pjsip.pjsua2.pj_qos_type;
import org.pjsip.pjsua2.pjsip_transport_type_e;

import javax.inject.Inject;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserRegistrationStatus;

/**
 * Class for providing pjsua2 methods amd current library state.
 * */
public class P2IHelperImpl implements P2IHelper {
    private static final String TAG = P2IHelperImpl.class.getSimpleName();

    private Endpoint endpoint;
    private volatile boolean started = false;

    private P2IAccount currentAccount = null;
    private P2ICall currentCall = null;
    private UserRegistrationStatus lastRegStatus = UserRegistrationStatus.UNKNOWN;

    @Inject
    public P2IHelperImpl() {
        loadNativeLibraries();
        startSipStack();
    }

    @Override
    public P2IAccount getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public void setCurrentAccount(P2IAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    @Override
    public P2ICall getCurrentCall() {
        return currentCall;
    }

    @Override
    public void setCurrentCall(P2ICall currentCall) {
        this.currentCall = currentCall;
    }

    @Override
    public UserRegistrationStatus getLastRegStatus() {
        return lastRegStatus;
    }

    @Override
    public void setLastRegStatus(UserRegistrationStatus lastRegStatus) {
        this.lastRegStatus = lastRegStatus;
    }

    @Override
    public void setRegStatus(int code, long expiration) {
        if (expiration == 0) {
            lastRegStatus = UserRegistrationStatus.UNREGISTERED;
            return;
        }
        if (code / 100 == 2) {
            lastRegStatus = UserRegistrationStatus.REGISTERED;
            return;
        }
        lastRegStatus = UserRegistrationStatus.UNKNOWN;
    }


    @Override
    protected void finalize() throws Throwable {
        stopSipStack();
        super.finalize();
    }

    @Override
    public void startSipStack() {
        if (started) {
            Log.d(TAG, "SIP stack already started, SKIPPING");
            return;
        }

        try {
            endpoint = new P2IEndpoint();
            endpoint.libCreate();
            EpConfig epConfig = new EpConfig();

            UaConfig uaConfig = new UaConfig();
            uaConfig.setUserAgent("Pjsua2IMS_UA");
            uaConfig.setThreadCnt(2);
            epConfig.setUaConfig(uaConfig);

            MediaConfig mediaConfig = new MediaConfig();
            mediaConfig.setHasIoqueue(true);
            mediaConfig.setQuality(10);
            mediaConfig.setChannelCount(2);
            mediaConfig.setVidPreviewEnableNative(true);
            mediaConfig.setThreadCnt(4);
            epConfig.setMedConfig(mediaConfig);

            endpoint.libInit(epConfig);

            TransportConfig transportConfig = new TransportConfig();
            transportConfig.setQosType(pj_qos_type.PJ_QOS_TYPE_VOICE);

            endpoint.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, transportConfig);
            endpoint.libStart();
            started = true;
        } catch (Exception e) {
            Log.e(TAG, "Starting SIP stack FAILED: ", e);
            started = false;
        }
//        registerUser(new User("alice", "alice", "ALICE", "open-ims.test", "10.0.0.9:4060"));
        Log.d(TAG, "SIP stack STARTED");
    }

    @Override
    public void stopSipStack() {
        if (!started) return;

        try {
            endpoint.libDestroy();
            endpoint.delete();
            endpoint = null;
            started = false;
        } catch (Exception e) {
            Log.e(TAG, "Stopping SIP stack FAILED: ", e);
        }
    }

    private void loadNativeLibraries() {
        try {
            System.loadLibrary("c++_shared");
            Log.d(TAG, "libc++_shared loaded");
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to load libc++_shared.so", e);
        }

        try {
            System.loadLibrary("openh264");
            Log.d(TAG, "libopenh264 loaded");
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to load libopenh264.so", e);
        }

        try {
            System.loadLibrary("pjsua2");
            Log.d(TAG, "libpjsua2 loaded");
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to load libpjsua2.so", e);
        }
    }

    @Override
    public void restartStack() {
        stopSipStack();
        startSipStack();
    }

    protected synchronized AudDevManager getAudDevManager() {
        return endpoint.audDevManager();
    }

    protected synchronized VidDevManager getVidDevManager() {
        return endpoint.vidDevManager();
    }

    @Override
    public void registerUser(User user) {
        Log.d(TAG, "Inside method registerUser");
        startSipStack();

        if (currentAccount != null && user.getSipUri().equals(currentAccount.getAccountData().getSipUri())) {
            Log.e(TAG, "Current account registered, skipping");
            return;
//            currentAccount.delete();
//            currentAccount = null;
        }

        try {
            if (currentAccount == null) {
                P2IAccountData accountData = new P2IAccountData();
                accountData.setName(user.getName());
                accountData.setPassword(user.getPassword());
                accountData.setRealm(user.getRealm());
                String pcscf = user.getPcscf();
                String host = pcscf.substring(0,pcscf.indexOf(":"));
                String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
                int port = Integer.parseInt(portStr);
                accountData.setHost(host);
                accountData.setPort(port);

                P2IAccount userAccount = new P2IAccount(accountData, this);
                Log.d(TAG, "Trying to register new account");
                userAccount.create();
                currentAccount = userAccount;
            }

            AccountInfo accountInfo = currentAccount.getInfo();
            setRegStatus(accountInfo.getRegStatus(), accountInfo.getRegExpiresSec());
        } catch (Exception e) {
            Log.e(TAG, "FAILED to create User SIP Account: ", e);
        }
        Log.d(TAG, "Registered user successfully.");
    }


//
//    public void logIn(User user) {
//        checkEndpoint();
//        if (mgrAccount != null) {
//            mgrAccount.delete();
//            mgrAccount = null;
//        }
//
//        P2IAccountData accountData = new P2IAccountData();
//        accountData.setName(user.getName());
//        accountData.setPassword(user.getPassword());
//        accountData.setRealm(user.getRealm());
//        String pcscf = user.getPcscf();
//        String host = pcscf.substring(0,pcscf.indexOf(":"));
//        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
//        int port = Integer.parseInt(portStr);
//        accountData.setHost(host);
//        accountData.setPort(port);
//
//        P2IAccount userAccount = new P2IAccount(accountData);
//        try {
//            userAccount.create();
//            mgrAccount = userAccount;
//        } catch (Exception e) {
//            Log.e(TAG, "FAILED to create User SIP Account: ", e);
//        }
//    }
}
