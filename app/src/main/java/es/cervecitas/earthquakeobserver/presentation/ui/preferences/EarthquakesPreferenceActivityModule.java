package es.cervecitas.earthquakeobserver.presentation.ui.preferences;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import es.cervecitas.earthquakeobserver.inject.PerActivity;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivityModule;

@Module(includes = {
        BaseActivityModule.class
})
public abstract class EarthquakesPreferenceActivityModule {

    @Binds
    @PerActivity
    abstract AppCompatActivity activity(EarthquakesPreferencesActivity earthquakesPreferencesActivity);
}
