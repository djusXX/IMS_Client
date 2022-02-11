package com.example.ims_mobile_client.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "imsmobileclient_database";

    public abstract CallDao callDao();
    public abstract MessageDao messageDao();
    public abstract BuddyDao buddyDao();

    private static AppDatabase INSTANCE;
    private final MutableLiveData<Boolean> isDBCreated = new MutableLiveData<>();
    private static final int THREADS_NUM = 4;
    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(THREADS_NUM);

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDB(context.getApplicationContext());
                    INSTANCE.updateDBCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDB(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase.getInstance(context).setDBCreated();
                    }
                }).build();
    }

    private void setDBCreated() {
        isDBCreated.postValue(true);
    }

    public LiveData<Boolean> getDBCreated() {
        return isDBCreated;
    }

    private void updateDBCreated(final Context context) {
        if (context.getDatabasePath(DB_NAME).exists()) {
            setDBCreated();
        }
    }

}
