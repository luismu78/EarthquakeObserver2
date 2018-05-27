package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.OnItemClickListener;
import es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.presenter.EarthquakeListPresenter;

@PerFragment
public class EarthquakeListItemClickListener implements OnItemClickListener<PresentationEarthquake> {

    private final EarthquakeListPresenter presenter;

    @Inject
    public EarthquakeListItemClickListener(EarthquakeListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClicked(PresentationEarthquake item) {
        presenter.onEarthquakeClicked(item);
    }
}
