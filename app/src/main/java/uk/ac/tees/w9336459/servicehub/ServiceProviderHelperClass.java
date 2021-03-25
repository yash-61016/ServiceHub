package uk.ac.tees.w9336459.servicehub;

public class ServiceProviderHelperClass {
    String name,mobileNumber,address,email,accountNumber,sortCode,postcode;

    public ServiceProviderHelperClass() {
    }

    public ServiceProviderHelperClass(String name,String email, String address, String PostCode, String accountNumber, String sortCode) {

        this.mobileNumber = mobileNumber;
        this.address = address;
        this.postcode = PostCode;
        this.email = email;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;

    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }


}
