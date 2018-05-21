package es.cervecitas.earthquakeobserver.data.entity;

import dagger.Module;
import es.cervecitas.earthquakeobserver.data.entity.mapper.EntityMapperModule;

/**
 * Provides entity dependencies.
 */
@Module(includes = {
        EntityMapperModule.class
})
public abstract class EntityModule {
}
