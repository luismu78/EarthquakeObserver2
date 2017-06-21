package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.cervecitas.earthquakeobserver.R;

class EarthquakesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvMagnitude;
    private TextView tvLocationOffset;
    private TextView tvPrimaryLocation;
    private TextView tvDate;
    private TextView tvTime;

    EarthquakesViewHolder(View itemView) {
        super(itemView);

        tvMagnitude = (TextView) itemView.findViewById(R.id.tvMagnitude);
        tvLocationOffset = (TextView) itemView.findViewById(R.id.tvLocationOffset);
        tvPrimaryLocation = (TextView) itemView.findViewById(R.id.tvPrimaryLocation);
        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
    }

    TextView getTvMagnitude() {
        return tvMagnitude;
    }

    TextView getTvLocationOffset() {
        return tvLocationOffset;
    }

    TextView getTvPrimaryLocation() {
        return tvPrimaryLocation;
    }

    TextView getTvDate() {
        return tvDate;
    }

    TextView getTvTime() {
        return tvTime;
    }
}
