package es.cervecitas.earthquakeobserver.presentation.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.inject.PerActivity;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link AppCompatActivity}.
 */
@Module
public abstract class BaseActivityModule {

    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

    @Binds
    @PerActivity
    abstract Context activityContext(AppCompatActivity activity);

    @Provides
    @PerActivity
    static Resources activityResources(Activity activity) {
        return activity.getResources();
    }

    @Provides
    @PerActivity
    static SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }

    @Provides
    @PerActivity
    static LayoutInflater layoutInflater(AppCompatActivity activity) {
        return LayoutInflater.from(activity);
    }

    @Provides
    @PerActivity
    static InputMethodManager inputMethodManager(AppCompatActivity activity) {
        return (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
