package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.inject.PerFragment;

/**
 * Created by luism on 16/01/2018.
 */
@PerFragment
public final class OnItemViewClickListenerFactory<T> {

    private final OnItemClickListener<T> onItemClickListener;

    @SuppressWarnings("unused")
    @Inject
    OnItemViewClickListenerFactory(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemViewClickListener create(T item) {
        return new OnItemViewClickListener<>(onItemClickListener, item);
    }
}
