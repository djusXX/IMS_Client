package ims_mobile_client.localDataSource.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import javax.inject.Inject;

import ims_mobile_client.localDataSource.daos.BuddyDao;
import ims_mobile_client.localDataSource.daos.CallDao;
import ims_mobile_client.localDataSource.daos.MessageDao;
import ims_mobile_client.localDataSource.daos.UserDao;
import ims_mobile_client.localDataSource.models.LocalBuddy;
import ims_mobile_client.localDataSource.models.LocalCall;
import ims_mobile_client.localDataSource.models.LocalMessage;
import ims_mobile_client.localDataSource.models.LocalUser;

@Database(entities = {LocalUser.class, LocalCall.class, LocalMessage.class, LocalBuddy.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "imsmobileclient_database";

    public abstract UserDao userDao();
    public abstract CallDao callDao();
    public abstract MessageDao messageDao();
    public abstract BuddyDao buddyDao();

    private static AppDatabase INSTANCE = null;

    @Inject
    public AppDatabase() {
        super();
    }

    public final AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized(AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME).build();
                }
                return INSTANCE;
            }
        }
        return INSTANCE;
    }

}
