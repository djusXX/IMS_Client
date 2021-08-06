//package com.example.ims_mobile_client.data;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import com.example.ims_mobile_client.data.DAOs.SipContactDao;
//import com.example.ims_mobile_client.data.entities.SipContactEntity;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Database(entities = {SipContactEntity.class}, version = 1)
//public abstract class IMSClientDatabase extends RoomDatabase {
//    public abstract SipContactDao sipContactDao();
//
//    private static volatile IMSClientDatabase INSTANCE;
//    private static final int NUMBER_OF_THREADS = 4;
//    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//
//    public static IMSClientDatabase getDB(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (IMSClientDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            IMSClientDatabase.class, "IMSClient_database")
//                            .addCallback(IMSClientDBCallback)
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//
//    private static RoomDatabase.Callback IMSClientDBCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() -> {
//                SipContactDao sipContactDao = INSTANCE.sipContactDao();
//            });
//        }
//    };
//
//}
