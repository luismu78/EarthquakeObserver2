package es.cervecitas.earthquakeobserver.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.cervecitas.earthquakeobserver.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
final class EarthquakeDataServiceFactory {

    private static final String USGS_BASE_URL = BuildConfig.BASE_URL;

    @Inject
    EarthquakeDataServiceFactory() {
        // TODO: provide the data here instead of using constants
    }

    private Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    public <T> T create(Class<T> serviceClass) {
        return retrofit(okHttpClientBuilder()).create(serviceClass);
    }

    private OkHttpClient.Builder okHttpClientBuilder() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // TODO: remove logging interceptor

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor);

//        return new OkHttpClient.Builder();
        // TODO: add cache (.cache)
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
