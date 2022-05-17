package ims_mobile_client;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.pjsua2IMS.SipManager;

@Module
@InstallIn(SingletonComponent.class)
public class Pjsua2IMSModule {

    @Provides
    @Singleton
    public IMCSipService provideIMCSipService(SipManager service) {
        return new IMCSipServiceImpl(service);
    }

}
