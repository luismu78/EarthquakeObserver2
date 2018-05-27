package es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake;

import android.view.View;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.inject.PerFragment;

@PerFragment
public final class EarthquakeViewHolderFactory {

    @Inject
    EarthquakeViewHolderFactory() {

    }

    public EarthquakeViewHolder create(View itemView) {
        return new EarthquakeViewHolder(itemView);
    }
}
