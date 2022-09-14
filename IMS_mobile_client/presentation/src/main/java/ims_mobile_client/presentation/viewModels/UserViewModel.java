package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.StatusType;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserRegistrationStatus;
import ims_mobile_client.domain.usecases.dataStorage.AddUserUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetLastUserUseCase;
import ims_mobile_client.domain.usecases.sip.UserGetPresenceStateUseCase;
import ims_mobile_client.domain.usecases.sip.UserGetRegistrationStateUseCase;
import ims_mobile_client.domain.usecases.sip.UserRegisterUseCase;
import ims_mobile_client.domain.usecases.sip.UserSetPresenceUseCase;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.presentation.models.UserCredentials;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class UserViewModel extends ViewModel {
    private final GetLastUserUseCase getLastUserUseCase;
    private final AddUserUseCase addUserUseCase;
    private final UserGetRegistrationStateUseCase userGetRegistrationStateUseCase;
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserGetPresenceStateUseCase userGetPresenceStateUseCase;
    private final UserSetPresenceUseCase userSetPresenceUseCase;

    private final MutableLiveData<UserCredentials> userCredentials = new MutableLiveData<>();
    private final MutableLiveData<UserRegistrationStatus> userRegistrationStatus = new MutableLiveData<>(UserRegistrationStatus.UNKNOWN);
    private final MutableLiveData<PresenceStatus> userPresence = new MutableLiveData<>(new PresenceStatus());


    @Inject
    public UserViewModel(GetLastUserUseCase getLastUserUseCase,
                         AddUserUseCase addUserUseCase,
                         UserGetRegistrationStateUseCase userGetRegistrationStateUseCase,
                         UserRegisterUseCase userRegisterUseCase,
                         UserGetPresenceStateUseCase userGetPresenceStateUseCase,
                         UserSetPresenceUseCase userSetPresenceUseCase) {
        this.getLastUserUseCase = getLastUserUseCase;
        this.addUserUseCase = addUserUseCase;
        this.userGetRegistrationStateUseCase = userGetRegistrationStateUseCase;
        this.userRegisterUseCase = userRegisterUseCase;
        this.userGetPresenceStateUseCase = userGetPresenceStateUseCase;
        this.userSetPresenceUseCase = userSetPresenceUseCase;

//        subscribePresence();
    }

    @Override
    protected void onCleared() {
        getLastUserUseCase.dispose();
        userGetRegistrationStateUseCase.dispose();
        userGetPresenceStateUseCase.dispose();
        super.onCleared();
    }

    public LiveData<UserCredentials> getUserCredentials() { return userCredentials; }
    public LiveData<UserRegistrationStatus> getUserRegistrationStatus() { return userRegistrationStatus; }
    public LiveData<PresenceStatus> getUserPresence() { return userPresence; }

    public void registerUser(String name, String password, String displayName, String realm, String pcscf) {
        User user = new User(name, password, displayName, realm, pcscf);
        userRegisterUseCase.execute(user);
        userCredentials.postValue(new UserCredentials(name, password, displayName, realm, pcscf));
        saveUserInStorage(name, password, displayName, realm, pcscf);
        fetchRegistrationStatus();
    }

    private void saveUserInStorage(String name, String password, String displayName, String realm, String pcscf) {
        addUserUseCase.execute(new User(name, password, displayName, realm, pcscf));
    }

    private void fetchUserCredentials() {
        getLastUserUseCase.execute(new DisposableSubscriber<User>() {
            @Override
            public void onNext(User user) {
                userCredentials.postValue(new UserCredentials(user.getName(),
                        user.getPassword(), user.getDisplayName(),
                        user.getRealm(), user.getPcscf()));
                getLastUserUseCase.dispose();
            }

            @Override
            public void onError(Throwable t) {
                getLastUserUseCase.dispose();
            }

            @Override
            public void onComplete() {
                getLastUserUseCase.dispose();
            }
        }, null);
    }

    private void fetchRegistrationStatus() {
        userGetRegistrationStateUseCase.execute(new DisposableSubscriber<UserRegistrationStatus>() {
            @Override
            public void onNext(UserRegistrationStatus registrationState) {
                userRegistrationStatus.postValue(registrationState);
                request(1);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    public void subscribePresence() {
        userGetPresenceStateUseCase.execute(new DisposableSubscriber<PresenceStatus>() {
            @Override
            public void onNext(PresenceStatus ps) {
                userPresence.postValue(ps);
                request(1);
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
        subscribePresence();
    }

}
