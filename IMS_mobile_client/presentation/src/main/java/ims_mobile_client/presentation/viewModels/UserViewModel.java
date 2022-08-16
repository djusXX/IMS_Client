package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.StatusType;
import ims_mobile_client.domain.usecases.sip.UserGetLoggedSipUri;
import ims_mobile_client.domain.usecases.sip.UserGetPresenceStateUseCase;
import ims_mobile_client.domain.usecases.sip.UserSetPresenceUseCase;
import ims_mobile_client.domain.models.PresenceStatus;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class UserViewModel extends ViewModel {
    private final UserGetPresenceStateUseCase userGetPresenceStateUseCase;
    private final UserSetPresenceUseCase userSetPresenceUseCase;
    private final UserGetLoggedSipUri userGetLoggedSipUri;

    private final MutableLiveData<String> loggedUserUri = new MutableLiveData<>();
    private final MutableLiveData<PresenceStatus> userPresence = new MutableLiveData<>(new PresenceStatus());


    public UserViewModel(UserGetPresenceStateUseCase userGetPresenceStateUseCase,
                         UserSetPresenceUseCase userSetPresenceUseCase, UserGetLoggedSipUri userGetLoggedSipUri) {
        this.userGetPresenceStateUseCase = userGetPresenceStateUseCase;
        this.userSetPresenceUseCase = userSetPresenceUseCase;
        this.userGetLoggedSipUri = userGetLoggedSipUri;

        subscribePresence();
        fetchUserSipUri();
    }

    @Override
    protected void onCleared() {
        userGetPresenceStateUseCase.dispose();
        super.onCleared();
    }

    public LiveData<String> getLoggedUserUri() { return loggedUserUri; }
    public LiveData<PresenceStatus> getUserPresence() { return userPresence; }

    private void fetchUserSipUri() {
        userGetLoggedSipUri.execute(new DisposableSubscriber<String>() {
            @Override
            public void onNext(String s) {
                loggedUserUri.postValue(s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    private void subscribePresence() {
        userGetPresenceStateUseCase.execute(new DisposableSubscriber<PresenceStatus>() {
            @Override
            public void onNext(PresenceStatus ps) {
                userPresence.postValue(ps);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    public void updatePresence(String type, String text) {
        PresenceStatus presenceStatus = new PresenceStatus(StatusType.valueOf(type), text);
        userSetPresenceUseCase.execute(presenceStatus);
    }

}
