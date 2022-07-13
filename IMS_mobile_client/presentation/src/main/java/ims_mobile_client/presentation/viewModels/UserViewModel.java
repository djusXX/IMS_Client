package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.usecases.dataStorage.AddUserUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetLastUserUseCase;
import ims_mobile_client.domain.usecases.sip.UserGetPresenceStateUseCase;
import ims_mobile_client.domain.usecases.sip.UserGetRegistrationStateUseCase;
import ims_mobile_client.domain.usecases.sip.UserRegisterUseCase;
import ims_mobile_client.domain.usecases.sip.UserSetPresenceUseCase;
import ims_mobile_client.presentation.models.UserCredentials;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.UserLoggedStatus;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class UserViewModel extends ViewModel {
    // storage usecases
    private final GetLastUserUseCase getLastUserUseCase;
    private final AddUserUseCase addUserUseCase;
    // sip usecases
    private final UserGetRegistrationStateUseCase userGetRegistrationStateUseCase;
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserGetPresenceStateUseCase userGetPresenceStateUseCase;
    private final UserSetPresenceUseCase userSetPresenceUseCase;

    private UserCredentials userCredentials = null;
    private final MutableLiveData<UserLoggedStatus> userLoggedStatus = new MutableLiveData<>(UserLoggedStatus.UNKNOWN);
    private final MutableLiveData<PresenceStatus> userPresence = new MutableLiveData<>(new PresenceStatus());


    public UserViewModel(GetLastUserUseCase getLastUserUseCase, AddUserUseCase addUserUseCase,
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

        getSavedUser();
    }

    @Override
    protected void onCleared() {
        getLastUserUseCase.dispose();
        userGetRegistrationStateUseCase.dispose();
        userGetPresenceStateUseCase.dispose();
        super.onCleared();
    }

    public UserCredentials getUserCredentials() { return userCredentials; }
    public LiveData<UserLoggedStatus> getUserLoggedStatus() { return userLoggedStatus; }
    public LiveData<PresenceStatus> getUserPresence() { return userPresence; }


    private void getSavedUser() {
        getLastUserUseCase.execute(new DisposableSubscriber<User>() {
            @Override
            public void onNext(User user) {
                userCredentials = new UserCredentials(user.getName(),
                        user.getPassword(), user.getDisplayName(),
                        user.getRealm(), user.getPcscf());
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

    private void saveUserCredentials() {
        if (userCredentials == null) {
            return;
        }
        addUserUseCase.execute(new User(userCredentials.getName(), userCredentials.getPassword(),
                userCredentials.getDisplayName(), userCredentials.getRealm(),
                userCredentials.getPcscf()));
    }

    private void subscribeLoggedStatus() {
        if (userCredentials == null) {
            return;
        }

        userGetRegistrationStateUseCase.execute(new DisposableSubscriber<UserLoggedStatus>() {
            @Override
            public void onNext(UserLoggedStatus registrationState) {
                userLoggedStatus.postValue(registrationState);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, userCredentials.getSipUri());
    }


    private void registerUser() {
        if (userCredentials == null) {
            return;
        }

        userRegisterUseCase.execute(new User(userCredentials.getName(),
                userCredentials.getPassword(), userCredentials.getDisplayName(),
                userCredentials.getRealm(), userCredentials.getPcscf()));

        subscribeLoggedStatus();
        subscribePresence();
    }

    public void addNewUser(String name, String password, String displayName, String realm, String pcscf) {
        userCredentials = new UserCredentials(name, password, displayName, realm, pcscf);
        saveUserCredentials();
        registerUser();
    }

    private void subscribePresence() {
        if (userCredentials == null) {
            return;
        }

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
        }, userCredentials.getSipUri());
    }

    public void updatePresence(PresenceStatus up) {
        userSetPresenceUseCase.execute(up);
    }

}
