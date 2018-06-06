package es.cervecitas.earthquakeobserver.presentation.ui.preferences;

import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import es.cervecitas.earthquakeobserver.R;

public class EarthquakesPreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_earthquake, rootKey);
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
