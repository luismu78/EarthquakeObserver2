package es.cervecitas.earthquakeobserver.presentation.common;


import io.reactivex.observers.DisposableObserver;

/**
 * An abstract {@link DisposableObserver} that provides optional overrides to all
 * {@link DisposableObserver} methods.
 *
 * @param <T> the received value type
 */

public abstract class AbstractDisposableObserver<T> extends DisposableObserver<T> {

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
