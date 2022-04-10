package com.example.domain.usecases.calls;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.CallEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import javax.inject.Inject;

public class GetCallsForUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetCallsForUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<CallEntity>> execute(String usrSipUri) {
        return repository.getCallsFor(usrSipUri);
    }
}
