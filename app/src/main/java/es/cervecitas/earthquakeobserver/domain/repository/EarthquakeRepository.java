package es.cervecitas.earthquakeobserver.domain.repository;

import java.util.List;

import es.cervecitas.earthquakeobserver.domain.Earthquake;
import io.reactivex.Observable;

/**
 * repository for retrieving Earthqueake data
 */
public interface EarthquakeRepository {

    // Earthquake
    Observable<Earthquake> getEarthquake();

    // List<Earthquake>
    Observable<List<Earthquake>> getEarthquakeList(final String format,
                                                   final String eventtype,
                                                   final String orderby,
                                                   final long minmag,
                                                   final int limit,
                                                   final String startdate);
}
