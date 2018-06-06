package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

import android.view.View;

/**
 * Created by luism on 16/01/2018.
 */

public final class OnItemViewClickListener<T> implements View.OnClickListener {

    private final OnItemClickListener<T> onItemClickListener;
    private final T item;

    public OnItemViewClickListener(OnItemClickListener<T> onItemClickListener, T item) {
        this.onItemClickListener = onItemClickListener;
        this.item = item;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClicked(item);
    }
}
