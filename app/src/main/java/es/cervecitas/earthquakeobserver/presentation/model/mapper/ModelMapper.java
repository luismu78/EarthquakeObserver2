package es.cervecitas.earthquakeobserver.presentation.model.mapper;

import es.cervecitas.earthquakeobserver.domain.DomainObject;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationObject;

/**
 * Maps model K to V and vice versa.
 */

public interface ModelMapper<K extends PresentationObject, V extends DomainObject> {

    V map(K k);

    K map(V v);
}
