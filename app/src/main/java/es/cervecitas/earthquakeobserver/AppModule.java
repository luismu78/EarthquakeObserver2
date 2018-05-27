package es.cervecitas.earthquakeobserver;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import es.cervecitas.earthquakeobserver.data.net.NetworkModule;
import es.cervecitas.earthquakeobserver.inject.PerActivity;
import es.cervecitas.earthquakeobserver.presentation.PresentationModule;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.EarthquakeListActivity;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.EarthquakeListActivityModule;

@Module(includes = {
        AndroidSupportInjectionModule.class,
        PresentationModule.class,
        NetworkModule.class
})
abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(App app);

    @PerActivity
    @ContributesAndroidInjector(modules = EarthquakeListActivityModule.class)
    abstract EarthquakeListActivity earthquakeListActivityInjector();
}
