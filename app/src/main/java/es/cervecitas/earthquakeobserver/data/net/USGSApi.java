package es.cervecitas.earthquakeobserver.data.net;

import es.cervecitas.earthquakeobserver.data.net.model.EarthquakeObjects;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface USGSApi {
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
