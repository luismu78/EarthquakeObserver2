package es.cervecitas.earthquakeobserver.model.service.cache;

import android.support.v4.util.LruCache;

import java.util.concurrent.Callable;

import es.cervecitas.earthquakeobserver.model.network.EarthquakeObjects;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class EarthquakeStorageCache {

    private LruCache<String, EarthquakeObjects> cache = new LruCache<>(50); //TODO: setting o constante

    public Observable<EarthquakeObjects> getEarthquakeObjects(final String key) {
        return Observable.fromCallable(new Callable<EarthquakeObjects>() {
            @Override
            public EarthquakeObjects call() throws Exception {
                EarthquakeObjects cachedEarthquakeObjects = cache.get(key);

                if (cachedEarthquakeObjects != null) {
                    return cachedEarthquakeObjects;
                }

                throw new EarthquakeObjectsNotInCacheException();
            }
        }).subscribeOn(Schedulers.io()); // important
    }

    public void addEarthquakeObject(String key, EarthquakeObjects earthquakeObjects) {
        cache.put(key, earthquakeObjects);
    }
}
