package com.example.domain.usecases.calls;

import com.example.domain.entities.CallEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetCallsForUseCase {
    private final ImsMobileClientRepository repository;

    public GetCallsForUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    Flowable<List<CallEntity>> execute(String usrSipUri) {
        return repository.getCallsFor(usrSipUri);
    }
}
