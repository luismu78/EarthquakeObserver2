package es.cervecitas.earthquakeobserver.ui.earthquakes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.earthquakeobserver.R;
import es.cervecitas.earthquakeobserver.app.EarthquakeObserverApplication;
import es.cervecitas.earthquakeobserver.model.Earthquake;

public class EarthquakesActivity extends AppCompatActivity implements EarthquakesView {

    @Inject
    EarthquakesPresenter presenter;

    @BindView(R.id.rvEarthquakes)
    RecyclerView rvEarthquakes;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ((EarthquakeObserverApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        rvEarthquakes.setLayoutManager(new LinearLayoutManager(this));

        presenter.setView(this);
        presenter.getEarthquakes();
    }

    // EarthquakesView

    @Override
    public void showLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showEarthquakeList(List<Earthquake> earthquakeItemList) {
        rvEarthquakes.setAdapter(new EarthquakeAdapter(this, earthquakeItemList));
        rvEarthquakes.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {
        //TODO: move to Log
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchEarthquakeDetail(Earthquake earthquake) {
        Uri earthquakeUri = Uri.parse(earthquake.getUrl());
        Intent showEarthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
        startActivity(showEarthquakeIntent);
    }

//    // Adapter
//    class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakesViewHolder> {
//
//        private String LOCATION_SEPARATOR;
//        private List<Earthquake> earthquakeList;
//        private DecimalFormat decimalFormat = new DecimalFormat("0.0");
//
//        EarthquakeAdapter(List<Earthquake> earthquakeList) {
//            this.earthquakeList = earthquakeList;
//            LOCATION_SEPARATOR = getApplicationContext().getString(R.string.of_separator);
//        }
//
//        @Override
//        public EarthquakesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(EarthquakesActivity.this);
//            return new EarthquakesViewHolder(inflater.inflate(R.layout.list_item_earthquake, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(EarthquakesViewHolder holder, int position) {
//            Earthquake earthquake = earthquakeList.get(position);
//
//            // Magnitude & color
//            holder.getTvMagnitude().setText(decimalFormat.format(earthquake.getMagnitude()));
//            GradientDrawable magnitudeCircle = (GradientDrawable) holder.getTvMagnitude().getBackground();
//            int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());
//            magnitudeCircle.setColor(magnitudeColor);
//
//            // Location
//            String originalLocation = earthquake.getLocation();
//            String locationOffset;
//            String primaryLocation;
//            if (originalLocation.contains(LOCATION_SEPARATOR)) {
//                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
//                locationOffset = parts[0] + LOCATION_SEPARATOR;
//                primaryLocation = parts[1];
//            } else {
//                locationOffset = getApplicationContext().getString(R.string.near_the);
//                primaryLocation = originalLocation;
//            }
//            holder.getTvPrimaryLocation().setText(primaryLocation);
//            holder.getTvLocationOffset().setText(locationOffset);
//
//            // Date
//            SimpleDateFormat sdf = new SimpleDateFormat("LLL dd, yyyy", java.util.Locale.getDefault());
//            holder.getTvDate().setText(sdf.format(earthquake.getCalendar().getTime()));
//
//            // Time
//            sdf = new SimpleDateFormat("h:mm a", java.util.Locale.getDefault());
//            holder.getTvTime().setText(sdf.format(earthquake.getCalendar().getTime()));
//        }
//
//        @Override
//        public int getItemCount() {
//            return earthquakeList.size();
//        }
//
//        private int getMagnitudeColor(double magnitude) {
//            int magnitudeColorResourceId;
//            int magnitudeFloor = (int) Math.floor(magnitude);
//            switch (magnitudeFloor) {
//                case 0:
//                case 1:
//                    magnitudeColorResourceId = R.color.magnitude1;
//                    break;
//                case 2:
//                    magnitudeColorResourceId = R.color.magnitude2;
//                    break;
//                case 3:
//                    magnitudeColorResourceId = R.color.magnitude3;
//                    break;
//                case 4:
//                    magnitudeColorResourceId = R.color.magnitude4;
//                    break;
//                case 5:
//                    magnitudeColorResourceId = R.color.magnitude5;
//                    break;
//                case 6:
//                    magnitudeColorResourceId = R.color.magnitude6;
//                    break;
//                case 7:
//                    magnitudeColorResourceId = R.color.magnitude7;
//                    break;
//                case 8:
//                    magnitudeColorResourceId = R.color.magnitude8;
//                    break;
//                case 9:
//                    magnitudeColorResourceId = R.color.magnitude9;
//                    break;
//                default:
//                    magnitudeColorResourceId = R.color.magnitude10plus;
//                    break;
//            }
//
//            return ContextCompat.getColor(getApplicationContext(), magnitudeColorResourceId);
//        }
//    }
}
