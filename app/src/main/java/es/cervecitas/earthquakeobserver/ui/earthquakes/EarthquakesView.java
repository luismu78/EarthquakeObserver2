package es.cervecitas.earthquakeobserver.ui.earthquakes;

import java.util.List;

import es.cervecitas.earthquakeobserver.model.Earthquake;

public interface EarthquakesView {

    void showLoading();

    void hideLoading();

    void showEarthquakeList(List<Earthquake> earthquakeItemList);

    void showErrorMessage();

    void launchEarthquakeDetail(Earthquake earthquake);
}
