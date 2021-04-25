package uk.ac.tees.w9336459.servicehub.Model;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

public class ServiceProviders2 {
    String firstname;
    String lastname;
    String emailid;

    public ServiceProviders2(String firstname, String lastname, String emailid, String phonenum, String postcode, Boolean pending, int totalrate, int numrate, float avrate, String address, float yearsofexperience, Double latitude, Double longitude, String profilepicture, String skills, String description) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailid = emailid;
        this.phonenum = phonenum;
        this.postcode = postcode;
        this.pending = pending;
        this.totalrate = totalrate;
        this.numrate = numrate;
        this.avrate = avrate;
        this.address = address;
        this.yearsofexperience = yearsofexperience;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profilepicture = profilepicture;
        this.skills = skills;
        this.description = description;
    }

    String phonenum;
    String postcode;
    Boolean pending;
    int totalrate;
    int numrate;
    float avrate;
    String address;
    float yearsofexperience;
    Double latitude;
    Double longitude;
    String profilepicture, skills, description;



    public ServiceProviders2(String fname,String lname, String profile_picture, String skills) {
        this.firstname = fname;
        this.lastname = lname;
        this.profilepicture = profile_picture;
        this.skills = skills;
    }

    public ServiceProviders2( String skills,String profilepicture) {

        this.skills = skills;
        this.profilepicture = profilepicture;
    }


// Empty constructore

    public  ServiceProviders2()  {

    }
// constructor for search
    public ServiceProviders2(String firstname, String lastname, String emailid, String phonenum, String address,float yearofexp,String postcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailid = emailid;
        this.phonenum = phonenum;
        this.address = address;
        this.yearsofexperience = yearofexp;
        this.postcode = postcode;
        this.numrate =0;
        this.totalrate=0;
    }

    //constructor of entering data to firebase
    public ServiceProviders2(String firstname, String lastname, String emailid, String phonenum,float yearofexp,String description,String skills,String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailid = emailid;
        this.phonenum = phonenum;
        this.yearsofexperience = yearofexp;
        this.skills = skills;
        this.description = description;
        this.address = address;
        this.numrate =0;
        this.totalrate=0;
    }



    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getTotalrate() {
        return totalrate;
    }

    public void setTotalrate(int totalrate) {
        this.totalrate = totalrate;
    }

    public int getNumrate() {
        return numrate;
    }

    public void setNumrate(int numrate) {
        this.numrate = numrate;
    }

    public float getAvrate() {
        return avrate;
    }

    public void setAvrate(float avrate) {
        this.avrate = avrate;
    }


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public float getYearsOfExperience() {
        return yearsofexperience;
    }

    public void setYearsOfExperience(float yearsofexperience) {
        this.yearsofexperience = yearsofexperience;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPhonenum() {
        return phonenum;
    }



}
