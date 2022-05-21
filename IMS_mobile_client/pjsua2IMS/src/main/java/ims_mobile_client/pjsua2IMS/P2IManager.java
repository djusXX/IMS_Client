package ims_mobile_client.pjsua2IMS;

import android.util.Log;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.MediaConfig;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.UaConfig;
import org.pjsip.pjsua2.pj_qos_type;
import org.pjsip.pjsua2.pjsip_transport_type_e;

import javax.inject.Inject;

import ims_mobile_client.domain.models.User;

public class P2IManager {
    private static final String TAG = P2IManager.class.getSimpleName();

    private Endpoint mgrEndpoint = null;
    private Account mgrAccount = null;
    private boolean isMgrInitialized = false;

    @Inject
    public P2IManager() {}

    public Endpoint getMgrEndpoint() {
        return mgrEndpoint;
    }

    private void checkEndpoint() {
        if (!isMgrInitialized) {
            initManager();
        }
    }

    public void initManager() {
        Log.d(TAG, "Called method createService()");
        loadNativeLibraries();
        configureEndpoint();
        isMgrInitialized = true;
    }

    public void deinitManager() {
        Log.d(TAG, "Called method destroyService()");
        mgrAccount.delete();
        mgrAccount = null;
        destroyEndpoint();
        isMgrInitialized = false;
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

    private void configureEndpoint() {
        mgrEndpoint = new Endpoint();
        try {
            mgrEndpoint.libCreate();
            EpConfig epConfig = new EpConfig();

            UaConfig uaConfig = new UaConfig();
            uaConfig.setUserAgent(TAG + "UA");
            uaConfig.setThreadCnt(2);
            epConfig.setUaConfig(uaConfig);

            MediaConfig mediaConfig = new MediaConfig();
            mediaConfig.setHasIoqueue(true);
            mediaConfig.setQuality(10);
            mediaConfig.setChannelCount(2);
            mediaConfig.setVidPreviewEnableNative(true);
            mediaConfig.setThreadCnt(4);
            epConfig.setMedConfig(mediaConfig);

            mgrEndpoint.libInit(epConfig);

            TransportConfig transportConfig = new TransportConfig();
            transportConfig.setQosType(pj_qos_type.PJ_QOS_TYPE_VOICE);

            mgrEndpoint.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, transportConfig);
            mgrEndpoint.libStart();

        } catch (Exception e) {
            Log.e(TAG, "Method startService() FAILED: ", e);
        }
    }

    private void destroyEndpoint() {
        Runtime.getRuntime().gc();
        try {
            mgrEndpoint.libDestroy();
            mgrEndpoint.delete();
            mgrEndpoint = null;
        } catch (Exception e) {
            Log.e(TAG, "Method stopService() FAILED: ", e);
        }
    }

    public void logIn(User user) {
        checkEndpoint();
        if (mgrAccount != null) {
            mgrAccount.delete();
            mgrAccount = null;
        }

        AccountData accountData = new AccountData();
        accountData.setName(user.getName());
        accountData.setPassword(user.getPassword());
        accountData.setRealm(user.getRealm());
        String pcscf = user.getPcscf();
        String host = pcscf.substring(0,pcscf.indexOf(":"));
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        int port = Integer.parseInt(portStr);
        accountData.setHost(host);
        accountData.setPort(port);

        P2IAccount userAccount = new P2IAccount(accountData);
        try {
            userAccount.create();
            mgrAccount = userAccount;
        } catch (Exception e) {
            Log.e(TAG, "FAILED to create User SIP Account: ", e);
        }
    }
}
