package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.cervecitas.earthquakeobserver.R;

/**
 * Created by luism on 28/04/2017.
 */

public class EarthquakesViewHolder extends RecyclerView.ViewHolder {

    private ViewGroup container;
    private TextView tvMagnitude;
    private TextView tvLocationOffset;
    private TextView tvPrimaryLocation;
    private TextView tvDate;
    private TextView tvTime;

    public EarthquakesViewHolder(View itemView) {
        super(itemView);

        container = (ViewGroup) itemView.findViewById(R.id.list_item_earthquake_container);
        tvMagnitude = (TextView) itemView.findViewById(R.id.tvMagnitude);
        tvLocationOffset = (TextView) itemView.findViewById(R.id.tvLocationOffset);
        tvPrimaryLocation = (TextView) itemView.findViewById(R.id.tvPrimaryLocation);
        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
    }

    public ViewGroup getContainer() {
        return container;
    }

    public TextView getTvMagnitude() {
        return tvMagnitude;
    }

    public TextView getTvLocationOffset() {
        return tvLocationOffset;
    }

    public TextView getTvPrimaryLocation() {
        return tvPrimaryLocation;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public TextView getTvTime() {
        return tvTime;
    }
}
