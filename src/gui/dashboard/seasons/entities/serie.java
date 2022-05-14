/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons.entities;


import java.sql.Date;

/**
 *
 * @author kalil
 */
public class serie {
    
    private int id , views_count ;
    private String name , description , image_local_url   , trailer , studio_name;
    private Date realse_date , created_at;
    private Float rating ;

    public serie() {
    }
 public serie(int id, String name) {
        this.id = id;
        this.name = name;
    }
   

  

   

    
    public serie(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
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

    public String getStudio_name() {
        return studio_name;
    }

    public void setStudio_name(String studio_name) {
        this.studio_name = studio_name;
    }

    public Date getRealse_date() {
        return realse_date;
    }

    public void setRealse_date(Date realse_date) {
        this.realse_date = realse_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
    
    

    @Override
    public String toString() {
        return  name +"";
    }

    public Object getSerie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
