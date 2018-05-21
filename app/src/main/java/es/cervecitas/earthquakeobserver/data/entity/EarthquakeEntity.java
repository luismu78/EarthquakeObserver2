package es.cervecitas.earthquakeobserver.data.entity;

import android.support.annotation.NonNull;

import java.util.Calendar;

public class EarthquakeEntity implements Entity {

    private final Double magnitude;
    private final String location;
    private final Calendar calendar;
    private String url;

    public EarthquakeEntity(Builder builder) {
        this.magnitude = builder.magnitude;
        this.location = builder.location;
        this.calendar = builder.calendar;
        this.url = builder.url;
    }

    @NonNull
    public Double getMagnitude() {
        return magnitude;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    @NonNull
    public Calendar getCalendar() {
        return calendar;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public static final class Builder {
        private Double magnitude;
        private String location;
        private Calendar calendar;
        private String url;

        public Builder() {

        }

        public Builder magnitude(Double magnitude) {
            this.magnitude = magnitude;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder calendar(Calendar calendar) {
            this.calendar = calendar;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public EarthquakeEntity build() {
            return new EarthquakeEntity(this);
        }
    }
}
