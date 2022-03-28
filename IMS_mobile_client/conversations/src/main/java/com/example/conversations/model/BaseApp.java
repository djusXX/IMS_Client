package com.example.conversations.model;

import com.example.repository.data.AppRepository;

public interface BaseApp {
    AppRepository getRepository();
}
