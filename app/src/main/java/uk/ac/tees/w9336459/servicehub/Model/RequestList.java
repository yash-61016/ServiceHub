package uk.ac.tees.w9336459.servicehub.Model;

public class RequestList {

    public String id;
    public String status;

    public RequestList(String id,String status)
    {
        this.id = id;
        this.status = status;
    }
    public RequestList(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
