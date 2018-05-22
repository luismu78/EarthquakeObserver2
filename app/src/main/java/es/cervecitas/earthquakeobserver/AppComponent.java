package es.cervecitas.earthquakeobserver;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import es.cervecitas.earthquakeobserver.data.DataModule;
import es.cervecitas.earthquakeobserver.domain.DomainModule;

@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class,
        DomainModule.class
})
interface AppComponent extends AndroidInjector<App> {

    /**
     * Builder for this component
     */
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {

    }
}
