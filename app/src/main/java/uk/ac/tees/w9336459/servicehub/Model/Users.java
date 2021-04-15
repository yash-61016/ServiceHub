package uk.ac.tees.w9336459.servicehub.Model;

/**
 * Created by AkshayeJH on 15/12/17.
 */

public class Users {

    public String name, image, skills;

    public Users() {

    }

    public Users(String name, String image, String skills) {
        this.name = name;
        this.image = image;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
