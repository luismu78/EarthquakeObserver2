package es.cervecitas.earthquakeobserver.data.cache;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class CacheInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private final CacheManager mCacheManager;
    private final Reachability mReachability;

    public CacheInterceptor(CacheManager cacheManager, Reachability reachability) {
        mCacheManager = cacheManager;
        mReachability = reachability;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

//        Response response = chain.proceed(chain.request());
//
//        String headers = response.header("Cache-Control");
//        if (mReachability.isConnected() &&
//                (headers == null || headers.contains("no-store")
//                        || headers.contains("must-revalidate") || headers.contains("no-cache")
//                        || headers.contains("max-age=0"))){
//            Log.d("HOLA", getClass().getSimpleName() + " - Returning fresh response");
//            return response.newBuilder()
//                    .header("Cache-Control", "public, max-age=600")
//                    .build();
//        } else{
//            Log.d("HOLA", getClass().getSimpleName() + " - Returning old response");
//            return response;
//        }

//        ------------------------------- 2 ----------------------------

        Request request = chain.request();

        Response response = chain.proceed(request);
        String rawJson = response.body().string();

        // Re-create the response before returning it because body can be read only once
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), rawJson))
                .build();

//        ------------------------------- 1 ----------------------------

//        Request request = chain.request();
//
//        Response response;
//
//        try {
//            response = chain.proceed(request);
//        } catch (Exception e) {
//            Log.d("HOLA", "<-- HTTP FAILED: " + e);
//            throw e;
//        }
//
//        ResponseBody responseBody = response.peekBody(4096);
//
//
//        String bodyString = responseBody.string();
//        MediaType contentType = responseBody.contentType();
//
//        return response
//                .newBuilder()
//                .body(ResponseBody.create(contentType, bodyString))
//                .build();


//        return response;


//        Request request = chain.request();
//        String key = request.url().toString();
//
//        Response response;
//        Log.d("HOLA", getClass().getSimpleName() + " - intercept");
//        if (mReachability.isConnected()) {
//            Log.d("HOLA", getClass().getSimpleName() + " - reachability.isConnected()");
//            try {
//                response = chain.proceed(request);
////                Response newResponse = response.newBuilder().build();
//                ResponseBody body = response.body();
//                String bodyString = body.string();
//                MediaType contentType = body.contentType();
//
//                Log.d("HOLA", getClass().getSimpleName() + " - response.isSuccessful(): " + response.isSuccessful());
//
//                if (response.isSuccessful()) {
//                    Log.d("HOLA", getClass().getSimpleName() + " - response.isSuccessful()");
//                    if (response.code() == 204) {
//                        Log.d("HOLA", getClass().getSimpleName() + " - response is 204");
//                        return response;
//                    }
//
//                    // -----------------
//
//                    // -----------------
//
//                    // save to cache this success model.
//                    mCacheManager.getCache().put(key, bodyString);
//
//                    Log.d("HOLA", getClass().getSimpleName() + " - cache.put(): " + bodyString);
//
//                    //*************************
//
//                    String cachedData = mCacheManager.getCache().get(key);
//
//                    Log.d("HOLA", getClass().getSimpleName() + " - cachedData: " + cachedData);
//
//                    return response
//                            .newBuilder()
//                            .code(200)
//                            .body(ResponseBody.create(contentType, cachedData))
//                            .request(request)
//                            .build();
//
////                    return new Response.Builder()
////                            .code(200)
////                            .body(ResponseBody.create(MediaType.parse("application/json"), cachedData))
////                            .request(request)
////                            .protocol(Protocol.HTTP_1_1)
////                            .build();
//
//                    // now we know that we definitely have a cache hit.
////                    return getCachedResponse(key, request);
//
//                } else if (response.code() >= 500) { // accommodate all server errors
//                    Log.d("HOLA", getClass().getSimpleName() + " - response: server error");
//                    // check if there is a cache hit or miss.
//                    if (isCacheHit(key)) {
//                        Log.d("HOLA", getClass().getSimpleName() + " - cache is hit");
//
//                        //********************
//
//                        String cachedData = mCacheManager.getCache().get(key);
//
//                        return new Response.Builder()
//                                .code(200)
//                                .body(ResponseBody.create(MediaType.parse("application/json"), cachedData))
//                                .request(request)
//                                .protocol(Protocol.HTTP_1_1)
//                                .build();
//                        // if data is in cache, the return the data from cache.
////                        return getCachedResponse(key, request);
//                    }else {
//                        Log.d("HOLA", getClass().getSimpleName() + " - cache miss");
//                        // if it's a miss, we can't do much but return the server state.
//                        return response;
//                    }
//                } else { // if there is any client side error
//                    Log.d("HOLA", getClass().getSimpleName() + " - client side error");
//                    // forward the response as it is to the business layers to handle.
//                    return response;
//                }
//            } catch (ConnectException | UnknownHostException e) {
//                Log.d("HOLA", getClass().getSimpleName() + " - internet conn exception: " + e.getMessage());
//                // Internet connection exception.
//                e.printStackTrace();
//            }
//        }
//
//        // if somehow there is an internet connection error
//        // check if the data is already cached.
//        if (isCacheHit(key)) {
//            Log.d("HOLA", getClass().getSimpleName() + " cache is hit - " + key);
//
//            //***************************
//
//            String cachedData = mCacheManager.getCache().get(key);
//
//            return new Response.Builder()
//                    .code(200)
//                    .body(ResponseBody.create(MediaType.parse("application/json"), cachedData))
//                    .request(request)
//                    .protocol(Protocol.HTTP_1_1)
//                    .build();
//
////            return getCachedResponse(key, request);
//        }else {
//            // if the data is not in the cache we'll throw an internet connection error.
//            throw new UnknownHostException();
//        }
    }

//    private Response getCachedResponse(String url, Request request) {
//        Log.d("HOLA", getClass().getSimpleName() + " - getCachedResponse");
//
//        String cachedData = mCacheManager.getCache().get(url);
//
//        return new Response.Builder()
//                .code(200)
//                .body(ResponseBody.create(MediaType.parse("application/json"), cachedData))
//                .request(request)
//                .protocol(Protocol.HTTP_1_1)
//                .build();
//    }

    public boolean isCacheHit(String key) {
        Log.d("HOLA", getClass().getSimpleName() + " - isCacheHit");
        return mCacheManager.getCache().get(key) != null;
    }

    public static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
