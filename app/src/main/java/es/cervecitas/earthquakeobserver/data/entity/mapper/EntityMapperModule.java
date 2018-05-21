package es.cervecitas.earthquakeobserver.data.entity.mapper;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;
import es.cervecitas.earthquakeobserver.data.entity.EarthquakeEntity;
import es.cervecitas.earthquakeobserver.domain.Earthquake;

/**
 * Provides entity mapper dependencies.
 */
@Module
public interface EntityMapperModule {

    @Binds
    @Reusable
    EntityMapper<EarthquakeEntity, Earthquake> earthqueakeEntityMapper(EarthqueakeEntityMapper earthqueakeEntityMapper);
}
