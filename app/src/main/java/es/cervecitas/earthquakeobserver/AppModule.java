package es.cervecitas.earthquakeobserver;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import es.cervecitas.earthquakeobserver.presentation.PresentationModule;

@Module(includes = {
        AndroidSupportInjectionModule.class,
        PresentationModule.class
})
abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(App app);
}
