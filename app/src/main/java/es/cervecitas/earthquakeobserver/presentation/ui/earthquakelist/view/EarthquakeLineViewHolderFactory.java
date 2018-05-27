package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import android.view.View;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake.EarthquakeViewHolder;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake.EarthquakeViewHolderFactory;

@PerFragment
class EarthquakeLineViewHolderFactory {

    private final EarthquakeViewHolderFactory earthquakeViewHolderFactory;

    @Inject
    public EarthquakeLineViewHolderFactory(EarthquakeViewHolderFactory earthquakeViewHolderFactory) {
        this.earthquakeViewHolderFactory = earthquakeViewHolderFactory;
    }

    public EarthquakeLineViewHolder create(View itemView) {
        EarthquakeViewHolder earthquakeViewHolder = earthquakeViewHolderFactory.create(itemView);
        return new EarthquakeLineViewHolder(itemView, earthquakeViewHolder);
    }
}
