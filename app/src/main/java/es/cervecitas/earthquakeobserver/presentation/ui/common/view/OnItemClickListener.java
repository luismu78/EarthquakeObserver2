package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

/**
 * Listener for item clicks.
 *
 * @param <T> the type of the item that is clicked
 */
public interface OnItemClickListener<T> {

    void onItemClicked(T item);
}
