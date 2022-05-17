package ims_mobile_client.ui.di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.ui.UiThread;

@Module
@InstallIn(SingletonComponent.class)
public class UiModule {

    @Provides
    public static PostExecutionThread UiThread() {
        return new UiThread();
    }
}
