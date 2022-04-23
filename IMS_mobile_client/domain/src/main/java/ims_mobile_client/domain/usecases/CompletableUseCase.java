package ims_mobile_client.domain.usecases;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

public abstract class CompletableUseCase<Params> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final Disposable subscription;

    protected CompletableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        subscription = Disposables.empty();
    }

    protected abstract Completable buildUseCaseObservable(Params params);

    public Completable execute(Params params) {
        return this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    public void unsubscribe() {
        if(!subscription.isDisposed()) { subscription.dispose(); }
    }
}
