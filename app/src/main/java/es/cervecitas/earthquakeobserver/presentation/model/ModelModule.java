package es.cervecitas.earthquakeobserver.presentation.model;

import dagger.Module;
import es.cervecitas.earthquakeobserver.presentation.model.mapper.ModelMapperModule;

@Module(includes = {
        ModelMapperModule.class
})
public abstract class ModelModule {
}
