package com.example.ims_mobile_client;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppExecutors {

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor pjsuaIO;
    private final Executor mainThread;

    @Inject
    public AppExecutors() {
        diskIO = Executors.newSingleThreadExecutor();
        networkIO = Executors.newFixedThreadPool(3);
        pjsuaIO = Executors.newFixedThreadPool(4);
        mainThread = new MainThreadExecutor();
    }

    public Executor diskIO() { return diskIO; }

    public Executor networkIO() { return networkIO; }

    public Executor pjsuaIO() { return pjsuaIO; }

    public Executor mainThread() { return mainThread; }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
