package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.LoadContentView;

public interface EarthquakeListView extends LoadContentView {

    void add(PresentationEarthquake presentationEarthquake);

    void show();

    void clear();

    void showDetails(PresentationEarthquake presentationEarthquake);
}
