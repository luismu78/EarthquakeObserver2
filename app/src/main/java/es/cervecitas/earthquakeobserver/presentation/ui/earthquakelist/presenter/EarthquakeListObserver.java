package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter;

import java.util.List;

import es.cervecitas.earthquakeobserver.domain.Earthquake;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.model.mapper.ModelMapperHolder;
import es.cervecitas.earthquakeobserver.presentation.ui.common.presenter.AbstractLoadContentViewObserver;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view.EarthquakeListView;

final class EarthquakeListObserver extends AbstractLoadContentViewObserver<EarthquakeListView, List<Earthquake>> {

    private final ModelMapperHolder modelMapperHolder;

    EarthquakeListObserver(EarthquakeListView view, ModelMapperHolder modelMapperHolder) {
        super(view);
        this.modelMapperHolder = modelMapperHolder;
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.clear();
    }

    @Override
    public void onNext(List<Earthquake> earthquakeList) {
        for (Earthquake earthquake : earthquakeList) {
            PresentationEarthquake presentationEarthquake = modelMapperHolder.getPresentationEarthquakeMapper().map(earthquake);
            view.add(presentationEarthquake);
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        view.show();
    }
}
