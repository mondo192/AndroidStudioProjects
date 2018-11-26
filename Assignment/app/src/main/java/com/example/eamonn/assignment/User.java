package com.example.eamonn.assignment;

import android.util.Log;

public class User {

    private long id;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public User() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        Log.i("User", "Inserting name into user object");
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
