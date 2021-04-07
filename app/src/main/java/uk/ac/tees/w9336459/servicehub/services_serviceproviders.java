package uk.ac.tees.w9336459.servicehub;

public class services_serviceproviders {

    private String Name, profile_picture, Skills;

    public services_serviceproviders(String name, String profile_picture, String skills) {
        Name = name;
        this.profile_picture = profile_picture;
        Skills = skills;
    }

    public services_serviceproviders() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }
}

