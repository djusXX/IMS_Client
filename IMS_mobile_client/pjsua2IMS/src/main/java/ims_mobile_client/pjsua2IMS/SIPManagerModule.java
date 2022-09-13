package ims_mobile_client.pjsua2IMS;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class SIPManagerModule {

    @Provides
    @Singleton
    public static P2IHelperImpl provideP2IHelper() {
        return new P2IHelperImpl();
    }
}
