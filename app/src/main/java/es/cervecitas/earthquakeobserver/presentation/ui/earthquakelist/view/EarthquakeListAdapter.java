package es.cervecitas.earthquakeobserver.presentation.ui.earthquakelist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.OnItemViewClickListenerFactory;
import es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake.EarthquakeAdapter;

@PerFragment
final class EarthquakeListAdapter extends RecyclerView.Adapter<EarthquakeLineViewHolder> {

    public static final String STATE_PRESENTATION_EARTHQUAKES = "EarthquakeListAdapter.presentationEarthquakes";

    private final List<PresentationEarthquake> presentationEarthquakeList;
    private final EarthquakeAdapter earthquakeAdapter;
    private final EarthquakeLineViewHolderFactory earthquakeLineViewHolderFactory;
    private final OnItemViewClickListenerFactory<PresentationEarthquake> onItemViewClickListenerFactory;
    private final LayoutInflater layoutInflater;

    @Inject
    public EarthquakeListAdapter(List<PresentationEarthquake> presentationEarthquakeList,
                                 EarthquakeAdapter earthquakeAdapter,
                                 EarthquakeLineViewHolderFactory earthquakeLineViewHolderFactory,
                                 OnItemViewClickListenerFactory<PresentationEarthquake> onItemViewClickListenerFactory,
                                 LayoutInflater layoutInflater) {
        this.presentationEarthquakeList = presentationEarthquakeList;
        this.earthquakeAdapter = earthquakeAdapter;
        this.earthquakeLineViewHolderFactory = earthquakeLineViewHolderFactory;
        this.onItemViewClickListenerFactory = onItemViewClickListenerFactory;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public EarthquakeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item_earthquake, parent, false);
        EarthquakeLineViewHolder holder = earthquakeLineViewHolderFactory.create(itemView);
        earthquakeAdapter.initializeViewHolder(holder.getViewHolder());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeLineViewHolder holder, int position) {
        PresentationEarthquake presentationEarthquake = presentationEarthquakeList.get(position);
        earthquakeAdapter.bindViewHolderWithData(holder.getViewHolder(), presentationEarthquake);

        holder.itemView.setOnClickListener(onItemViewClickListenerFactory.create(presentationEarthquake));
    }

    @Override
    public int getItemCount() {
        return presentationEarthquakeList.size();
    }

    void addPresentationEarthquake(PresentationEarthquake presentationEarthquake) {
        presentationEarthquakeList.add(presentationEarthquake);
    }

    void clearPresentationEarthquakes() {
        presentationEarthquakeList.clear();
    }

    void notifyPresentationEarthquakesChanged() {
        notifyDataSetChanged();
    }

    void onSavedInstanceState(Bundle outstate) {
        // TODO: These models should not be saved / restored in the Bundle. The repository
        // provided by the data layer should be used to provide these synchronously from cache
        // when available.
        outstate.putSerializable(STATE_PRESENTATION_EARTHQUAKES, (Serializable)presentationEarthquakeList);
    }

    @SuppressWarnings("unchecked")
    void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Serializable presentationEarthquakes = savedInstanceState.getSerializable(STATE_PRESENTATION_EARTHQUAKES);
            if (presentationEarthquakes != null) {
                this.presentationEarthquakeList.addAll((List<PresentationEarthquake>)presentationEarthquakes);
            }

        }
    }

}
