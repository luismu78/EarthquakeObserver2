package es.cervecitas.earthquakeobserver.model.service.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.model.service.EarthquakeService;
import es.cervecitas.earthquakeobserver.model.service.model.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.model.service.model.Feature;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class EarthquakeRepository extends BaseRepository {

    private static final String CACHE_PREFIX_GET_EARTHQUAKES = "earthquakes";

    private final EarthquakeService service;

    public EarthquakeRepository(EarthquakeService service) {
        this.service = service;
    }

    public Observable<List<Earthquake>> getEarthquakeData(
            String format, String eventtype, String orderby, long minmag, final int limit, String startdate) {
        Observable<List<Earthquake>> earthquakeInfoObservable = Observable.combineLatest(
                getLocalEarthquakeData(format, eventtype, orderby, minmag, limit, startdate),
                getRemoteEarthquakeData(format, eventtype, orderby, minmag, limit, startdate),
                new BiFunction<List<Earthquake>, List<Earthquake>, List<Earthquake>>() {
                    // TODO: this is wrong. fix it.
                    @Override
                    public List<Earthquake> apply(@NonNull List<Earthquake> earthquakes, @NonNull List<Earthquake> earthquakes2) throws Exception {
                        if (earthquakes.size() > 0) {
                            return earthquakes;
                        } else if (earthquakes2.size() > 0) {
                            return earthquakes2;
                        } else return new ArrayList<Earthquake>();
                    }
                }
        );
        return cacheObservable(CACHE_PREFIX_GET_EARTHQUAKES + startdate + limit + minmag, earthquakeInfoObservable);
    }

    private Observable<List<Earthquake>> getRemoteEarthquakeData(
            String format, String eventtype, String orderby, long minmag, int limit, String startdate) {
        return getRemoteEarthquakeData(format, eventtype, orderby, minmag, limit, startdate);
    }

    private Observable<List<Earthquake>> getLocalEarthquakeData(
            String format, String eventtype, String orderby, long minmag, int limit, String startdate) {
        return cacheObservable(
                CACHE_PREFIX_GET_EARTHQUAKES + startdate + limit + minmag,
                service
                        .getEarthquakeObjects(format, eventtype, orderby, minmag, limit, startdate)
                        .cache()
                        .doOnNext(new Consumer<EarthquakeObjects>() {
                            @Override
                            public void accept(@NonNull EarthquakeObjects earthquakeObjects) throws Exception {
                                if (earthquakeObjects.getFeatures().isEmpty()) {
                                    // throw exception
                                }
                            }
                        }))
                        .map(new Function<EarthquakeObjects, List<Earthquake>>() { // // Function<Input, Output>()
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
