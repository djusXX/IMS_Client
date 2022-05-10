package ims_mobile_client.ui;

import javax.inject.Inject;

import ims_mobile_client.domain.executors.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UiThread implements PostExecutionThread {

    @Inject
    public UiThread() {
        super();
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
