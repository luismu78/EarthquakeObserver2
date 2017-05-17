package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.app.EarthquakeObserverApplication;
import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.ui.preferences.EarthquakesPreferencesActivity;

public class EarthquakesActivity extends AppCompatActivity implements EarthquakesView {

    @Inject
    EarthquakesPresenter presenter;

    @BindView(R.id.rvEarthquakes)
    RecyclerView rvEarthquakes;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.imgNoEarthquakes)
    ImageView imgNoEarthqueakes;

    @BindView(R.id.empty_view)
    TextView emptyView;

    @BindView(R.id.sub_empty_view)
    TextView subEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ((EarthquakeObserverApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        rvEarthquakes.setAdapter(new EarthquakeAdapter(this, new ArrayList<Earthquake>()));
        rvEarthquakes.setLayoutManager(new LinearLayoutManager(this));
        rvEarthquakes.setHasFixedSize(true);

        presenter.setView(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getEarthquakes();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getEarthquakes();
    }

    // EarthquakesView

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEarthquakeList(List<Earthquake> earthquakeItemList) {
        if (earthquakeItemList.size() == 0) {
            showErrorMessage();
        } else {
            hideErrorMessage();
            rvEarthquakes.setAdapter(new EarthquakeAdapter(this, earthquakeItemList));
            rvEarthquakes.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorMessage() {
        imgNoEarthqueakes.setVisibility(View.VISIBLE);
        emptyView.setText(R.string.no_data_found);
        emptyView.setVisibility(View.VISIBLE);
        subEmptyView.setText(R.string.try_later);
        subEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        imgNoEarthqueakes.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        subEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void launchEarthquakeDetail(Earthquake earthquake) {
        Uri earthquakeUri = Uri.parse(earthquake.getUrl());
        Intent showEarthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
        startActivity(showEarthquakeIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.earthquake_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, EarthquakesPreferencesActivity.class);
                startActivity(settingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
