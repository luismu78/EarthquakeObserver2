package es.cervecitas.earthquakeobserver.data;

import dagger.Module;
import es.cervecitas.earthquakeobserver.dagger.RepositoryModule;
import es.cervecitas.earthquakeobserver.data.entity.EntityModule;

/**
 * Provides data dependencies
 */
@Module(includes = {
        RepositoryModule.class,
        EntityModule.class
})
public abstract class DataModule {
}
