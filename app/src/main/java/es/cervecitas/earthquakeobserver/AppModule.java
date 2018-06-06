package es.cervecitas.earthquakeobserver;

import android.app.Application;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
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

    @Provides @Named("CACHE_DIR")
    @Singleton
    static File cacheDir(Application application) {
        return application.getCacheDir();
    }

    @PerActivity
    @ContributesAndroidInjector(modules = EarthquakeListActivityModule.class)
    abstract EarthquakeListActivity earthquakeListActivityInjector();
}
