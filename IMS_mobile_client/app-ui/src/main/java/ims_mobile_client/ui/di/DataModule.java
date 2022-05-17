package ims_mobile_client.ui.di;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.data.IMCRepositoryImpl;
import ims_mobile_client.data.dataStores.DataStoreFactory;
import ims_mobile_client.data.dataStores.DataStoreLocal;
import ims_mobile_client.data.executor.JobExecutor;
import ims_mobile_client.data.mappers.MapperProvider;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMCRepository;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DataModule {

    @Provides
    public static ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }


    @Provides
    public static DataStoreFactory provideDataStoreFactory(DataStoreLocal local) {
        return new DataStoreFactory(local, null);
    }


    @Provides
    @Singleton
    public static IMCRepository provideIMCRepository(DataStoreFactory dataStoreFactory,
                                                     MapperProvider mapper) {
        return new IMCRepositoryImpl(dataStoreFactory, mapper);
    }
}
