package ims_mobile_client.ui.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.data.dataSources.RemoteDataSource;

@Module
@InstallIn(SingletonComponent.class)
abstract class RemoteStorageModule {

//    @Binds
//    abstract RemoteDataSource bindRemoteDataSource(RemoteDataSourceImpl remoteDataSource);
}
