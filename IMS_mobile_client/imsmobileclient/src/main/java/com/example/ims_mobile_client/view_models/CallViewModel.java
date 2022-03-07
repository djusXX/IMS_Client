package com.example.ims_mobile_client.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ims_mobile_client.AppBase;
import com.example.ims_mobile_client.data.AppRepository;
import com.example.ims_mobile_client.data.entities.CallEntity;

import java.util.List;

public class CallViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final LiveData<List<CallEntity>> calls;

    public CallViewModel(@NonNull Application application) {
        super(application);

        repository = ((AppBase) application).getRepository();
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
