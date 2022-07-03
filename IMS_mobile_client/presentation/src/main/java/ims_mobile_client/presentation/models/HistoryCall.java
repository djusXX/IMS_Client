package ims_mobile_client.presentation.models;

public class HistoryCall {

    private String sipUriFrom;
    private String sipUriTo;
    private boolean isVideoCall;
    private boolean isConference = false;
    private long beginTime = -1;
    private long endTime = -1;
}
