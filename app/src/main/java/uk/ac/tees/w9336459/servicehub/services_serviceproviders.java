package uk.ac.tees.w9336459.servicehub;

public class services_serviceproviders {

    public String name, image, status;

    public services_serviceproviders(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public services_serviceproviders(String name, String image, String status) {
        this.name = name;
        this.image = image;
        this.status = status;
    }
}

