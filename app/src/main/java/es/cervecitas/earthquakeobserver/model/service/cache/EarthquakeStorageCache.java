package es.cervecitas.earthquakeobserver.model.service.cache;

import android.support.v4.util.LruCache;
import android.util.Log;

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
                Log.d("HOLA", "cache.get(key) - key --> " + key);
                EarthquakeObjects cachedEarthquakeObjects = cache.get(key);

                if (cachedEarthquakeObjects != null) {
                    Log.d("HOLA", "cache hit");
                    return cachedEarthquakeObjects;
                }

                Log.d("HOLA", "cache fail");
                throw new EarthquakeObjectsNotInCacheException();
            }
        }).subscribeOn(Schedulers.io()); // important
    }

    public void addEarthquakeObject(String key, EarthquakeObjects earthquakeObjects) {
        cache.put(key, earthquakeObjects);
    }
}
