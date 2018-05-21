package es.cervecitas.earthquakeobserver.data.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.earthquakeobserver.data.entity.EarthquakeEntity;
import es.cervecitas.earthquakeobserver.data.entity.mapper.EntityMapperHolder;
import es.cervecitas.earthquakeobserver.data.net.USGSApi;
import es.cervecitas.earthquakeobserver.data.net.model.EarthquakeObjects;
import es.cervecitas.earthquakeobserver.data.net.model.Feature;
import es.cervecitas.earthquakeobserver.domain.Earthquake;
import es.cervecitas.earthquakeobserver.domain.repository.EarthquakeRepository;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class EarthquakeEntityRepository implements EarthquakeRepository {

    private final EntityMapperHolder entityMapperHolder;

    @Inject
    EarthquakeEntityRepository(EntityMapperHolder entityMapperHolder) {
        this.entityMapperHolder = entityMapperHolder;
    }

    @Inject
    USGSApi api;

    @Override
    public Observable<Earthquake> getEarthquake() {
        return null;
    }

    @Override
    public Observable<List<Earthquake>> getEarthquakeList(final String format,
                                                          final String eventtype,
                                                          final String orderby,
                                                          final long minmag,
                                                          final int limit,
                                                          final String startdate) {
        return api.getEarthquakeObjects(format, eventtype, orderby, minmag, limit, startdate)
                .map(new Function<EarthquakeObjects, List<EarthquakeEntity>>() {
                    @Override
                    public List<EarthquakeEntity> apply(EarthquakeObjects earthquakeObjects) throws Exception {
                        return earthquakeObjectToListEarthquakeEntity(earthquakeObjects);
                    }
                })
                .map(new Function<List<EarthquakeEntity>, List<Earthquake>>() {
                    @Override
                    public List<Earthquake> apply(List<EarthquakeEntity> earthquakeEntities) throws Exception {
                        List<Earthquake> earthquakeList = new ArrayList<>();
                        for (EarthquakeEntity earthquakeEntity : earthquakeEntities) {
                            earthquakeList.add(entityMapperHolder.getEarthquakeEntityMapper().map(earthquakeEntity));
                        }
                        return earthquakeList;
                    }
                });
    }

    private List<EarthquakeEntity> earthquakeObjectToListEarthquakeEntity(EarthquakeObjects earthquakeObjects) {
        List<EarthquakeEntity> earthquakeEntityList = new ArrayList<>();
        Calendar calDate = Calendar.getInstance();
        EarthquakeEntity.Builder earthquakeEntityBuilder = new EarthquakeEntity.Builder();

        for (Feature feature : earthquakeObjects.getFeatures()) {
            earthquakeEntityBuilder.magnitude(feature.getProperties().getMag());
            earthquakeEntityBuilder.location(feature.getProperties().getPlace());
            calDate.setTimeInMillis(feature.getProperties().getTime());
            earthquakeEntityBuilder.calendar(calDate);
            earthquakeEntityBuilder.url(feature.getProperties().getUrl());
            earthquakeEntityList.add(earthquakeEntityBuilder.build());
        }
        return earthquakeEntityList;
    }
}
