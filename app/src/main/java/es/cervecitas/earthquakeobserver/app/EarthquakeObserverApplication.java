package es.cervecitas.earthquakeobserver.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

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

        initLeakCannary();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(EarthquakeObserverApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    private void initLeakCannary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
    }
}
