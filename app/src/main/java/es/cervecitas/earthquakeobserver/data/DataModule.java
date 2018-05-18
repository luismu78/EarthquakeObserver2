package es.cervecitas.earthquakeobserver.data;

import dagger.Module;
import es.cervecitas.earthquakeobserver.dagger.RepositoryModule;

/**
 * Provides data dependencies
 */
@Module(includes = {
        RepositoryModule.class
})
public abstract class DataModule {
}
