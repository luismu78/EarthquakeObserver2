package es.cervecitas.earthquakeobserver.presentation.ui.common.presenter;

import es.cervecitas.earthquakeobserver.presentation.common.AbstractDisposableObserver;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.LoadContentView;

/**
 * An abstract observer that provides default behavior for
 *
 * @param <V> the type of {@link LoadContentView}
 * @param <T> the received value type
 */

public class AbstractLoadContentViewObserver<V extends LoadContentView, T> extends AbstractDisposableObserver<T> {

    protected final V view;

    protected AbstractLoadContentViewObserver(V view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        view.hideRetry();
        view.hideContent();
        view.showLoading();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        view.hideLoading();
        view.showRetry();
        view.showError(e.getMessage());
    }

    @Override
    public void onComplete() {
        view.hideLoading();
        view.showContent();
    }
}
