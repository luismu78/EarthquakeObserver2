package es.cervecitas.earthquakeobserver.presentation.model;

import java.util.Calendar;

public class PresentationEarthquake implements PresentationObject {

    private final Double magnitude;
    private final String location;
    private final Calendar dateTime;
    private final String url;

    private PresentationEarthquake(Builder builder) {
        this.magnitude = builder.magnitude;
        this.location = builder.location;
        this.dateTime = builder.dateTime;
        this.url = builder.url;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public String getUrl() {
        return url;
    }

    public static final class Builder {
        private Double magnitude;
        private String location;
        private Calendar dateTime;
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

        public Builder dateTime(Calendar dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public PresentationEarthquake build() {
            return new PresentationEarthquake(this);
        }
    }
}
