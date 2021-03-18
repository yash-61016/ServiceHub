package uk.ac.tees.w9336459.servicehub;

public class UserAccountHelper {
    String name;
    String number;
    String address;
    String postcode;
    String email;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcde(String postcode) {
        this.postcode = postcode;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }



    public UserAccountHelper() {

    }
    public UserAccountHelper(String name,String email, String number, String address,String Postcode) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.postcode = Postcode;
        this.email = email;

    }



}
