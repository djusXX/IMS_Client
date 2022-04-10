package com.example.domain.usecases.user;

import com.example.domain.entities.UserEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import javax.inject.Inject;

public class SetUserUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public SetUserUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    public void execute(UserEntity userEntity) {
        repository.addUser(userEntity);
    }
}
