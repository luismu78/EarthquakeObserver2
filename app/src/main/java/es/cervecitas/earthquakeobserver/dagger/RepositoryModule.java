package es.cervecitas.earthquakeobserver.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.service.EarthquakeService;
import es.cervecitas.earthquakeobserver.service.ServiceConfig;
import es.cervecitas.earthquakeobserver.service.repository.BaseRepository;
import es.cervecitas.earthquakeobserver.service.repository.EarthquakeDataRepository;

@Module
public class RepositoryModule {

    private static final String EARTHQUAKE_SERVICE_ENDPOINT = "http://earthquake.usgs.gov/fdsnws/event/1/";

    @Provides
    @Singleton
    EarthquakeDataRepository provideEarthquakeDataRepository() {
        EarthquakeService earthquakeService = new EarthquakeService(new ServiceConfig(EARTHQUAKE_SERVICE_ENDPOINT));
        return new EarthquakeDataRepository(earthquakeService);
    }
}
