package es.cervecitas.earthquakeobserver.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.domain.Earthquake;
import es.cervecitas.earthquakeobserver.domain.EarthquakeQueryParameters;
import es.cervecitas.earthquakeobserver.domain.repository.EarthquakeRepository;
import io.reactivex.Observable;

public class GetEarthquakeList implements UseCase<EarthquakeQueryParameters,List<Earthquake>> {

    private final EarthquakeRepository repository;

    @Inject
    GetEarthquakeList(EarthquakeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Earthquake>> execute(EarthquakeQueryParameters params) {
        return repository.getEarthquakeList(
                params.getFormat(),
                params.getEventType(),
                params.getOrderBy(),
                params.getMinMag(),
                params.getLimit(),
                params.getStartDate());
    }
}
