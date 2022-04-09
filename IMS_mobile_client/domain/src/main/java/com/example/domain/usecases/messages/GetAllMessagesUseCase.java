package com.example.domain.usecases.messages;

import androidx.lifecycle.LiveData;

import com.example.domain.entities.MessageEntity;
import com.example.domain.repository.ImsMobileClientRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetAllMessagesUseCase {
    private final ImsMobileClientRepository repository;

    public GetAllMessagesUseCase(ImsMobileClientRepository repository) {
        this.repository = repository;
    }

    LiveData<List<MessageEntity>> execute() {
        return repository.getAllMessages();
    }
}
