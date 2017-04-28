package es.cervecitas.earthquakeobserver.model;

import android.support.annotation.NonNull;

import java.util.Calendar;

public class Earthquake {

    @NonNull
    private final Double magnitude;

    @NonNull
    private final String location;

    @NonNull
    private final Calendar calendar;

    @NonNull
    private String url;

    public Earthquake(@NonNull final Double magnitude, @NonNull final String location, final long time, @NonNull final String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(time);
        this.url = url;
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
}
