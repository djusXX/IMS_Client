package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ims_mobile_client.domain.models.CallState;
import ims_mobile_client.presentation.models.CallInfo;

/**
 * getCallDetailsUseCase // for active call
 * setCallAttribute[manyUseCases] // for active call
 *      - end/decline/mute/answer/...
 *
 *
 * getHistoryCallListUseCase
 * saveCallDataInDb
 * */
public class CallViewModel extends ViewModel {


    // subscribe states of below data on server
    private final MutableLiveData<String> sipUriFrom = new MutableLiveData<>();
    private final MutableLiveData<String> sipUriTo = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVideo = new MutableLiveData<>();
    private final MutableLiveData<Long> beginTime = new MutableLiveData<>();
    private final MutableLiveData<Long> endTime = new MutableLiveData<>();
    private final MutableLiveData<Integer> callId = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isOnHold = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVoiceMuted = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVideoMuted = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isActive = new MutableLiveData<>();
    private final MutableLiveData<CallState> callState = new MutableLiveData<>();



    public MutableLiveData<String> getSipUriFrom() {
        return sipUriFrom;
    }

    public MutableLiveData<String> getSipUriTo() {
        return sipUriTo;
    }

    public MutableLiveData<Boolean> getIsVideo() {
        return isVideo;
    }

    public MutableLiveData<Long> getBeginTime() {
        return beginTime;
    }

    public MutableLiveData<Long> getEndTime() {
        return endTime;
    }

    public MutableLiveData<Integer> getCallId() {
        return callId;
    }

    public MutableLiveData<Boolean> getIsOnHold() {
        return isOnHold;
    }

    public MutableLiveData<Boolean> getIsVoiceMuted() {
        return isVoiceMuted;
    }

    public MutableLiveData<Boolean> getIsVideoMuted() {
        return isVideoMuted;
    }

    public MutableLiveData<Boolean> getIsActive() {
        return isActive;
    }

    public MutableLiveData<CallState> getCallState() {
        return callState;
    }

    public void addOutgoingCall(String buddySipUri, boolean isVideo) {
        // send request of out call to pjsip
    }



}
