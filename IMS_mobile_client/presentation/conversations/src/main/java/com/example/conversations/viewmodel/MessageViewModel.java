package com.example.conversations.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.conversations.model.BaseApp;
import com.example.repository.data.AppRepository;
import com.example.repository.data.entities.MessageEntity;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final LiveData<List<MessageEntity>> messages;

    public MessageViewModel(@NonNull Application application) {
        super(application);

        repository = ((BaseApp) application).getRepository();;
        messages = repository.getAllMessages();
    }

    public LiveData<List<MessageEntity>> getAllMessages() { return messages; }

    public LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri) {
        return repository.getMessagesFor(usrSipUri);
    }

    public LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return repository.getMessagesFor(usrSipUri, buddySipUri);
    }

    public void addMessage(MessageEntity messageEntity) { repository.addMessage(messageEntity); }

}
