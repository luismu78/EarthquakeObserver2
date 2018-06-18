package es.cervecitas.earthquakeobserver.presentation.ui.common.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.inject.PerFragment;

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of {@link Fragment}.
 */
@Module
public abstract class BaseFragmentModule {

    private static final String CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager";

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    static FragmentManager childFragmentManager(Fragment fragment) {
        return fragment.getChildFragmentManager();
    }
}
