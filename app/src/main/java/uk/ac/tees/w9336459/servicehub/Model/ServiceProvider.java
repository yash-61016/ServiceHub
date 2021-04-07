package uk.ac.tees.w9336459.servicehub.Model;

import javax.security.auth.PrivateCredentialPermission;

public class ServiceProvider {
    private static String id;
    private static String imgUrl;
    private static String ServiceProviderName;

    public ServiceProvider(String id, String imgUrl, String serviceProviderName) {
        this.id = id;
        this.imgUrl = imgUrl;
        ServiceProviderName = serviceProviderName;
    }

    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static String getServiceProviderName() {
        return ServiceProviderName;
    }

    public static void setServiceProviderName(String serviceProviderName) {
        ServiceProviderName = serviceProviderName;
    }
}
