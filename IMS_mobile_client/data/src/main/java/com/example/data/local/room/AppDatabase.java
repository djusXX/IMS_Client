package com.example.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.domain.entities.BuddyEntity;
import com.example.domain.entities.CallEntity;
import com.example.domain.entities.MessageEntity;
import com.example.domain.entities.UserEntity;


@Database(entities = {UserEntity.class, CallEntity.class, MessageEntity.class, BuddyEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CallDao callDao();
    public abstract MessageDao messageDao();
    public abstract BuddyDao buddyDao();

    public static final String DB_NAME = "imsmobileclient_database";


}
