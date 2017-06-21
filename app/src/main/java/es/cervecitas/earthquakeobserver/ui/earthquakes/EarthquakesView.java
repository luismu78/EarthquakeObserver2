package es.cervecitas.earthquakeobserver.ui.earthquakes;

import es.cervecitas.earthquakeobserver.model.Earthquake;

public interface EarthquakesView {

    void showLoading();

    void hideLoading();

    void displayEarthquake(Earthquake earthquakeItemList);

    void clearEarthquakes();

    void showErrorMessage();

    void hideErrorMessage();

    void launchEarthquakeDetail(Earthquake earthquake);
}
