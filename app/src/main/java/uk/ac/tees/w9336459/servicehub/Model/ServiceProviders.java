package uk.ac.tees.w9336459.servicehub.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by AkshayeJH on 15/12/17.
 */

public class ServiceProviders {

    public String name, profilepicture, skills;

    public ServiceProviders() {

    }

    public ServiceProviders(String name, String profile_picture, String skills) {
        this.name = name;
        this.profilepicture = profile_picture;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profile_picture) {
        this.profilepicture = profile_picture;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
