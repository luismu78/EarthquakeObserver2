package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Objects;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivity;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view.EarthquakeListFragment;

public class EarthquakeListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setLogo(R.drawable.nav_logo);

        if (getSupportFragmentManager().findFragmentByTag(EarthquakeListFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, new EarthquakeListFragment(), EarthquakeListFragment.TAG)
                    .commit();
        }

    }
}
