package com.example.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;



@Database(entities = {CallEntity.class, MessageEntity.class, BuddyEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    abstract CallDao callDao();
    abstract MessageDao messageDao();
    abstract BuddyDao buddyDao();

    public static final String DB_NAME = "imsmobileclient_database";


}
