package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.OnItemClickListener;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter.EarthquakeListPresenterModule;

@Module(includes = {
        EarthquakeListPresenterModule.class
})
public abstract class EarthquakeListFragmentModule {

    @Binds
    @PerFragment
    abstract OnItemClickListener<PresentationEarthquake> presentationEarthquakeOnItemClickListener(EarthquakeListItemClickListener earthquakeListItemClickListener);

    @Provides
    static List<PresentationEarthquake> presentationEarthquakeModels() {
        return new ArrayList<>();
    }

    @Binds
    @PerFragment
    abstract EarthquakeListView earthquakeListView(EarthquakeListFragment earthquakeListFragment);

//    @Binds
//    @PerFragment
//    abstract Fragment fragment(EarthquakeListFragment earthquakeListFragment);
}
