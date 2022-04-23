package ims_mobile_client.domain.usecases;

import io.reactivex.observers.DisposableObserver;

public class BaseDisposableObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
