package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.model.Earthquake;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakesViewHolder> {

    private String LOCATION_SEPARATOR;
    private List<Earthquake> earthquakeList;
    private DecimalFormat decimalFormat = new DecimalFormat("0.0");
    private Context context;

    EarthquakeAdapter(Context context, List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
        this.context = context;
        LOCATION_SEPARATOR = context.getString(R.string.of_separator);
    }

    @Override
    public EarthquakesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new EarthquakesViewHolder(inflater.inflate(R.layout.list_item_earthquake, parent, false));
    }

    @Override
    public void onBindViewHolder(EarthquakesViewHolder holder, final int position) {
        Earthquake earthquake = earthquakeList.get(position);

        // Magnitude & color
        holder.getTvMagnitude().setText(decimalFormat.format(earthquake.getMagnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.getTvMagnitude().getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Location
        String originalLocation = earthquake.getLocation();
        String locationOffset;
        String primaryLocation;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = context.getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        holder.getTvPrimaryLocation().setText(primaryLocation);
        holder.getTvLocationOffset().setText(locationOffset);

        // Date
        SimpleDateFormat sdf = new SimpleDateFormat("LLL dd, yyyy", java.util.Locale.getDefault());
        holder.getTvDate().setText(sdf.format(earthquake.getCalendar().getTime()));

        // Time
        sdf = new SimpleDateFormat("h:mm a", java.util.Locale.getDefault());
        holder.getTvTime().setText(sdf.format(earthquake.getCalendar().getTime()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Earthquake selectedEarthquake = earthquakeList.get(position);
                Uri earthquakeUri = Uri.parse(selectedEarthquake.getUrl());
                Intent showEarthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                context.startActivity(showEarthquakeIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return earthquakeList.size();
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
