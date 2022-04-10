package com.example.domain.usecases.messages;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import javax.inject.Inject;

public class GetMessagesForPair {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetMessagesForPair(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<MessageEntity>> execute(String usrSipUri, String buddySipUri) {
        return repository.getMessagesFor(usrSipUri, buddySipUri);
    }
}
