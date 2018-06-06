package es.cervecitas.earthquakeobserver.domain.interactors;

import io.reactivex.Observable;

/**
 * Use cases are interactors in terms of "clean architecture", which encapsulate a focused unit of
 * work. These do not, by themselves, determine where (what thread) the work will be done.
 *
 * @param <K> the type of the input passed as a parameter to {@link #execute(K)}
 * @param <V> the type of the item emitted by the {@link Observable} returned by {@link #execute(V)}
 */

public interface UseCase<K, V> {

    Observable<V> execute(K params);
}
