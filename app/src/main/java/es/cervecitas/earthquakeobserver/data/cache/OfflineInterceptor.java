package es.cervecitas.earthquakeobserver.data.cache;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineInterceptor implements Interceptor {

    private final Reachability mReachability;

    public OfflineInterceptor(Reachability reachability) {
        mReachability = reachability;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if(!mReachability.isConnected()){
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached")
                    .build();
        }

        return chain.proceed(request);
    }
}
