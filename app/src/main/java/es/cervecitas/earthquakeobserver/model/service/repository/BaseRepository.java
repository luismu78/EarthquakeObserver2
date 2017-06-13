package es.cervecitas.earthquakeobserver.model.service.repository;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import es.cervecitas.earthquakeobserver.model.Earthquake;
import io.reactivex.Observable;

abstract class BaseRepository {

    private LruCache<Earthquake, Observable<?>> apiObservables = createLruCache();

    @NonNull
    private LruCache<Earthquake, Observable<?>> createLruCache() {
        return new LruCache<>(50);
    }

    <T> Observable<T> cacheObservable(Earthquake earthquake, Observable<T> observable) {

        Observable<T> cachedObservable = (Observable<T>) apiObservables.get(earthquake);
        if (cachedObservable != null) {
            return cachedObservable;
        }

        cachedObservable = observable;
        updateCache(earthquake, cachedObservable);

        return cachedObservable;
    }

    private <T> void updateCache(Earthquake earthquake, Observable<T> observable) {
        apiObservables.put(earthquake, observable);
    }

    public <T> void removeCache(Earthquake earthquake) {
        apiObservables.remove(earthquake);
    }

    public void clearCache() {
        apiObservables = createLruCache();
    }
}
