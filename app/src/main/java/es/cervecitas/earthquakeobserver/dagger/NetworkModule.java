package es.cervecitas.earthquakeobserver.dagger;

import dagger.Module;

@Module
public class NetworkModule {

//    private static final String NAME_BASE_URL = "NAME_BASE_URL";
//    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 256 * 1024; // 256k
//    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_AGE = 5 * 60; // 5 min
//
//    @Provides
//    @Named(NAME_BASE_URL)
//    String provideBaseUrlString() {
//        return Constants.BASE_URL;
//    }
//
//    @Provides
//    @Singleton
//    Converter.Factory provideGsonConverter() {
//        return GsonConverterFactory.create();
//    }
//
//    @Provides
//    @Singleton
//    Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient okHttpClient, @Named(NAME_BASE_URL) String baseUrl) {
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(converter)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(okHttpClient)
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    OkHttpClient provideHttpClient(final Context context) {
//        return new OkHttpClient.Builder()
//                .cache(new Cache(context.getCacheDir(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE))
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//
//                        if (isNetworkAvailable(context)) {
//                            Response response = chain.proceed(chain.request());
//                            CacheControl cacheControl = new CacheControl.Builder()
//                                    .maxAge(HTTP_RESPONSE_DISK_CACHE_MAX_AGE, TimeUnit.SECONDS)
//                                    .build();
//                            return response.newBuilder()
//                                    .header("Cache-Control", cacheControl.toString())
//                                    .build();
//                        } else {
//                            Request request = chain.request();
//                            request = request.newBuilder()
//                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
//                                    .build();
//                            return chain.proceed(request);
//                        }
//
//                    }
//                })
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    EarthquakeEventAPI provideEarthquakeEventApi(Retrofit retrofit) {
//        return retrofit.create(EarthquakeEventAPI.class);
//    }
//
//    private static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//        return networkInfo != null && networkInfo.isConnected();
//
//    }
}
