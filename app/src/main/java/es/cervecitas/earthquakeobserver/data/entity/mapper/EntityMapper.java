package es.cervecitas.earthquakeobserver.data.entity.mapper;

import es.cervecitas.earthquakeobserver.data.entity.Entity;
import es.cervecitas.earthquakeobserver.domain.DomainObject;

/**
 * Maps entity K to V and vice versa.
 *
 * @param <K> the type of the {@link Entity}
 * @param <V> the type of the {@link DomainObject}
 */
public interface EntityMapper<K extends Entity, V extends DomainObject> {

    V map(K k);

    K map(V v);
}
