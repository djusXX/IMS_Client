package com.example.domain.usecases.messages;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.MessageEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import javax.inject.Inject;

public class GetAllMessagesUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetAllMessagesUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<MessageEntity>> execute() {
        return repository.getAllMessages();
    }
}
