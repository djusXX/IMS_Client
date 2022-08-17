package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.CallSimpleState;
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
    private final MutableLiveData<String> remoteSipUri = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVideo = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVoiceMuted = new MutableLiveData<>();
    private final MutableLiveData<CallSimpleState> callSimpleState = new MutableLiveData<>();


    public MutableLiveData<String> getRemoteSipUri() {
        return remoteSipUri;
    }

    public MutableLiveData<Boolean> getIsVideo() {
        return isVideo;
    }

    public MutableLiveData<Boolean> getIsVoiceMuted() {
        return isVoiceMuted;
    }

    public MutableLiveData<CallSimpleState> getCallSimpleState() {
        return callSimpleState;
    }


    public void toggleMute() {}
    public void toggleCamera() {}
    public void acceptCall() {}
    public void rejectCall() {}
    public void endCall() {}

    public void addOutgoingCall(String buddySipUri, boolean isVideo) {
        // send request of out call to pjsip
    }


}
