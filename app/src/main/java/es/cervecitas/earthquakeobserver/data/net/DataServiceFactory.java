package es.cervecitas.earthquakeobserver.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
final class DataServiceFactory {

    @Inject
    DataServiceFactory() {

    }

    private Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    private OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder();
        // TODO: add cache (.cache)
    }

    private Retrofit retrofit(OkHttpClient.Builder okHttpClientBuilder) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Config.BASE_API_URL)
                .client(okHttpClientBuilder.build())
                .build();
    }

    public <T> T create(Class<T> serviceClass) {

        //TODO: get rid of the logging interceptor or add a conditional for debug building in gradle

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return retrofit(okHttpClientBuilder())
                .create(serviceClass);
//                .addInterceptor(logging))
    }
}
