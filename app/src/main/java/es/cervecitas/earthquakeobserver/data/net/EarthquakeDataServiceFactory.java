package es.cervecitas.earthquakeobserver.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import es.cervecitas.earthquakeobserver.App;
import es.cervecitas.earthquakeobserver.BuildConfig;
import es.cervecitas.earthquakeobserver.data.cache.OfflineInterceptor;
import es.cervecitas.earthquakeobserver.data.cache.OnlineInterceptor;
import es.cervecitas.earthquakeobserver.data.cache.Reachability;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
final class EarthquakeDataServiceFactory {

    private static final String USGS_BASE_URL = BuildConfig.BASE_URL;
    private final Reachability reachability;

    @SuppressWarnings("WeakerAccess")
    @Inject
    @Named("CACHE_DIR")
    File cacheDir;

    @Inject
    EarthquakeDataServiceFactory(App app) {
        this.reachability = new Reachability(app.getApplicationContext());
    }

    private Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    public <T> T create(Class<T> serviceClass) {
        return retrofit(okHttpClientBuilder()).create(serviceClass);
    }

    private OkHttpClient.Builder okHttpClientBuilder() {

//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File httpCacheDirectory = new File(cacheDir, "http-cache");
        int cacheSize = 512 * 1024; // 0.5 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        // TODO: remove logging interceptor

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new OnlineInterceptor(reachability))
                .addNetworkInterceptor(new OfflineInterceptor(reachability))
                .cache(cache);
//                .addInterceptor(loggingInterceptor);
    }

    private Retrofit retrofit(OkHttpClient.Builder okHttpClientBuilder) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(USGS_BASE_URL)
                .client(okHttpClientBuilder.build())
                .build();
    }
}
