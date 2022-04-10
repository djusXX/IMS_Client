package com.example.domain.usecases.buddies;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import javax.inject.Inject;

public class GetBuddiesForUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetBuddiesForUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<BuddyEntity>> execute(String usrSipUri) {
        return repository.getBuddiesFor(usrSipUri);
    }
}
