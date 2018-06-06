package es.cervecitas.earthquakeobserver.presentation.ui.common.view.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.inject.PerFragment;
import es.cervecitas.earthquakeobserver.presentation.model.PresentationEarthquake;

@PerFragment
public final class EarthquakeAdapter {

    private static final String LOCATION_SEPARATOR = " of ";

    private final Context context;

    @Inject
    EarthquakeAdapter(Context context) {
        this.context = context;
    }

    public void initializeViewHolder(EarthquakeViewHolder holder) {
    }

    public void bindViewHolderWithData(EarthquakeViewHolder holder, PresentationEarthquake presentationEarthquake) {
        bindMagnitude(holder, presentationEarthquake);
        bindLocation(holder, presentationEarthquake);
        bindDate(holder, presentationEarthquake);
        bindTime(holder, presentationEarthquake);
    }

    private void bindMagnitude(EarthquakeViewHolder holder, PresentationEarthquake presentationEarthquake) {
        if (holder.tvMagnitude != null) {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            holder.tvMagnitude.setText(decimalFormat.format(presentationEarthquake.getMagnitude()));
            setMagnitudeCircle(holder, presentationEarthquake);
        }
    }

    private void bindLocation(EarthquakeViewHolder holder, PresentationEarthquake presentationEarthquake) {
        if (holder.tvPrimaryLocation != null && holder.tvLocationOffset != null) {
            String originalLocation = presentationEarthquake.getLocation();
            String locationOffset;
            String primaryLocation;
            if (originalLocation.contains(LOCATION_SEPARATOR)) {
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                locationOffset = parts[0] + context.getString(R.string.of_separator);
                primaryLocation = parts[1];
            } else {
                locationOffset = context.getString(R.string.near_the);
                primaryLocation = originalLocation;
            }
            holder.tvPrimaryLocation.setText(primaryLocation);
            holder.tvLocationOffset.setText(locationOffset);
        }
    }

    private void bindDate(EarthquakeViewHolder holder, PresentationEarthquake earthquake) {
        if (holder.tvDate != null) {
            SimpleDateFormat sdfDate = new SimpleDateFormat("LLL dd, yyyy", java.util.Locale.getDefault());
            holder.tvDate.setText(sdfDate.format(earthquake.getDateTime().getTime()));
        }
    }

    private void bindTime(EarthquakeViewHolder holder, PresentationEarthquake earthquake) {
        if (holder.tvTime != null) {
            SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a", java.util.Locale.getDefault());
            holder.tvTime.setText(sdfTime.format(earthquake.getDateTime().getTime()));
        }
    }

    private void setMagnitudeCircle(EarthquakeViewHolder holder, PresentationEarthquake presentationEarthquake) {
        if (holder.tvMagnitude != null) {
            GradientDrawable magnitudeCircle = (GradientDrawable) holder.tvMagnitude.getBackground();
            int magnitudeColor = getMagnitudeColor(presentationEarthquake.getMagnitude());
            magnitudeCircle.setColor(magnitudeColor);
        }
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(context, magnitudeColorResourceId);
    }

}
