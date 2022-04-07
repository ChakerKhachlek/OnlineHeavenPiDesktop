/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Lord Solari
 */
public class Serie {
    private int id;
    private String name;
    private String description;
    private String image_local_url;
    private String trailer ;
    private String release_date;
    private Float rating;
    private int views_count;
    private String created_at;
    private String studio_name;
    
    private List<Category> categories;

    //empty constructor
    public Serie() {
    }
    
     public Serie( String name, String description, String image_local_url, String trailer, String release_date, Float rating, int views_count, String created_at, String studio_name) {
        this.name = name;
        this.description = description;
        this.image_local_url = image_local_url;
        this.trailer = trailer;
        this.release_date = release_date;
        this.rating = rating;
        this.views_count = views_count;
        this.created_at = created_at;
        this.studio_name = studio_name;
    }
     //constructor will all attributs
    public Serie(int id, String name, String description, String image_local_url, String trailer, String release_date, Float rating, int views_count, String created_at, String studio_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_local_url = image_local_url;
        this.trailer = trailer;
        this.release_date = release_date;
        this.rating = rating;
        this.views_count = views_count;
        this.created_at = created_at;
        this.studio_name = studio_name;
    }

    
    //serie without seasons and created_at
    public Serie(int id, String name, String description, String image_local_url, String trailer, String release_date, Float rating, int views_count, String studio_name) {    
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_local_url = image_local_url;
        this.trailer = trailer;
        this.release_date = release_date;
        this.rating = rating;
        this.views_count = views_count;
        this.studio_name = studio_name;
    }

    //serie without seasons and created_at and id
    public Serie(String name, String description, String image_local_url, String trailer, String release_date, Float rating, int views_count,  String studio_name) {
        this.name = name;
        this.description = description;
        this.image_local_url = image_local_url;
        this.trailer = trailer;
        this.release_date = release_date;
        this.rating = rating;
        this.views_count = views_count;
        this.studio_name = studio_name;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_local_url() {
        return image_local_url;
    }

    public void setImage_local_url(String image_local_url) {
        this.image_local_url = image_local_url;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStudio_name() {
        return studio_name;
    }

    public void setStudio_name(String studio_name) {
        this.studio_name = studio_name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    
    
    
}
