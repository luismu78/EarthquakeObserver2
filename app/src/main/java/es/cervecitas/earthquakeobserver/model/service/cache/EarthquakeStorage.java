package es.cervecitas.earthquakeobserver.model.service.cache;

import android.util.Log;

import es.cervecitas.earthquakeobserver.model.network.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.model.service.EarthquakeService;
import io.reactivex.Notification;
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

    public Observable<EarthquakeObjects> getEarthquakeObjects( //TODO: usar esto
            final String format,
            final String eventType,
            final String orderBy,
            final long minMag,
            final int limit,
            final String startDate) {

        final String key = getKey(format, eventType, orderBy, minMag, limit, startDate);

        Log.d("HOLA", "key: " + key);

        return cache
                .getEarthquakeObjects(key)
//                .doOnEach(new Consumer<Notification<EarthquakeObjects>>() {
//                    @Override
//                    public void accept(@NonNull Notification<EarthquakeObjects> earthquakeObjectsNotification) throws Exception {
//                        Log.d("HOLA", "Notification error: " + earthquakeObjectsNotification.getError().toString());
//                        Log.d("HOLA", "Notification value: " + earthquakeObjectsNotification.getValue().toString());
//                    }
//                })
                .doOnEach(new Consumer<Notification<EarthquakeObjects>>() {
                    @Override
                    public void accept(@NonNull Notification<EarthquakeObjects> earthquakeObjectsNotification) throws Exception {
                        Log.d("HOLA", getClass().getSimpleName() + " - Error: " + earthquakeObjectsNotification.getError().toString());
                        Log.d("HOLA", getClass().getSimpleName() + " - Value: " + earthquakeObjectsNotification.getValue().toString());
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends EarthquakeObjects>>() {
                    @Override
                    public ObservableSource<? extends EarthquakeObjects> apply(@NonNull Throwable throwable) throws Exception {

                        Log.d("HOLA", getClass().getSimpleName() + " onErrorResumeNext ");

                        return earthquakeService
                                .getEarthquakeObjects(format, eventType, orderBy, minMag, limit, startDate)
                                .toObservable()
                                .doOnEach(new Consumer<Notification<EarthquakeObjects>>() {
                                    @Override
                                    public void accept(@NonNull Notification<EarthquakeObjects> earthquakeObjectsNotification) throws Exception {
                                        Log.d("HOLA", getClass().getSimpleName() + " - earthquakeService - errors: " + earthquakeObjectsNotification.getError());
                                        Log.d("HOLA", getClass().getSimpleName() + " - earthquakeService - value: " + earthquakeObjectsNotification.getValue());
                                    }
                                })
                                .doOnNext(new Consumer<EarthquakeObjects>() {
                                    @Override
                                    public void accept(@NonNull EarthquakeObjects earthquakeObjects) throws Exception {
                                        Log.d("HOLA", "getEarthquakeObjects - onNext");
                                        cache.addEarthquakeObject(key, earthquakeObjects);
                                    }
                                })
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        Log.d("HOLA", "getEarthquakeObjects - onComplete");

                                    }
                                });
//                                .doOnSuccess(new Consumer<EarthquakeObjects>() {
//                                    @Override
//                                    public void accept(@NonNull EarthquakeObjects earthquakeObjects) throws Exception {
//                                        cache.addEarthquakeObject(key, earthquakeObjects);
//                                    }
//                                });
                    }
                });
    }

    private String getKey(String format, String eventType, String orderBy, long minMag, int limit, String startDate) {
        return startDate + orderBy + minMag + limit;
    }
}
