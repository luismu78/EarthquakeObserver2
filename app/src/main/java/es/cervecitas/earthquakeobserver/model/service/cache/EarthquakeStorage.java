package es.cervecitas.earthquakeobserver.model.service.cache;

import es.cervecitas.earthquakeobserver.model.network.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.model.service.EarthquakeService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class EarthquakeStorage {

    private EarthquakeService earthquakeService;
    private EarthquakeStorageCache cache = new EarthquakeStorageCache();

    public EarthquakeStorage(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    public Observable<EarthquakeObjects> getEarthquakeObjects(
            final String format,
            final String eventType,
            final String orderBy,
            final long minMag,
            final int limit,
            final String startDate) {

        final String key = getKey(orderBy, minMag, limit, startDate);

        return cache
                .getEarthquakeObjects(key)
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends EarthquakeObjects>>() {
                    @Override
                    public ObservableSource<? extends EarthquakeObjects> apply(@NonNull Throwable throwable) throws Exception {
                        return earthquakeService
                                .getEarthquakeObjects(format, eventType, orderBy, minMag, limit, startDate)
                                .toObservable()
                                .doOnNext(new Consumer<EarthquakeObjects>() {
                                    @Override
                                    public void accept(@NonNull EarthquakeObjects earthquakeObjects) throws Exception {
                                        cache.addEarthquakeObject(key, earthquakeObjects);
                                    }
                                })
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Exception {
                                    }
                                });
                    }
                });
    }

    private String getKey(String orderBy, long minMag, int limit, String startDate) {
        return startDate + orderBy + minMag + limit;
    }
}
