package es.cervecitas.earthquakeobserver.presentation.ui.earthquakes;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.model.Earthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivity;
import es.cervecitas.earthquakeobserver.presentation.ui.preferences.EarthquakesPreferencesActivity;

public class EarthquakesActivity extends BaseActivity implements EarthquakesView {

    @Inject
    EarthquakesPresenter presenter;

    @BindView(R.id.rvEarthquakes)
    RecyclerView rvEarthquakes;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.imgNoEarthquakes)
    ImageView imgNoEarthqueakes;

    @BindView(R.id.empty_view)
    TextView emptyView;

    @BindView(R.id.sub_empty_view)
    TextView subEmptyView;

    ArrayList<Earthquake> visibleEarthquakes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

//        ((EarthquakeObserverApplication) getApplication()).getAppComponent().inject(this);

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
    public void displayEarthquake(Earthquake earthquake) {
        visibleEarthquakes.add(earthquake);
        rvEarthquakes.setAdapter(new EarthquakeAdapter(this, visibleEarthquakes));
        rvEarthquakes.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void clearEarthquakes() {
        visibleEarthquakes = new ArrayList<>();
        rvEarthquakes.setAdapter(new EarthquakeAdapter(this, visibleEarthquakes));
        rvEarthquakes.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String title, String subTitle, boolean showLogo) {

        llNoData.setVisibility(View.VISIBLE);

        if (showLogo) {
            imgNoEarthqueakes.setVisibility(View.VISIBLE);
        } else {
            imgNoEarthqueakes.setVisibility(View.GONE);
        }

        if (title != null) {
            emptyView.setText(title);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }

        if (subTitle != null) {
            subEmptyView.setText(subTitle);
            subEmptyView.setVisibility(View.VISIBLE);
        } else {
            subEmptyView.setVisibility(View.GONE);
        }

    }

    @Override
    public void hideErrorMessage() {
        llNoData.setVisibility(View.GONE);
//        imgNoEarthqueakes.setVisibility(View.GONE);
//        emptyView.setVisibility(View.GONE);
//        subEmptyView.setVisibility(View.GONE);
    }

    @Override
    public int numberOfEarthquakes() {
        return rvEarthquakes.getAdapter().getItemCount();
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