package redspark.io.miguelopolis.enums;

/**
 * Created by infra on 09/05/17.
 */

public enum ConnectionType {

    HOMOLOGATION("http://45.55.209.136:8080/");

    private String url;

    ConnectionType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
