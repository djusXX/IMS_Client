package ims_mobile_client.data.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ims_mobile_client.domain.executors.ThreadExecutor;

public class JobExecutor implements ThreadExecutor {
    private final LinkedBlockingQueue<Runnable> workQueue;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final ThreadFactory threadFactory;

    @Inject
    public JobExecutor() {
        workQueue = new LinkedBlockingQueue<>();
        threadFactory = new ThreadFactory() {
            private static final String name = "ims_mobile_client_data_";
            private int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, name + counter++);
            }
        };
        threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, workQueue, threadFactory);
    }

    @Override
    public void execute(Runnable command) {
        if(command != null) {
            this.threadPoolExecutor.execute(command);
        }
    }
}
