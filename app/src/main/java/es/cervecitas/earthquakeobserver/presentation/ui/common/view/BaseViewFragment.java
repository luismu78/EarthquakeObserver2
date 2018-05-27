package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.presentation.ui.common.presenter.Presenter;

/**
 * A {@link BaseFragment} that contains and invokes {@link Presenter} lifecycle invocations.
 *
 * @param <T> the type of the {@link Presenter}.
 */

public abstract class BaseViewFragment<T extends Presenter> extends BaseFragment implements MVPView {

    @Inject
    protected T presenter;

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        /*
         * The Presenter.onStart method is called in onViewStateRestored so that the Fragment’s
         * views are bound before the presentation begins. This ensures that no NullPointerException
         * occurs if the Presenter calls an MVPView method that uses a bound view.
         *
         * Furthermore, Fragments that do not return a non-null View in onCreateView will result in
         * onViewStateRestored not being called. This results in Presenter.onStart not being
         * invoked. Therefore, no-UI Fragments do not support Presenter-View pairs. We could modify
         * our code to support Presenter-View pairs in no-UI Fragments if needed. However, I will
         * keep things as is since I do not consider it appropriate to have a Presenter-View pair
         * in a no-UI Fragment. Do feel free to disagree and refactor.
         */
        presenter.onStart(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        presenter.onEnd();
        super.onDestroyView();
    }
}
