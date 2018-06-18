package es.cervecitas.earthquakeobserver.presentation.ui.preferences;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.presentation.ui.common.BaseActivity;

public class EarthquakesPreferencesActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_earthquake_settings);

        Objects.requireNonNull(this.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(getString(R.string.action_settings));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
