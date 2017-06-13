package es.cervecitas.earthquakeobserver.model.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import es.cervecitas.earthquakeobserver.model.service.model.EarthquakeObjects;
import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;

public class EarthquakeService {

    private final ServiceConfig serviceConfig;
    private final EarthquakeServiceInterface service;

    interface EarthquakeServiceInterface {
        //@GET("query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10")
        String PARAM_FORMAT = "format";
        String PARAM_EVENT_TYPE = "eventtype";
        String PARAM_ORDER_BY = "orderby";
        String PARAM_MIN_MAG = "minmag";
        String PARAM_LIMIT = "limit";
        String PARAM_STARTDATE = "starttime";

        @GET("query")
        Observable<EarthquakeObjects> getEarthquakeObjects(
                @Query(PARAM_FORMAT) String format,
                @Query(PARAM_EVENT_TYPE) String eventtype,
                @Query(PARAM_ORDER_BY) String orderby,
                @Query(PARAM_MIN_MAG) long minmag,
                @Query(PARAM_LIMIT) int limit,
                @Query(PARAM_STARTDATE) String startdate);
    }

    public EarthquakeService(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
        service = buildService();
    }

    public Observable<EarthquakeObjects> getEarthquakeObjects(
            String format,
            String eventType,
            String orderBy,
            long minmag,
            int limit,
            String starttime) {

        return service.getEarthquakeObjects(format, eventType, orderBy, minmag, limit, starttime);
    }

    private EarthquakeServiceInterface buildService() {
        return getDefaultRetrofitBuilder().build().create(EarthquakeServiceInterface.class);
    }

    private Retrofit.Builder getDefaultRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(serviceConfig.getBaseServiceUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClientBuilder().build())
                .addConverterFactory(GsonConverterFactory.create());
    }

    private OkHttpClient.Builder getOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain
                                .request()
                                .newBuilder()
                                .addHeader(CONTENT_TYPE, "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                });
    }
}
