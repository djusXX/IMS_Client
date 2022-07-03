package ims_mobile_client;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.serviceManagers.ImsBuddyManager;
import ims_mobile_client.serviceManagers.ImsCallingManager;
import ims_mobile_client.serviceManagers.ImsMessagingManager;
import ims_mobile_client.serviceManagers.ImsUserManager;

@Module
@InstallIn(SingletonComponent.class)
public class Pjsua2IMSModule {

    @Provides
    @Singleton
    public IMSManager provideIMSService(ImsCallingManager imsCallingManager,
                                        ImsMessagingManager imsMessagingManager,
                                        ImsUserManager userManager,
                                        ImsBuddyManager imsBuddyManager) {
        return new IMSManagerImpl(imsCallingManager, imsMessagingManager, userManager, imsBuddyManager);
    }

}
