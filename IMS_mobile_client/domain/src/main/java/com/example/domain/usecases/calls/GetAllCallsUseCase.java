package com.example.domain.usecases.calls;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.CallEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetAllCallsUseCase {
    private final ImsMobileClientRepository repository;

    public GetAllCallsUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<CallEntity>> execute() {
        return repository.getAllCalls();
    }
}
