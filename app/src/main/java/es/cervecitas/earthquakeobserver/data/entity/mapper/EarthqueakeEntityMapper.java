package es.cervecitas.earthquakeobserver.data.entity.mapper;

import javax.inject.Inject;

import dagger.Reusable;
import es.cervecitas.earthquakeobserver.data.entity.EarthquakeEntity;
import es.cervecitas.earthquakeobserver.domain.Earthquake;

@Reusable
public class EarthqueakeEntityMapper implements EntityMapper<EarthquakeEntity, Earthquake> {

    @Inject
    public EarthqueakeEntityMapper() {

    }

    @Override
    public Earthquake map(EarthquakeEntity earthquakeEntity) {
        return new Earthquake.Builder()
                .location(earthquakeEntity.getLocation())
                .magnitude(earthquakeEntity.getMagnitude())
                .dateTime(earthquakeEntity.getCalendar())
                .url(earthquakeEntity.getUrl())
                .build();
    }

    @Override
    public EarthquakeEntity map(Earthquake earthquake) {
        return new EarthquakeEntity.Builder()
                .location(earthquake.getLocation())
                .magnitude(earthquake.getMagnitude())
                .calendar(earthquake.getDateTime())
                .url(earthquake.getUrl())
                .build();
    }
}
