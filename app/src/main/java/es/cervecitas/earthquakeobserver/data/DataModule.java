package es.cervecitas.earthquakeobserver.data;

import dagger.Module;
import es.cervecitas.earthquakeobserver.data.entity.EntityModule;
import es.cervecitas.earthquakeobserver.data.repository.RepositoryModule;

/**
 * Provides data dependencies
 */
@Module(includes = {
        EntityModule.class,
        RepositoryModule.class
})
public abstract class DataModule {
}
