package es.cervecitas.earthquakeobserver.data.net;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static USGSApi usgsApi(DataServiceFactory dataServiceFactory) {
        return dataServiceFactory.create(USGSApi.class);
    }
}
