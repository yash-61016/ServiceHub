package uk.ac.tees.w9336459.servicehub;

public class ServiceProviderHelperClass {
    String Name,Number,Address,Email,AccountNumber,SortCode,Postcode;

    public ServiceProviderHelperClass() {
    }

    public ServiceProviderHelperClass(String name, String number,
                                      String address, String email, String accountNumber,
                                      String sortCode, String postcode) {
        Name = name;
        Number = number;
        Address = address;
        Email = email;
        AccountNumber = accountNumber;
        SortCode = sortCode;
        Postcode = postcode;
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

    public String getEmail() {
        return Email;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public String getSortCode() {
        return SortCode;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public void setSortCode(String sortCode) {
        SortCode = sortCode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }
}
