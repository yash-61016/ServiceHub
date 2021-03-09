package uk.ac.tees.w9336459.servicehub;

public class UserAccountHelper {
    String name;
    String number;
    String address;
    String email;
    String password;
    String verify_password;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerify_password(String verify_password) {
        this.verify_password = verify_password;
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

    public String getPassword() {
        return password;
    }

    public String getVerify_password() {
        return verify_password;
    }



    public UserAccountHelper() {

    }
    public UserAccountHelper(String name, String number, String address, String email, String password, String verify_password) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.email = email;
        this.password = password;
        this.verify_password = verify_password;
    }



}
