package com.example.data.di;


import com.example.data.local.datasource.LocalDataSource;
import com.example.data.repository.ImsMobileClientRepositoryImpl;
import com.example.domain.repository.ImsMobileClientRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public ImsMobileClientRepository getRepo(LocalDataSource localDataSource) {
        return new ImsMobileClientRepositoryImpl(localDataSource);
    }
}
