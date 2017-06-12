package es.cervecitas.earthquakeobserver.data;

import java.util.List;

import es.cervecitas.earthquakeobserver.model.Earthquake;
import io.reactivex.Observable;

public class AppRepository implements AppDataStore {


    @Override
    public Observable<List<Earthquake>> getEarthquakeList() {
        return null;
    }
}
