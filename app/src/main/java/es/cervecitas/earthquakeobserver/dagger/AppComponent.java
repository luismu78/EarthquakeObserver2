package es.cervecitas.earthquakeobserver.dagger;

import javax.inject.Singleton;

import dagger.Component;
import es.cervecitas.earthquakeobserver.ui.earthquakes.EarthquakesActivity;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class}) // used to connect objects to their dependencies, typically by use of overridden inject() methods
public interface AppComponent {

    void inject(EarthquakesActivity target); // means: EarthquakeActivity class will require injection from AppComponent
}
