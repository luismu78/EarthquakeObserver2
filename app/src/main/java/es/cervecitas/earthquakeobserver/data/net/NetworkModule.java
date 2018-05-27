package es.cervecitas.earthquakeobserver.data.net;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides network dependencies.
 */
@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static USGSApi usgsApi(EarthquakeDataServiceFactory earthquakeDataServiceFactory) {
        return earthquakeDataServiceFactory.create(USGSApi.class);
    }
}
