package es.cervecitas.earthquakeobserver.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakes.EarthquakesPresenter;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakes.EarthquakesPresenterImpl;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    EarthquakesPresenter provideEarthquakesPresenter(Context context) {
        return new EarthquakesPresenterImpl(context);
    }
}
