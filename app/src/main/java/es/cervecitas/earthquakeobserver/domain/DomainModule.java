package es.cervecitas.earthquakeobserver.domain;

import dagger.Module;
import es.cervecitas.earthquakeobserver.domain.executor.ExecutorModule;

/**
 * Provides Domain Dependencies
 */
@Module(includes = {
        ExecutorModule.class
})
public abstract class DomainModule {
}
