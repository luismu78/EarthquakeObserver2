package es.cervecitas.earthquakeobserver.domain;

public final class EarthquakeQueryParameters implements DomainObject {

    private final String format;
    private final String eventType;
    private final Integer limit;
    private final Long minMag;
    private final String orderBy;
    private final String startDate;

    private EarthquakeQueryParameters(Builder builder) {
        this.format = builder.format;
        this.eventType = builder.eventType;
        this.limit = builder.limit;
        this.minMag = builder.minMag;
        this.orderBy = builder.orderBy;
        this.startDate = builder.startDate;
    }

    public String getFormat() {
        return format;
    }

    public String getEventType() {
        return eventType;
    }

    public Integer getLimit() {
        return limit;
    }

    public Long getMinMag() {
        return minMag;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public static final class Builder {
        private String format;
        private String eventType;
        private Integer limit;
        private Long minMag;
        private String orderBy;
        private String startDate;

        public Builder() {

        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder minMag(Long minMag) {
            this.minMag = minMag;
            return this;
        }

        public Builder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder startDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public EarthquakeQueryParameters build() {
            return new EarthquakeQueryParameters(this);
        }
    }


}
