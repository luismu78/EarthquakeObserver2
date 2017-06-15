package es.cervecitas.earthquakeobserver.model.service.repository;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import io.reactivex.Observable;

abstract class BaseRepository {

    private LruCache<String, Observable<?>> apiObservables = createLruCache();

    @NonNull
    private LruCache<String, Observable<?>> createLruCache() {
        return new LruCache<>(10);
    }

    <T> Observable<T> cacheObservable(String startDateLimitMinMag, Observable<T> observable) {

        // TODO: no cachear el observable
        // TODO: cachear el resultado del observable

        Observable<T> cachedObservable = (Observable<T>) apiObservables.get(startDateLimitMinMag);
        if (cachedObservable != null) {
            return cachedObservable;
        }

        cachedObservable = observable;
        updateCache(startDateLimitMinMag, cachedObservable);

        return cachedObservable;
    }

    private <T> void updateCache(String startDateLimitMinMag, Observable<T> observable) {
        apiObservables.put(startDateLimitMinMag, observable);
    }

    //TODO: find usage
    public <T> void removeCache(String startDateLimitMinMag) {
        apiObservables.remove(startDateLimitMinMag);
    }

    //TODO: find usage
    public void clearCache() {
        apiObservables = createLruCache();
    }
}
