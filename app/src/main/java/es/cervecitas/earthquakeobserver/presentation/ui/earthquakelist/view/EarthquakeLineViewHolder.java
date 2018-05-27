package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake.EarthquakeViewHolder;

public class EarthquakeLineViewHolder extends RecyclerView.ViewHolder{

    private final EarthquakeViewHolder viewHolder;

    EarthquakeLineViewHolder(View itemView, EarthquakeViewHolder viewHolder) {
        super(itemView);
        this.viewHolder = viewHolder;
    }

    public EarthquakeViewHolder getViewHolder() {
        return viewHolder;
    }
}
