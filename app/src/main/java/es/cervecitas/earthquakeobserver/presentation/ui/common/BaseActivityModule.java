package es.cervecitas.earthquakeobserver.presentation.ui.common;

import dagger.Module;
import android.support.v7.app.AppCompatActivity;

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of {@link AppCompatActivity}.
 */
@Module
public abstract class BaseActivityModule {

    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";
}
