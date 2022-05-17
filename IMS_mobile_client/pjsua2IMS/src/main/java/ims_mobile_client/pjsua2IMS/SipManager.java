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

public class SipManager {
    private static final String TAG = SipManager.class.getSimpleName();

    private Endpoint endpoint = null;
    private Account account;

    @Inject
    public SipManager() {}

    public void createService() {
        Log.d(TAG, "Called method createService()");
        loadNativeLibraries();
        configureEndpoint();
    }

    public void destroyService() {
        Log.d(TAG, "Called method destroyService()");
        destroyAllAccounts();
        destroyEndpoint();
    }

    private void destroyAllAccounts() {

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
        endpoint = new Endpoint();
        try {
            endpoint.libCreate();
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

            endpoint.libInit(epConfig);

            TransportConfig transportConfig = new TransportConfig();
            transportConfig.setQosType(pj_qos_type.PJ_QOS_TYPE_VOICE);

            endpoint.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, transportConfig);
            endpoint.libStart();

        } catch (Exception e) {
            Log.e(TAG, "Method startService() FAILED: ", e);
        }
    }

    private void destroyEndpoint() {

        Runtime.getRuntime().gc();
        try {
            endpoint.libDestroy();
            endpoint.delete();
            endpoint = null;
        } catch (Exception e) {
            Log.e(TAG, "Method stopService() FAILED: ", e);
        }
    }

    public void logIn(User user) {

    }
}
