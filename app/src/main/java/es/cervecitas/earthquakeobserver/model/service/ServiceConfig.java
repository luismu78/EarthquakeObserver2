package es.cervecitas.earthquakeobserver.model.service;

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
