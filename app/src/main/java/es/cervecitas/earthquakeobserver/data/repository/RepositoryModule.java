package es.cervecitas.earthquakeobserver.data.repository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;
import es.cervecitas.earthquakeobserver.domain.repository.EarthquakeRepository;

/**
 * Provides repository dependencies.
 */
@Module
public abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract EarthquakeRepository earthquakeRepository(EarthquakeEntityRepository earthquakeEntityRepository);
}
