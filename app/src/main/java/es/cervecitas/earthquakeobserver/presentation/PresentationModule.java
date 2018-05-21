package es.cervecitas.earthquakeobserver.presentation;

import dagger.Module;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivityModule;

/**
 * Provides presentation dependencies.
 */
@Module(includes = {
        BaseActivityModule.class
})
public abstract class PresentationModule {
}
