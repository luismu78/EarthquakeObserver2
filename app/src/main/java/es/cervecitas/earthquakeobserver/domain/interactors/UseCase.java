package es.cervecitas.earthquakeobserver.domain.interactors;

import io.reactivex.Observable;

public interface UseCase<K, V> {

    Observable<V> execute(K params);
}
