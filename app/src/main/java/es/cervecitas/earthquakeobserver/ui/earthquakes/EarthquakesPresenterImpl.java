package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.app.EarthquakeObserverApplication;
import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.model.service.repository.EarthquakeDataRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class EarthquakesPresenterImpl implements EarthquakesPresenter {

    @Inject
    EarthquakeDataRepository repository;

    @Inject
    Context context;

    @Inject
    SharedPreferences preferences;

    private EarthquakesView view;

    public EarthquakesPresenterImpl(Context context) {
        ((EarthquakeObserverApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(EarthquakesView view) {
        this.view = view;
    }

    @Override
    public void getEarthquakes() {
        view.showLoading();
        view.clearEarthquakes();

        //TODO: pedir los datos primero a la cache. Si no los tiene hacer peticion REST
        // Si los datos de la cache estan caducados hacer peticion al repositorio rest

        repository.getEarthquakes(getFormat(), getEventType(), getOrderBy(), getMinMag(), getLimit(), getStartDate())
                .observeOn(AndroidSchedulers.mainThread()) // Modifies a Single to emit its item (or notify of its error) on a specified Scheduler
                .subscribe(new Consumer<Earthquake>() {
                    @Override
                    public void accept(@NonNull Earthquake earthquake) throws Exception {
                        view.displayEarthquake(earthquake);
                        view.hideLoading();
                    }
                });

//                .subscribe(new Consumer<List<Earthquake>>() { // Subscribes to a Single and provides a callback to handle the item it emits
//                    @Override
//                    public void accept(@NonNull List<Earthquake> earthquakes) throws Exception {
//                        view.displayEarthquake(earthquakes);
//                        view.hideLoading();
//                    }
//                });
    }

    private String getFormat() {
        return "geojson"; //TODO: fix magic string
    }

    private String getEventType() {
        return "earthquake"; //TODO: fix magic string
    }

    private int getLimit() {
        return Integer.parseInt(
                preferences.getString(
                        context.getString(R.string.settings_earthquake_limit_key),
                        context.getString(R.string.settings_earthquake_limit_default)
                ));
    }

    private long getMinMag() {
        return Long.parseLong(
                preferences.getString(
                        context.getString(R.string.settings_min_magnitude_key),
                        context.getString(R.string.settings_min_magnitude_default)
                ));
    }

    private String getOrderBy() {
        return preferences.getString(
                context.getString(R.string.settings_order_by_key),
                context.getString(R.string.settings_order_by_default)
        );
    }

    private String getStartDate() {
        int timePeriod = Integer.parseInt(
                preferences.getString(
                        context.getString(R.string.settings_filter_time_period_key),
                        context.getString(R.string.settings_filter_time_period_default)
                ));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -timePeriod + 1);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }
}
