package com.example.presentation.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.usecases.user.GetLastUserUseCase;
import com.example.domain.usecases.user.GetUserUseCase;
import com.example.domain.usecases.user.SetUserUseCase;
import com.example.presentation.models.LocalUser;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocalUserViewModel extends ViewModel {

    private final GetLastUserUseCase getLastUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final SetUserUseCase setUserUseCase;
    private final MediatorLiveData<LocalUser> localUserMLD = new MediatorLiveData<>();

    @Inject
    public LocalUserViewModel(GetLastUserUseCase getLastUserUseCase, GetUserUseCase getUserUseCase, SetUserUseCase setUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.getLastUserUseCase = getLastUserUseCase;
        this.setUserUseCase = setUserUseCase;
    }

    public LiveData<LocalUser> getLastUser() {
        localUserMLD.addSource(getLastUserUseCase.execute(), userEntity -> {
            localUserMLD.postValue(new LocalUser(userEntity));
        });
        return localUserMLD;
    }

    public LiveData<LocalUser> getUser(String usrSipUri) {
        localUserMLD.addSource(getUserUseCase.execute(usrSipUri), userEntity -> {
            localUserMLD.postValue(new LocalUser(userEntity));
        });
        return localUserMLD;
    }

    public void setUser(LocalUser localUser) {
        setUserUseCase.execute(localUser.toUserEntity());
    }
}
