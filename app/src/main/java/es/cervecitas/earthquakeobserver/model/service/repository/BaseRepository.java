package es.cervecitas.earthquakeobserver.model.service.repository;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import es.cervecitas.earthquakeobserver.model.Earthquake;
import io.reactivex.Observable;

abstract class BaseRepository {

    private LruCache<Long, Observable<?>> apiObservables = createLruCache();

    @NonNull
    private LruCache<Long, Observable<?>> createLruCache() {
        return new LruCache<>(50);
    }

    <T> Observable<T> cacheObservable(Long timestamp, Observable<T> observable) {

        Observable<T> cachedObservable = (Observable<T>) apiObservables.get(timestamp);
        if (cachedObservable != null) {
            return cachedObservable;
        }

        cachedObservable = observable;
        updateCache(timestamp, cachedObservable);

        return cachedObservable;
    }

    private <T> void updateCache(Long timestamp, Observable<T> observable) {
        apiObservables.put(timestamp, observable);
    }

    public <T> void removeCache(Long timestamp) {
        apiObservables.remove(timestamp);
    }

    public void clearCache() {
        apiObservables = createLruCache();
    }
}
