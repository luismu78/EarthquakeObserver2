package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.domain.EarthquakeQueryParameters;
import es.cervecitas.earthquakeobserver.domain.executor.UseCaseHandler;
import es.cervecitas.earthquakeobserver.domain.interactors.GetEarthquakeList;
import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.presenter.BaseUseCasePresenter;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view.EarthquakeListView;

@PerFragment
final class EarthquakeListPresenterImpl extends BaseUseCasePresenter<EarthquakeListView> implements EarthquakeListPresenter {

    @SuppressWarnings("WeakerAccess")
    @Inject
    Context activityContext;

    private final GetEarthquakeList getEarthquakeList;
    private final EarthquakeListObserverFactory observerFactory;

    private SharedPreferences sharedPreferences;

    @Inject
    protected EarthquakeListPresenterImpl(EarthquakeListView view,
                                          UseCaseHandler useCaseHandler,
                                          GetEarthquakeList getEarthquakeList,
                                          EarthquakeListObserverFactory observerFactory) {
        super(view, useCaseHandler);
        this.getEarthquakeList = getEarthquakeList;
        this.observerFactory = observerFactory;
    }

    @Override
    public void onListEarthquakes() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activityContext);

        EarthquakeQueryParameters parameters = new EarthquakeQueryParameters.Builder()
                .format(getFormat())
                .eventType(getEventType())
                .orderBy(getOrderBy())
                .minMag(getMinMag())
                .limit(getLimit())
                .startDate(getStartDate())
                .build();

        clearUseCases();
        useCaseHandler.execute(getEarthquakeList, parameters, observerFactory.create());
    }

    @SuppressWarnings("SameReturnValue")
    private String getFormat() {
        return "geojson"; //TODO: fix magic string
    }


    @SuppressWarnings("SameReturnValue")
    private String getEventType() {
        return "earthquake"; //TODO: fix magic string
    }

    private int getLimit() {
        return Integer.parseInt(
                sharedPreferences.getString(
                        activityContext.getString(R.string.settings_earthquake_limit_key),
                        activityContext.getString(R.string.settings_earthquake_limit_default)
                ));
    }

    private long getMinMag() {
        return Long.parseLong(
                sharedPreferences.getString(
                        activityContext.getString(R.string.settings_min_magnitude_key),
                        activityContext.getString(R.string.settings_min_magnitude_default)
                ));
    }

    private String getOrderBy() {
        return sharedPreferences.getString(
                activityContext.getString(R.string.settings_order_by_key),
                activityContext.getString(R.string.settings_order_by_default)
        );
    }

    private String getStartDate() {
        int timePeriod = Integer.parseInt(
                sharedPreferences.getString(
                        activityContext.getString(R.string.settings_filter_time_period_key),
                        activityContext.getString(R.string.settings_filter_time_period_default)
                ));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -timePeriod + 1);
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onRetry() {
        Log.d("HOLA", getClass().getSimpleName() + " - onRetry");
    }

    @Override
    public void onEarthquakeClicked(PresentationEarthquake presentationEarthquake) {
        Uri earthquakeUri = Uri.parse(presentationEarthquake.getUrl());
        Intent showEarthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
        activityContext.startActivity(showEarthquakeIntent);
    }
}
