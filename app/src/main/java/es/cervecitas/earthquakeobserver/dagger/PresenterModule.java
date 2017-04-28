package es.cervecitas.earthquakeobserver.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.ui.earthquakes.EarthquakesPresenter;
import es.cervecitas.earthquakeobserver.ui.earthquakes.EarthquakesPresenterImpl;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    EarthquakesPresenter provideEarthquakesPresenter() {
        return new EarthquakesPresenterImpl();
    }
}
