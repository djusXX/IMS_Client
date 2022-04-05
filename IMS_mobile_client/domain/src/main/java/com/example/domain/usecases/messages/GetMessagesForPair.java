package com.example.domain.usecases.messages;

import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetMessagesForPair {
    private final ImsMobileClientRepository repository;

    public GetMessagesForPair(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    Flowable<List<MessageEntity>> execute(String usrSipUri, String buddySipUri) {
        return repository.getMessagesFor(usrSipUri, buddySipUri);
    }
}
