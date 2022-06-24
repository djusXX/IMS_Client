package ims_mobile_client.domain.usecases;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class FlowableUseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public FlowableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        disposables = new CompositeDisposable();
    }

    protected abstract Flowable<T> buildUseCaseFlowable(@Nullable Params params);

    public void execute(DisposableSubscriber<T> subscriber, @Nullable Params params) {
        Flowable<T> flowable = this.buildUseCaseFlowable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

        disposables.add(flowable.subscribeWith(subscriber));
    }

    public void dispose() {
        if(!disposables.isDisposed()) { disposables.dispose(); }
    }
}
