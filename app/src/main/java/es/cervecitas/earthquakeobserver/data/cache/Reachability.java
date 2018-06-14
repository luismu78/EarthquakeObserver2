package es.cervecitas.earthquakeobserver.data.cache;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Reachability {

    private final ConnectivityManager connectivityManager;

    public Reachability(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
