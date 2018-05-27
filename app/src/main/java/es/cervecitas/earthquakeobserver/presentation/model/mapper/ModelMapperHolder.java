package es.cervecitas.earthquakeobserver.presentation.model.mapper;

import javax.inject.Inject;

import dagger.Reusable;
import es.cervecitas.earthquakeobserver.domain.Earthquake;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;

/**
 * Holds instances of {@link ModelMapper}s
 */
@Reusable
public final class ModelMapperHolder {

    private final ModelMapper<PresentationEarthquake, Earthquake> presentationEarthquakeMapper;

    @Inject
    public ModelMapperHolder(ModelMapper<PresentationEarthquake, Earthquake> presentationEarthquakeMapper) {
        this.presentationEarthquakeMapper = presentationEarthquakeMapper;
    }

    public ModelMapper<PresentationEarthquake, Earthquake> getPresentationEarthquakeMapper() {
        return presentationEarthquakeMapper;
    }
}
