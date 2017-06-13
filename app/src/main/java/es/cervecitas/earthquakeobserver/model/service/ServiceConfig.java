package es.cervecitas.earthquakeobserver.model.service;

public class ServiceConfig {

    private String baseServiceUrl;

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
