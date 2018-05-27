package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter;

import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.presenter.Presenter;

public interface EarthquakeListPresenter extends Presenter {

    void onListEarthquakes();

    void onRetry();

    void onEarthquakeClicked(PresentationEarthquake presentationEarthquake);
}
