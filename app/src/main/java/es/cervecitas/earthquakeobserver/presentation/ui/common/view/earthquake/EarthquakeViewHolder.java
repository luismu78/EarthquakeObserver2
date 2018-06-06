package es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.earthquakeobserver.R;

/**
 * Holds views that are used to bind view data. All views defined here are optional and can be null.
 * This makes this ViewHolder reusable across different layout configurations
 */
public final class EarthquakeViewHolder {

    @Nullable
    @BindView(R.id.tvMagnitude)
    public TextView tvMagnitude;

    @Nullable
    @BindView(R.id.tvLocationOffset)
    public TextView tvLocationOffset;

    @Nullable
    @BindView(R.id.tvPrimaryLocation)
    public TextView tvPrimaryLocation;

    @Nullable
    @BindView(R.id.tvDate)
    public TextView tvDate;

    @Nullable
    @BindView(R.id.tvTime)
    public TextView tvTime;

    EarthquakeViewHolder(View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
