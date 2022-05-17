package ims_mobile_client.ui.models;

public class Call {

    private String remoteUri;
    private String remoteDisplayName;

    public Call(String remoteUri, String remoteDisplayName) {
        this.remoteUri = remoteUri;
        this.remoteDisplayName = remoteDisplayName;
    }

    public String getRemoteUri() {
        return remoteUri;
    }

    public String getRemoteDisplayName() {
        return remoteDisplayName;
    }
}
