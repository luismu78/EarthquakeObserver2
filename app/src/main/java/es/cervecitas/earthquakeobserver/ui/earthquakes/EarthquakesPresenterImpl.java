package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.app.EarthquakeObserverApplication;
import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.model.network.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.model.network.Feature;
import es.cervecitas.earthquakeobserver.model.service.cache.EarthquakeStorage;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class EarthquakesPresenterImpl implements EarthquakesPresenter {

    @Inject
    EarthquakeStorage storage;

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

        storage
                .getEarthquakeObjects(getFormat(), getEventType(), getOrderBy(), getMinMag(), getLimit(), getStartDate())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<EarthquakeObjects, List<Earthquake>>() {
                         @Override
                         public List<Earthquake> apply(@NonNull EarthquakeObjects earthquakeObjects) throws Exception {
                             List<Earthquake> earthquakeList = new ArrayList<>();

                             for (Feature feature : earthquakeObjects.getFeatures()) {
                                 // magnitude
                                 Double magnitude = feature.getProperties().getMag();
                                 // location
                                 String location = feature.getProperties().getPlace();
                                 // date & time
                                 Calendar calDate = Calendar.getInstance();
                                 calDate.setTimeInMillis(feature.getProperties().getTime());
                                 // url
                                 String url = feature.getProperties().getUrl();

                                 earthquakeList.add(new Earthquake(magnitude, location, calDate.getTime().getTime(), url));
                             }

                             return earthquakeList;
                         }
                     }
                )
                .flatMap(new Function<List<Earthquake>, Observable<Earthquake>>() {
                    @Override
                    public Observable<Earthquake> apply(@NonNull List<Earthquake> earthquakes) throws Exception {
                        return Observable.fromIterable(earthquakes);
                    }
                })
                .subscribe(new Observer<Earthquake>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Earthquake earthquake) {
                        view.displayEarthquake(earthquake);
                        view.hideErrorMessage();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showErrorMessage();
                        view.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
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
