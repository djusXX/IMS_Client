package ims_mobile_client.localDataSource.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ims_mobile_client.localDataSource.daos.BuddyDao;
import ims_mobile_client.localDataSource.daos.CallDao;
import ims_mobile_client.localDataSource.daos.MessageDao;
import ims_mobile_client.localDataSource.daos.UserDao;
import ims_mobile_client.localDataSource.entities.LocalBuddy;
import ims_mobile_client.localDataSource.entities.LocalCall;
import ims_mobile_client.localDataSource.entities.LocalMessage;
import ims_mobile_client.localDataSource.entities.LocalUser;

@Database(entities = {LocalUser.class, LocalCall.class, LocalMessage.class, LocalBuddy.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CallDao callDao();
    public abstract MessageDao messageDao();
    public abstract BuddyDao buddyDao();

    public static final String DB_NAME = "imsmobileclient_database";


}
