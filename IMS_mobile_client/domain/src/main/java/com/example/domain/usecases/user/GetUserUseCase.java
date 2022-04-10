package com.example.domain.usecases.user;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.UserEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import javax.inject.Inject;

public class GetUserUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetUserUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    public LiveData<UserEntity> execute(String usrSipUri) { return repository.getUser(usrSipUri); }
}
