/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.cj.xdevapi.JsonArray;

import java.sql.Array;

/**
 *
 * @author Lord Solari
 */
public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String profile_image;
    private String cover_image;
    private int age;
    private String bio;
    private String country;
    private boolean is_admin;

    private boolean is_verified;

    private String db_token;

    private String username;

    private String userRoles;

    private String password;



    public void setUserRoles(String userRoles) {

        this.userRoles = userRoles;
    }
    public String getUserRoles() {
        return userRoles;
    }


    public User(int id, String first_name, String last_name, String email, String gender, String profile_image, String cover_image, int age, String bio, String country, boolean is_admin) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.profile_image = profile_image;
        this.cover_image = cover_image;
        this.age = age;
        this.bio = bio;
        this.country = country;
        this.is_admin = is_admin;
    }
    public User( String first_name, String last_name, String email, String gender, String profile_image, String cover_image, int age, String bio, String country, boolean is_admin) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.profile_image = profile_image;
        this.cover_image = cover_image;
        this.age = age;
        this.bio = bio;
        this.country = country;
        this.is_admin = is_admin;
    }
    public User( String first_name, String last_name, String email, String gender, String profile_image, String cover_image, int age, String bio, String country) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.profile_image = profile_image;
        this.cover_image = cover_image;
        this.age = age;
        this.bio = bio;
        this.country = country;
    }
    public User(){
        
    }

    public User(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
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


    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getDb_token() {
        return db_token;
    }

    public void setDb_token(String db_token) {
        this.db_token = db_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", cover_image='" + cover_image + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", country='" + country + '\'' +
                ", is_admin=" + is_admin +
                ", is_verified=" + is_verified +
                ", db_token='" + db_token + '\'' +
                ", username='" + username + '\'' +
                ", userRoles=" + userRoles +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
