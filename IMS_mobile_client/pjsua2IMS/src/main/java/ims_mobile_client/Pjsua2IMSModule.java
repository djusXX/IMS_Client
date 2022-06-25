package ims_mobile_client;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.domain.service.IMSService;

@Module
@InstallIn(SingletonComponent.class)
public class Pjsua2IMSModule {

    @Provides
    @Singleton
    public IMSService provideIMSService(ImsCallingManager imsCallingManager,
                                        ImsMessagingManager imsMessagingManager,
                                        ImsUserManager userManager,
                                        ImsBuddyManager imsBuddyManager) {
        return new IMSServiceImpl(imsCallingManager, imsMessagingManager, userManager, imsBuddyManager);
    }

}
