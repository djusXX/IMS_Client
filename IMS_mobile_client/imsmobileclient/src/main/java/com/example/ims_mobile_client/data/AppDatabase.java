package com.example.ims_mobile_client.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ims_mobile_client.data.DAOs.BuddyDao;
import com.example.ims_mobile_client.data.DAOs.CallDao;
import com.example.ims_mobile_client.data.DAOs.MessageDao;
import com.example.ims_mobile_client.data.Entities.BuddyEntity;
import com.example.ims_mobile_client.data.Entities.CallEntity;
import com.example.ims_mobile_client.data.Entities.MessageEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CallEntity.class, MessageEntity.class, BuddyEntity.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    abstract CallDao callDao();
    abstract MessageDao messageDao();
    abstract BuddyDao buddyDao();

    private static volatile AppDatabase INSTANCE;
    private static final int THREADS_NUM = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(THREADS_NUM);

    static AppDatabase getDB(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "imsmobileclient_database")
//                            .addCallback(databaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                INSTANCE.callDao().deleteAll();
                INSTANCE.messageDao().deleteAll();
                INSTANCE.buddyDao().deleteAll();
            });
        }
    };
}
