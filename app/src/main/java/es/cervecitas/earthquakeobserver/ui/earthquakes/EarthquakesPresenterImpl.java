package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.app.EarthquakeObserverApplication;
import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.network.EarthquakeEventAPI;
import es.cervecitas.earthquakeobserver.network.model.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.network.model.Feature;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakesPresenterImpl implements EarthquakesPresenter {

    @Inject
    EarthquakeEventAPI earthquakeEventAPI;

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

        earthquakeEventAPI
                .getEarthquakeObjects(getFormat(), getEventType(), getOrderBy(), getMinMag(), getLimit(), getStartDate())
                .subscribeOn(Schedulers.io()) // Asynchronously subscribes subscribers to this Single on the specified scheduler
                .observeOn(Schedulers.io()) // Modifies a Single to emit its item (or notify of its error) on a specified Scheduler
                .map(new Function<EarthquakeObjects, List<Earthquake>>() { // // Function<Input, Output>()
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
                })
                .observeOn(AndroidSchedulers.mainThread()) // Modifies a Single to emit its item (or notify of its error) on a specified Scheduler
                .subscribe(new Consumer<List<Earthquake>>() { // Subscribes to a Single and provides a callback to handle the item it emits
                    @Override
                    public void accept(@NonNull List<Earthquake> earthquakes) throws Exception {
                        view.showEarthquakeList(earthquakes);
                        view.hideLoading();
                    }
                });
    }

    private String getFormat() {
        return "geojson";
    }

    private String getEventType() {
        return "earthquake";
    }

    private int getLimit() {
        return 20;
    }

    private long getMinMag() {
        return 6;
    }

    private String getOrderBy() {
        return "time";
    }

    private String getStartDate() {
        int timePeriod = 2;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -timePeriod + 1);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }
}
