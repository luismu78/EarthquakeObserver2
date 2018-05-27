package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

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

        if (getSupportFragmentManager().findFragmentByTag(EarthquakeListFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, new EarthquakeListFragment(), EarthquakeListFragment.TAG)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
