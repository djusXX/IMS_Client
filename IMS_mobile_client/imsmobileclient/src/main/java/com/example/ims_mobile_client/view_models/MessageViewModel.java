package com.example.ims_mobile_client.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ims_mobile_client.AppBase;
import com.example.ims_mobile_client.data.AppRepository;
import com.example.ims_mobile_client.data.Entities.MessageEntity;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final LiveData<List<MessageEntity>> messages;

    public MessageViewModel(@NonNull Application application) {
        super(application);

        repository = ((AppBase) application).getRepository();
        messages = repository.getAllMessages();
    }

    public LiveData<List<MessageEntity>> getAllMessages() { return messages; }

    public void addMessage(MessageEntity messageEntity) { repository.addMessage(messageEntity); }

}
