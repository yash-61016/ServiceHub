package uk.ac.tees.w9336459.servicehub;

public class UserAccountHelper {
    String Name, Number , Address ,Postcode , Email;


    public UserAccountHelper() {

    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostcode() {
        return Postcode;
    }

    public String getEmail() {
        return Email;
    }


    public void setName(String Name) {
        this.Name = Name;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPostcode(String PostCode) {
        this.Postcode = PostCode;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public UserAccountHelper(String Name, String Number, String Address, String PostCode, String Email) {
        this.Name = Name;
        this.Number = Number;
        this.Address = Address;
        this.Postcode = PostCode;
        this.Email = Email;
    }
}
