package ims_mobile_client;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.pjsua2IMS.P2IManager;

@Module
@InstallIn(SingletonComponent.class)
public class Pjsua2IMSModule {

    @Provides
    @Singleton
    public IMCSipService provideIMCSipService(P2IManager service) {
        return new IMCSipServiceImpl(service);
    }

}
