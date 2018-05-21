package es.cervecitas.earthquakeobserver.presentation.ui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import es.cervecitas.earthquakeobserver.R;

public class EarthquakesPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_earthquake);

        Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
        bindPreferenceSummaryToValue(minMagnitude);

        Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
        bindPreferenceSummaryToValue(orderBy);

        Preference startDate = findPreference(getString(R.string.settings_filter_time_period_key));
        bindPreferenceSummaryToValue(startDate);

        Preference itemLimit = findPreference(getString(R.string.settings_earthquake_limit_key));
        bindPreferenceSummaryToValue(itemLimit);
    }

    private void bindPreferenceSummaryToValue(Preference preference) {

        preference.setOnPreferenceChangeListener(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String preferenceString;
        Boolean preferenceBoolean;

        if (preference instanceof CheckBoxPreference) {
            // Boolean preferences
            preferenceBoolean = sharedPreferences.getBoolean(preference.getKey(), false);
            onPreferenceChange(preference, preferenceBoolean);
        } else {
            // String preferences
            preferenceString = sharedPreferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);

            if (preference instanceof EditTextPreference) {
                EditTextPreference p = (EditTextPreference) preference;
                preference.setSummary(p.getText());
            }

        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
//            if (prefIndex > 0) {
            CharSequence[] labels = listPreference.getEntries();
            preference.setSummary(labels[prefIndex]);
//            }

        } else if (preference instanceof EditTextPreference) {

            if (preference.getKey().equals(getString(R.string.settings_earthquake_limit_key))) {
                int v;
                try {
                    v = Integer.parseInt(value.toString());
                } catch (NumberFormatException e) {
                    preference.setSummary(((EditTextPreference) preference).getText());
                    return false;
                }
                if (v < 10 || v > 50) {
                    preference.setSummary(((EditTextPreference) preference).getText());
                    return false;
                }

            } else if (preference.getKey().equals(getString(R.string.settings_min_magnitude_key))) {
                int v;
                try {
                    v = Integer.parseInt(value.toString());
                } catch (NumberFormatException e) {
                    preference.setSummary(((EditTextPreference) preference).getText());
                    return false;
                }
                if (v < 1 || v > 9) {
                    preference.setSummary(((EditTextPreference) preference).getText());
                    return false;
                }
            }

            preference.setSummary(stringValue);

        } else {
            preference.setSummary(stringValue);
        }

        return true;
    }
}
