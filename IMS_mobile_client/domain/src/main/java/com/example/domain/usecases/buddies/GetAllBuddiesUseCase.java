package com.example.domain.usecases.buddies;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;


public class GetAllBuddiesUseCase {
    private final ImsMobileClientRepository repository;

    public GetAllBuddiesUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    Flowable<List<BuddyEntity>> execute() {
        return repository.getAllBuddies();
    }

}
