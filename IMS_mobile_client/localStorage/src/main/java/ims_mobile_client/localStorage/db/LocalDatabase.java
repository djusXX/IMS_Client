package ims_mobile_client.localStorage.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ims_mobile_client.localStorage.daos.BuddyDao;
import ims_mobile_client.localStorage.daos.CallDao;
import ims_mobile_client.localStorage.daos.MessageDao;
import ims_mobile_client.localStorage.daos.UserDao;
import ims_mobile_client.localStorage.models.LocalBuddy;
import ims_mobile_client.localStorage.models.LocalCall;
import ims_mobile_client.localStorage.models.LocalMessage;
import ims_mobile_client.localStorage.models.LocalUser;

@Database(entities = {LocalUser.class, LocalCall.class, LocalMessage.class, LocalBuddy.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public static final String DB_NAME = "imsmobileclient_database";

    public abstract UserDao userDao();
    public abstract CallDao callDao();
    public abstract MessageDao messageDao();
    public abstract BuddyDao buddyDao();

//    private static LocalDatabase INSTANCE = null;


//    public final LocalDatabase getInstance(final Context context) {
//        if (INSTANCE == null) {
//            synchronized(LocalDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            LocalDatabase.class, DB_NAME).build();
//                }
//                return INSTANCE;
//            }
//        }
//        return INSTANCE;
//    }

}


