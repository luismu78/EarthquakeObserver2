package es.cervecitas.earthquakeobserver.presentation.ui.common.presenter;

import android.os.Bundle;

import es.cervecitas.earthquakeobserver.presentation.ui.common.view.MVPView;

/**
 * Abstract {@link Presenter} for all presenters to extend.
 *
 * @param <T> the type of the {@link MVPView}.
 */

public abstract class BasePresenter<T extends MVPView> implements Presenter {

    protected final T view;

    BasePresenter(T view) {
        this.view = view;
    }

    @Override
    public void onStart(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onEnd() {

    }
}
