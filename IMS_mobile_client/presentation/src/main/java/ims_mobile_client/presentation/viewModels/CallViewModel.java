package ims_mobile_client.presentation.viewModels;

import android.view.SurfaceHolder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.CallSimpleState;
import ims_mobile_client.domain.usecases.sip.GetCurrentCallUseCase;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class CallViewModel extends ViewModel {
    private final GetCurrentCallUseCase getCurrentCallUseCase;


    private final MutableLiveData<String> remoteSipUri = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVideo = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isVoiceMuted = new MutableLiveData<>();
    private final MutableLiveData<CallSimpleState> callSimpleState = new MutableLiveData<>();
    private final MutableLiveData<Call> currentCall = new MutableLiveData<>();

    @Inject
    public CallViewModel(GetCurrentCallUseCase getCurrentCallUseCase) {
        this.getCurrentCallUseCase = getCurrentCallUseCase;
    }

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

    public void updateVideoPreview(SurfaceHolder holder) {

    }

    public void stopVideoPreview() {

    }

    public void updateVideoWindow(boolean show) {

    }




    private void getActiveCall() {
        getCurrentCallUseCase.execute(new DisposableSubscriber<Call>() {
            @Override
            public void onNext(Call call) {
                currentCall.postValue(call);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

}
