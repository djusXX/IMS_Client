package com.example.domain.usecases.user;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.UserEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import javax.inject.Inject;

public class GetLastUserUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetLastUserUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    public LiveData<UserEntity> execute() { return repository.getLastUser(); }
}
