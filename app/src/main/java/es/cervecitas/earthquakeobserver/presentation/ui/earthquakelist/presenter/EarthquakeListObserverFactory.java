package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.model.mapper.ModelMapperHolder;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view.EarthquakeListView;

@PerFragment
final class EarthquakeListObserverFactory {

    private final EarthquakeListView view;
    private final ModelMapperHolder modelMapperHolder;

    @Inject
    public EarthquakeListObserverFactory(EarthquakeListView view, ModelMapperHolder modelMapperHolder) {
        this.view = view;
        this.modelMapperHolder = modelMapperHolder;
    }

    EarthquakeListObserver create() {
        return new EarthquakeListObserver(view, modelMapperHolder);
    }
}
