package ims_mobile_client.ui.di;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.data.IMSRepositoryImpl;
import ims_mobile_client.data.dataStores.DataStoreFactory;
import ims_mobile_client.data.dataStores.DataStoreLocal;
import ims_mobile_client.data.executor.JobExecutor;
import ims_mobile_client.data.mappers.MapperProvider;
import ims_mobile_client.data.sip.SIPManager;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.pjsua2IMS.SIPManagerImpl;

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
    public static SIPManager provideSIPManager() {
        return new SIPManagerImpl();
    }


    @Provides
    @Singleton
    public static IMSRepository provideIMCRepository(DataStoreFactory dataStoreFactory,
                                                     MapperProvider mapper,
                                                     SIPManager sipManager) {
        return new IMSRepositoryImpl(dataStoreFactory, mapper, sipManager);
    }
}
