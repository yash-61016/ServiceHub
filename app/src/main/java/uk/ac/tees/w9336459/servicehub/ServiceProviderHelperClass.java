package uk.ac.tees.w9336459.servicehub;

public class ServiceProviderHelperClass {
    String name,mobileNumber,address,email,accountNumber,sortCode,password;

    public ServiceProviderHelperClass() {
    }

    public ServiceProviderHelperClass(String name, String mobileNumber, String address, String email, String accountNumber, String sortCode, String password) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.password = password;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
