package es.cervecitas.earthquakeobserver.repository;

public class ServiceConfig {

    public String baseServiceUrl;

    public ServiceConfig(String baseServiceUrl) {
        this.baseServiceUrl = baseServiceUrl;
    }

    public String getBaseServiceUrl() {
        return baseServiceUrl;
    }

    public void setBaseServiceUrl(String baseServiceUrl) {
        this.baseServiceUrl = baseServiceUrl;
    }
}
