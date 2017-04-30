package es.cervecitas.earthquakeobserver.dagger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.cervecitas.earthquakeobserver.app.Constants;
import es.cervecitas.earthquakeobserver.network.EarthquakeEventAPI;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 256 * 1024;
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_AGE = 60 * 60;

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient okHttpClient, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(final Context context) {
        return new OkHttpClient
                .Builder()
                .cache(new Cache(context.getCacheDir(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //if (isNetworkAvailable(context)) {
                        if (isNetworkAvailable(context)) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + HTTP_RESPONSE_DISK_CACHE_MAX_AGE).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    EarthquakeEventAPI provideEarthquakeEventApi(Retrofit retrofit) {
        return retrofit.create(EarthquakeEventAPI.class);
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }
}
