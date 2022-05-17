package ims_mobile_client.localStorage;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.data.dataSources.LocalDataSource;
import ims_mobile_client.localStorage.db.LocalDatabase;
import ims_mobile_client.localStorage.mappers.MapperProvider;

@Module
@InstallIn(SingletonComponent.class)
public class LocalStorageModule {

    @Provides
    @Singleton
    public final LocalDatabase provideLocalDataSourceDatabase(@ApplicationContext Context context) {
        final String DB_NAME = "imsmobileclient_database";
        return Room.databaseBuilder(context.getApplicationContext(),
                LocalDatabase.class, DB_NAME).build();
    }


    @Provides
    @Singleton
    public LocalDataSource provideLocalDataSource(LocalDatabase db, MapperProvider mappers) {
        return new LocalDataSourceImpl(db, mappers);
    }
}
