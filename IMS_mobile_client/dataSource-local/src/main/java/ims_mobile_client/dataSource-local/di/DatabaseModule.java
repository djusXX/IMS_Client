package com.example.data.di;

import static com.example.data.local.room.AppDatabase.DB_NAME;

import android.content.Context;

import androidx.room.Room;

import com.example.data.local.datasource.LocalDataSource;
import com.example.data.local.datasource.LocalDataSourceImpl;
import com.example.data.local.room.AppDatabase;
import com.example.domain.repository.ImsMobileClientRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public AppDatabase getDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    @Singleton
    @Provides
    public LocalDataSource getLDS(AppDatabase db) {
        return new LocalDataSourceImpl(db);
    }
}
