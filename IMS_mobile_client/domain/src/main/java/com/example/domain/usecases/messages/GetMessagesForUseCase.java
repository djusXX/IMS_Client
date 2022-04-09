package com.example.domain.usecases.messages;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.MessageEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetMessagesForUseCase {
    private final ImsMobileClientRepository repository;

    public GetMessagesForUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<MessageEntity>> execute(String usrSipUri) {
        return repository.getMessagesFor(usrSipUri);
    }
}
