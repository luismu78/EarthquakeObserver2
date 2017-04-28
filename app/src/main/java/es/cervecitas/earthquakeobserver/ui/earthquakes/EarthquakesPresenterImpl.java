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

        earthquakeEventAPI.getEarthquakeObjects(getFormat(), getEventType(), getOrderBy(), getMinMag(), getLimit(), getStartDate())
                .enqueue(new Callback<EarthquakeObjects>() {
                    @Override
                    public void onResponse(Call<EarthquakeObjects> call, Response<EarthquakeObjects> response) {
                        if (response.code() != 200) {
                            view.showErrorMessage();
                        } else {
                            List<Earthquake> earthquakeList = new ArrayList<>();

                            for (Feature feature : response.body().getFeatures()) {
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

                            view.showEarthquakeList(earthquakeList);
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<EarthquakeObjects> call, Throwable t) {
                        view.showErrorMessage();
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
