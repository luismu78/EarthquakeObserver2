package es.cervecitas.earthquakeobserver.service.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.network.model.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.network.model.Feature;
import es.cervecitas.earthquakeobserver.service.EarthquakeService;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class EarthquakeDataRepository extends BaseRepository {

    private final EarthquakeService service;

    public EarthquakeDataRepository(EarthquakeService service) {
        this.service = service;
    }

    public Observable<List<Earthquake>> getEarthquakes(
            String format,
            String eventType,
            String orderBy,
            long minMag,
            int limit,
            String startDate) {

        return getEarthquakeFromServer(format, eventType, orderBy, minMag, limit, startDate).cache();
    }

    private Observable<List<Earthquake>> getEarthquakeFromServer(
            String format,
            String eventType,
            String orderBy,
            long minMag,
            int limit,
            String startDate) {

        return service.getEarthquakeObjects(format, eventType, orderBy, minMag, limit, startDate)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, EarthquakeObjects>() {
                    @Override
                    public EarthquakeObjects apply(@NonNull Throwable throwable) throws Exception {
                        return new EarthquakeObjects();
                    }
                })
                .map(new Function<EarthquakeObjects, List<Earthquake>>() {
                    @Override
                    public List<Earthquake> apply(@NonNull EarthquakeObjects earthquakeObjects) throws Exception {
                        List<Earthquake> earthquakeList = new ArrayList<>();

                        for (Feature feature : earthquakeObjects.getFeatures()) {
                            // magnitude
                            Double magnitude = feature.getProperties().getMag();
                            // location
                            String location = feature.getProperties().getPlace();
                            // date & time
                            Calendar calDate = Calendar.getInstance();
                            calDate.setTimeInMillis(feature.getProperties().getTime());
                            // url
                            String url = feature.getProperties().getUrl();

                            earthquakeList.add(new Earthquake(magnitude, location, calDate.getTime().getTime(), url));
                        }

                        return earthquakeList;
                    }
                });
    }
}