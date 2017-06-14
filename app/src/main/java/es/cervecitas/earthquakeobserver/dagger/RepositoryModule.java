package es.cervecitas.earthquakeobserver.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.model.service.EarthquakeService;
import es.cervecitas.earthquakeobserver.model.service.ServiceConfig;
import es.cervecitas.earthquakeobserver.model.service.repository.EarthquakeRepository;

@Module
public class RepositoryModule {

    private static final String EARTHQUAKES_ENDPOINT = "http://earthquake.usgs.gov/fdsnws/event/1/";

    @Provides
    @Singleton
    EarthquakeRepository provideEarthquakeRepository() {
        EarthquakeService earthquakeService = new EarthquakeService(new ServiceConfig(EARTHQUAKES_ENDPOINT));
        return new EarthquakeRepository(earthquakeService);
    }
}
