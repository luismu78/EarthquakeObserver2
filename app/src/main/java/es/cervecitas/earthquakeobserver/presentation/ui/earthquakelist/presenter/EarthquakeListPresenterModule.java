package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter;

import dagger.Binds;
import dagger.Module;
import es.cervecitas.earthquakeobserver.inject.PerFragment;

@Module
public abstract class EarthquakeListPresenterModule {

    @Binds
    @PerFragment
    abstract EarthquakeListPresenter earthquakeListPresenter(EarthquakeListPresenterImpl earthquakeListPresenter);
}
