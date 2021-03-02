package uk.ac.tees.w9336459.servicehub;

public class UserAccountHelper {

    String f_name;
    String l_name;
    String email;
    String mobile;
    String ad1;
    String ad2;
    String ad3;
    String password;
    String v_password;

    /**
     * Constructors
     */

    public UserAccountHelper() {
        this.f_name = null;
        this.l_name = null;
        this.email = null;
        this.mobile = null;
        this.ad1 = null;
        this.ad2 = null;
        this.ad3 = null;
        this.password = null;
        this.v_password = null;
    }

    public UserAccountHelper(String f_name, String l_name, String email,
                             String mobile, String ad1, String ad2,
                             String ad3, String password, String v_password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.mobile = mobile;
        this.ad1 = ad1;
        this.ad2 = ad2;
        this.ad3 = ad3;
        this.password = password;
        this.v_password = v_password;
    }

    /**
     * Getters and Setters for the User Registeration
     * @return
     */
    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAd1() {
        return ad1;
    }

    public void setAd1(String ad1) {
        this.ad1 = ad1;
    }

    public String getAd2() {
        return ad2;
    }

    public void setAd2(String ad2) {
        this.ad2 = ad2;
    }

    public String getAd3() {
        return ad3;
    }

    public void setAd3(String ad3) {
        this.ad3 = ad3;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getV_password() {
        return v_password;
    }

    public void setV_password(String v_password) {
        this.v_password = v_password;
    }


}
