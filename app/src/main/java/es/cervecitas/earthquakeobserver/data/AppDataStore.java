package es.cervecitas.earthquakeobserver.data;

import java.util.List;

import es.cervecitas.earthquakeobserver.model.Earthquake;
import io.reactivex.Observable;

public interface AppDataStore {

    Observable<List<Earthquake>> getEarthquakeList();
}
