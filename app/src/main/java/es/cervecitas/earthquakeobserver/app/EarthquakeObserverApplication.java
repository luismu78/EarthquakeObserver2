package es.cervecitas.earthquakeobserver.app;

import android.app.Application;

import es.cervecitas.earthquakeobserver.dagger.AppComponent;
import es.cervecitas.earthquakeobserver.dagger.AppModule;
import es.cervecitas.earthquakeobserver.dagger.DaggerAppComponent;

/**
 * Created by luism on 28/04/2017.
 */

public class EarthquakeObserverApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(EarthquakeObserverApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
