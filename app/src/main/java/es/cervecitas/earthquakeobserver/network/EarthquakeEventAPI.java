package es.cervecitas.earthquakeobserver.network;

import es.cervecitas.earthquakeobserver.network.model.EarthquakeObjects;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luism on 28/04/2017.
 */

public interface EarthquakeEventAPI {
    //@GET("query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10")
    String PARAM_FORMAT = "format";
    String PARAM_EVENT_TYPE = "eventtype";
    String PARAM_ORDER_BY = "orderby";
    String PARAM_MIN_MAG = "minmag";
    String PARAM_LIMIT = "limit";
    String PARAM_STARTDATE = "starttime";

    @GET("query")
    Single<EarthquakeObjects> getEarthquakeObjects(
            @Query(PARAM_FORMAT) String format,
            @Query(PARAM_EVENT_TYPE) String eventtype,
            @Query(PARAM_ORDER_BY) String orderby,
            @Query(PARAM_MIN_MAG) long minmag,
            @Query(PARAM_LIMIT) int limit,
            @Query(PARAM_STARTDATE) String startdate);
}
