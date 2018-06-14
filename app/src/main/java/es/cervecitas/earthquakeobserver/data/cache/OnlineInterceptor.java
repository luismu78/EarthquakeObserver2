package es.cervecitas.earthquakeobserver.data.cache;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class OnlineInterceptor implements Interceptor {

    private final Reachability mReachability;

    public OnlineInterceptor(Reachability reachability) {
        mReachability = reachability;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        String headers = response.header("Cache-Control");
        if(mReachability.isConnected() &&
                (headers == null || headers.contains("no-store") || headers.contains("must-revalidate") || headers.contains("no-cache") || headers.contains("max-age=0"))){

            Log.d("HOLA", getClass().getSimpleName() + " - Returning fresh response");
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=600")
                    .build();
        } else{
            Log.d("HOLA", getClass().getSimpleName() + " - Returning old response");
            return response;
        }
    }
}
