package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserLoggedStatus;
import ims_mobile_client.domain.usecases.dataStorage.AddUserUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetLastUserUseCase;
import ims_mobile_client.domain.usecases.sip.UserGetRegistrationStateUseCase;
import ims_mobile_client.domain.usecases.sip.UserRegisterUseCase;
import ims_mobile_client.presentation.models.UserCredentials;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    // storage usecases
    private final GetLastUserUseCase getLastUserUseCase;
    private final AddUserUseCase addUserUseCase;
    // sip usecases
    private final UserGetRegistrationStateUseCase userGetRegistrationStateUseCase;
    private final UserRegisterUseCase userRegisterUseCase;

    private final MutableLiveData<UserCredentials> userCredentials = new MutableLiveData<>();
    private final MutableLiveData<UserLoggedStatus> userRegistrationStatus = new MutableLiveData<>(UserLoggedStatus.UNKNOWN);

    public LoginViewModel(GetLastUserUseCase getLastUserUseCase,
                          AddUserUseCase addUserUseCase,
                          UserGetRegistrationStateUseCase userGetRegistrationStateUseCase,
                          UserRegisterUseCase userRegisterUseCase) {
        this.getLastUserUseCase = getLastUserUseCase;
        this.addUserUseCase = addUserUseCase;
        this.userGetRegistrationStateUseCase = userGetRegistrationStateUseCase;
        this.userRegisterUseCase = userRegisterUseCase;

        fetchUserCredentials();
        fetchRegistrationStatus();
    }

    @Override
    protected void onCleared() {
        getLastUserUseCase.dispose();
        userGetRegistrationStateUseCase.dispose();
        super.onCleared();
    }

    public LiveData<UserCredentials> getUserCredentials() {
        return userCredentials;
    }

    public LiveData<UserLoggedStatus> getUserRegistrationStatus() {
        return userRegistrationStatus;
    }

    public void registerUser(String name, String password, String displayName, String realm, String pcscf) {
        User user = new User(name, password, displayName, realm, pcscf);
        userRegisterUseCase.execute(user);
        userCredentials.postValue(new UserCredentials(name, password, displayName, realm, pcscf));
        saveUserInStorage(name, password, displayName, realm, pcscf);
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
        userGetRegistrationStateUseCase.execute(new DisposableSubscriber<UserLoggedStatus>() {
            @Override
            public void onNext(UserLoggedStatus registrationState) {
                userRegistrationStatus.postValue(registrationState);
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
