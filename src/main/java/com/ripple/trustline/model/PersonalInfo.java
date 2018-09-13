package com.ripple.trustline.model;

/**
 * @author vreddy
 */
public class PersonalInfo {

    public String name;

    public String email;

    public PersonalInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
