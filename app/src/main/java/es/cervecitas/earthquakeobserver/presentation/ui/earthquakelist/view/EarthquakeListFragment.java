package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.AbstractLoadContentFragment;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter.EarthquakeListPresenter;
import es.cervecitas.earthquakeobserver.presentation.ui.preferences.EarthquakesPreferencesActivity;

public class EarthquakeListFragment extends AbstractLoadContentFragment<EarthquakeListPresenter> implements EarthquakeListView {

    public static final String TAG = "earthquakelist.view.EarthquakeListFragment";

    @SuppressWarnings("WeakerAccess")
    @Inject
    EarthquakeListAdapter adapter;

    @BindView(R.id.contentPane)
    RecyclerView rvEarthquakes;

    private SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_earthquake_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        //TODO: inject this
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        sharedPreferenceChangeListener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        adapter.clearPresentationEarthquakes();
                        presenter.onListEarthquakes();
                    }
                };
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        adapter.onRestoreInstanceState(savedInstanceState);

        rvEarthquakes.setAdapter(adapter);
        rvEarthquakes.setLayoutManager(
                new LinearLayoutManager(activityContext, LinearLayoutManager.VERTICAL, false));

        presenter.onListEarthquakes();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: clear caches
                presenter.onListEarthquakes();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSavedInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.earthquake_activity, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(activityContext, EarthquakesPreferencesActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void add(PresentationEarthquake presentationEarthquake) {
        adapter.addPresentationEarthquake(presentationEarthquake);
    }

    @Override
    public void show() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clear() {
        adapter.clearPresentationEarthquakes();
    }

    @Override
    public void showDetails(PresentationEarthquake presentationEarthquake) {
        Log.d("HOLA", getClass().getSimpleName() + " - showDetails - name:" + presentationEarthquake.getLocation());
    }

    @Override
    protected void onRetryButtonClicked() {
        presenter.onRetry();
    }
}
