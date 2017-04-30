package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.app.EarthquakeObserverApplication;
import es.cervecitas.earthquakeobserver.model.Earthquake;

public class EarthquakesActivity extends AppCompatActivity implements EarthquakesView {

    @Inject
    EarthquakesPresenter presenter;

    @BindView(R.id.rvEarthquakes)
    RecyclerView rvEarthquakes;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ((EarthquakeObserverApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        rvEarthquakes.setLayoutManager(new LinearLayoutManager(this));

        presenter.setView(this);
        presenter.getEarthquakes();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getEarthquakes();
            }
        });
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
        rvEarthquakes.setAdapter(new EarthquakeAdapter(this, earthquakeItemList));
        rvEarthquakes.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {
        //TODO: move to Log
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchEarthquakeDetail(Earthquake earthquake) {
        Uri earthquakeUri = Uri.parse(earthquake.getUrl());
        Intent showEarthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
        startActivity(showEarthquakeIntent);
    }

}