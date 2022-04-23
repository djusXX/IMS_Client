package ims_mobile_client.domain.usecases;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BaseSingleObserver<T> implements SingleObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
