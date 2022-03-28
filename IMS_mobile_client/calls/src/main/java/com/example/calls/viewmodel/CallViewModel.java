package com.example.calls.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.repository.data.AppRepository;
import com.example.repository.data.entities.CallEntity;

import java.util.List;


public class CallViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final LiveData<List<CallEntity>> calls;

    public CallViewModel(@NonNull Application application) {
        super(application);

        repository = ((com.example.conversations.model.BaseApp) application).getRepository();
        calls = repository.getAllCalls();
    }

    public LiveData<List<CallEntity>> getAllCalls() {
        return calls;
    }

    public LiveData<List<CallEntity>> getCallsFor(String usrSipUri) {
        return repository.getCallsFor(usrSipUri);
    }

    public LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return repository.getCallsFor(usrSipUri, buddySipUri);
    }

    public void addCall(CallEntity callEntity) {
        repository.addCall(callEntity);
    }

}
