package com.pidev.entities;



import java.time.LocalDate;

public class User {
    
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int age;
    private String bio;
    private String country;
    private String profileImage;
    private String coverImage;
    private String password;
    private String roles;
    private String apiToken;
    private boolean isVerified;
    
    public User(int id, String username, String firstName, String lastName, String email, String gender, int age, String bio, String country, String profileImage, String coverImage, String password, String roles, String apiToken, boolean isVerified) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.bio = bio;
        this.country = country;
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this.password = password;
        this.roles = roles;
        this.apiToken = apiToken;
        this.isVerified = isVerified;
    }

    public User(String username, String firstName, String lastName, String email, String gender, int age, String bio, String country, String profileImage, String coverImage, String password, String roles, String apiToken, boolean isVerified) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.bio = bio;
        this.country = country;
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this.password = password;
        this.roles = roles;
        this.apiToken = apiToken;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
    
    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    

    
}