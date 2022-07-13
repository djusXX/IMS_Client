package ims_mobile_client.presentation.models;

import ims_mobile_client.presentation.utils.StatusType;

public class PresenceStatus {
    private StatusType presenceStatusType;
    private String presenceStatusText;

    public PresenceStatus() {
        presenceStatusType = StatusType.UNKNOWN;
        presenceStatusText = "";
    }

    public PresenceStatus(StatusType presenceStatusType, String presenceStatusText) {
        this.setPresenceStatusType(presenceStatusType);
        this.setPresenceStatusText(presenceStatusText);
    }

    public StatusType getPresenceStatusType() {
        return presenceStatusType;
    }

    public void setPresenceStatusType(StatusType presenceStatusType) {
        this.presenceStatusType = presenceStatusType;
    }

    public String getPresenceStatusText() {
        return presenceStatusText;
    }

    public void setPresenceStatusText(String presenceStatusText) {
        this.presenceStatusText = presenceStatusText;
    }

}
