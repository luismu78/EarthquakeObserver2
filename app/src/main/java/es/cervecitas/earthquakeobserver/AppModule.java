package es.cervecitas.earthquakeobserver;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {
        AndroidSupportInjectionModule.class
})
abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(App app);
}
