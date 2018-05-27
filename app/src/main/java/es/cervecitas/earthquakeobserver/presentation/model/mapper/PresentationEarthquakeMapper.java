package es.cervecitas.earthquakeobserver.presentation.model.mapper;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.domain.Earthquake;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;

public class PresentationEarthquakeMapper implements ModelMapper<PresentationEarthquake, Earthquake> {

    @Inject
    public PresentationEarthquakeMapper() {

    }

    @Override
    public Earthquake map(PresentationEarthquake presentationEarthquake) {
        return new Earthquake.Builder()
                .location(presentationEarthquake.getLocation())
                .magnitude(presentationEarthquake.getMagnitude())
                .url(presentationEarthquake.getUrl())
                .dateTime(presentationEarthquake.getDateTime())
                .build();
    }

    @Override
    public PresentationEarthquake map(Earthquake earthquake) {
        return new PresentationEarthquake.Builder()
                .location(earthquake.getLocation())
                .magnitude(earthquake.getMagnitude())
                .url(earthquake.getUrl())
                .dateTime(earthquake.getDateTime())
                .build();
    }
}
