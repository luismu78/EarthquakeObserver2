package es.cervecitas.earthquakeobserver.presentation;

import dagger.Module;
import es.cervecitas.earthquakeobserver.presentation.model.ModelModule;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivityModule;

@Module(includes = {
        ModelModule.class,
        BaseActivityModule.class
})
public abstract class PresentationModule {
}
