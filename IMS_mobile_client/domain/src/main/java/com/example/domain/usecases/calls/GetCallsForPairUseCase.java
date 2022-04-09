package com.example.domain.usecases.calls;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.CallEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetCallsForPairUseCase {
    private final ImsMobileClientRepository repository;

    public GetCallsForPairUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<CallEntity>> execute(String usrSipUri, String buddySipUri) {
        return repository.getCallsFor(usrSipUri, buddySipUri);
    }
}
