package ims_mobile_client.pjsua2IMS;

import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.DigestCredential;
import org.pjsip.pjsua2.pj_constants_;
import org.pjsip.pjsua2.pj_qos_type;
import org.pjsip.pjsua2.pjsip_cred_data_type;

public class P2IAccountData {

    private String name;
    private String password;
    private String displayName;
    private String realm;
    private String host;
    private long port;
    private boolean tcpTransport = false;
    private String authType = "digest";
    private String contactUriParams = "";
    private int regExpirationTimeout = 600;
    private String guestDisplayName = "";
    private String callId = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public boolean isTcpTransport() {
        return tcpTransport;
    }

    public void setTcpTransport(boolean tcpTransport) {
        this.tcpTransport = tcpTransport;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getContactUriParams() {
        return contactUriParams;
    }

    public void setContactUriParams(String contactUriParams) {
        this.contactUriParams = contactUriParams;
    }

    public int getRegExpirationTimeout() {
        return regExpirationTimeout;
    }

    public void setRegExpirationTimeout(int regExpirationTimeout) {
        this.regExpirationTimeout = regExpirationTimeout;
    }

    public String getGuestDisplayName() {
        return guestDisplayName;
    }

    public void setGuestDisplayName(String guestDisplayName) {
        this.guestDisplayName = guestDisplayName;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    AuthCredInfo getAuthCredInfo() {
        return new AuthCredInfo(authType, realm,
                name, 0, password);
    }

    AuthCredInfo getIMSAuthCredInfo() {
        AuthCredInfo ret = new AuthCredInfo("digest",
                realm,
                name + "@" + realm,
                pjsip_cred_data_type.PJSIP_CRED_DATA_DIGEST
                        | pjsip_cred_data_type.PJSIP_CRED_DATA_EXT_AKA,
                password);

        ret.setAkaK(password);
        return ret;
    }

    DigestCredential getDigestCredInfo() {
        DigestCredential cred = new DigestCredential();
        cred.setUsername(name);
        cred.setUri(getSipUri());
        cred.setRealm(realm);

        return cred;
    }

    public String getSipUri() {
        if ("*".equals(realm))
            return "sip:" + name;

        return "sip:" + name + "@" + realm;
    }

    String getRegistrarUri() {
        return "sip:" + realm;
    }

    String getProxyUri() {
        StringBuilder proxyUri = new StringBuilder();

        proxyUri.append("sip:").append(host).append(":").append(port);

        if (tcpTransport) {
            proxyUri.append(";transport=tcp");
        }

        return proxyUri.toString();
    }

    AccountConfig getAccountConfig() {
        AccountConfig accountConfig = new AccountConfig();
        accountConfig.setIdUri(getSipUri());
        accountConfig.getRegConfig().setRegistrarUri(getRegistrarUri());
        accountConfig.getRegConfig().setTimeoutSec(regExpirationTimeout);


        // account sip stuff configs
        accountConfig.getSipConfig().getAuthCreds().add(getIMSAuthCredInfo());
        accountConfig.getSipConfig().getProxies().add(getProxyUri());
        accountConfig.getSipConfig().setContactUriParams(contactUriParams);
        accountConfig.getSipConfig().setAuthInitialEmpty(true);
        accountConfig.getPresConfig().setPublishEnabled(true);
        accountConfig.getPresConfig().setPublishQueue(true);

        // nat configs to allow call reconnection across networks
        accountConfig.getNatConfig().setSdpNatRewriteUse(pj_constants_.PJ_TRUE);
        accountConfig.getNatConfig().setViaRewriteUse(pj_constants_.PJ_TRUE);

        // account media  stuff configs
        accountConfig.getMediaConfig().getTransportConfig().setQosType(pj_qos_type.PJ_QOS_TYPE_VOICE);
        setVideoConfig(accountConfig);

        return accountConfig;
    }

    AccountConfig getGuestAccountConfig() {
        AccountConfig accountConfig = new AccountConfig();
        accountConfig.getMediaConfig().getTransportConfig().setQosType(pj_qos_type.PJ_QOS_TYPE_VIDEO);
        String idUri = getGuestDisplayName().isEmpty()
                ? getSipUri()
                : "\""+getGuestDisplayName()+"\" <"+getSipUri()+">";
        accountConfig.setIdUri(idUri);
        accountConfig.getSipConfig().getProxies().add(getProxyUri());
        accountConfig.getRegConfig().setRegisterOnAdd(false);
        setVideoConfig(accountConfig);
        return accountConfig;
    }

    private void setVideoConfig(AccountConfig accountConfig) {
        accountConfig.getVideoConfig().setAutoTransmitOutgoing(true);
        accountConfig.getVideoConfig().setAutoShowIncoming(true);
        accountConfig.getVideoConfig().setDefaultCaptureDevice(1); // FRONT Camera
        accountConfig.getVideoConfig().setDefaultRenderDevice(2);  // BACK Camera
    }






























}
