package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ims_mobile_client.domain.usecases.repository.AddUserUseCase;
import ims_mobile_client.domain.usecases.repository.GetLastUserUseCase;
import ims_mobile_client.domain.usecases.service.GetRegistrationStateUseCase;
import ims_mobile_client.domain.usecases.service.SetUserPresenceUseCase;
import ims_mobile_client.presentation.models.UserCredentials;
import ims_mobile_client.presentation.models.UserPresence;
import ims_mobile_client.presentation.models.UserRegistration;

public class UserViewModel extends ViewModel {
    // repo usecases
    private final GetLastUserUseCase getLastUserUseCase;
    private final AddUserUseCase addUserUseCase;

    // service usecases
    private final GetRegistrationStateUseCase getRegistrationStateUseCase;
    private final RequestUserRegistrationUseCase requestUserRegistrationUseCase;
    private final GetUserPresenceStateUseCase getUserPresenceStateUseCase;
    private final SetUserPresenceUseCase setUserPresenceUseCase;

    private final MutableLiveData<UserCredentials> userCredentials = new MutableLiveData<>();
    private final MutableLiveData<UserRegistration> userRegistration = new MutableLiveData<>();
    private final MutableLiveData<UserPresence> userPresence = new MutableLiveData<>();


    public UserViewModel(GetLastUserUseCase getLastUserUseCase, AddUserUseCase addUserUseCase, SetUserPresenceUseCase setUserPresenceUseCase) {
        this.getLastUserUseCase = getLastUserUseCase;
        this.addUserUseCase = addUserUseCase;
        this.setUserPresenceUseCase = setUserPresenceUseCase;
    }
}
