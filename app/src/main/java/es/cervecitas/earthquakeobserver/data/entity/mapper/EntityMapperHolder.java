package es.cervecitas.earthquakeobserver.data.entity.mapper;

import javax.inject.Inject;

import dagger.Reusable;
import es.cervecitas.earthquakeobserver.data.entity.EarthquakeEntity;
import es.cervecitas.earthquakeobserver.domain.Earthquake;

/**
 * Holds instances of {@link EntityMapper}s.
 */
@Reusable
public final class EntityMapperHolder {

    private final EntityMapper<EarthquakeEntity, Earthquake> earthquakeEntityMapper;

    @Inject
    public EntityMapperHolder(EntityMapper<EarthquakeEntity, Earthquake> earthquakeEntityMapper) {
        this.earthquakeEntityMapper = earthquakeEntityMapper;
    }

    public EntityMapper<EarthquakeEntity, Earthquake> getEarthquakeEntityMapper() {
        return earthquakeEntityMapper;
    }
}
