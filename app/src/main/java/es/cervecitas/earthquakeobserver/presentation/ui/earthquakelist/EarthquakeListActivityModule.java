package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import es.cervecitas.earthquakeobserver.inject.PerActivity;
import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivityModule;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view.EarthquakeListFragment;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view.EarthquakeListFragmentModule;

@Module(includes = {
        BaseActivityModule.class
})
public abstract class EarthquakeListActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = EarthquakeListFragmentModule.class)
    abstract EarthquakeListFragment earthquakeListFragmentInjector();

    @Binds
    @PerActivity
    abstract AppCompatActivity activity(EarthquakeListActivity earthquakeListActivity);


}
