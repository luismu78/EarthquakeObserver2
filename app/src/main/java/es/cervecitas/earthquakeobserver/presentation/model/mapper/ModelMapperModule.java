package es.cervecitas.earthquakeobserver.presentation.model.mapper;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;
import es.cervecitas.earthquakeobserver.domain.Earthquake;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;

/**
 * Provides model mapper dependencies.
 */
@Module
public abstract class ModelMapperModule {

    @Binds
    @Reusable
    abstract ModelMapper<PresentationEarthquake, Earthquake> earthquakeModelMapper(PresentationEarthquakeMapper presentationEarthquakeMapper);
}
