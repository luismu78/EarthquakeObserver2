package es.cervecitas.earthquakeobserver.ui.earthquakes;

import java.util.List;

import es.cervecitas.earthquakeobserver.model.Earthquake;

public interface EarthquakesView {

    void showLoading();

    void hideLoading();

//    void displayEarthquake(List<Earthquake> earthquakeItemList);

    void displayEarthquake(Earthquake earthquakeItemList);

    void clearEarthquakes();

    void showErrorMessage();

    void hideErrorMessage();

    void launchEarthquakeDetail(Earthquake earthquake);
}
