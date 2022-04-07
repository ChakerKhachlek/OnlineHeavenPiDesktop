/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;



/**
 *
 * @author HP
 */
public class Season {
    
    private int id;
    private int serie_id;
     private String name;
     private String description;
     private String image_url;
     private String trailer_url;

    public Season() {
    }

    public Season(int id, int serie_id, String name, String description, String image_url, String trailer_url) {
        this.id = id;
        this.serie_id = serie_id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.trailer_url = trailer_url;
    }

   

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerie_id() {
        return serie_id;
    }

    public void setSerie_id(int serie_id) {
        this.serie_id = serie_id;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", serie_id=" + serie_id + ", name=" + name + ", description=" + description + ", image_url=" + image_url + ", trailer_url=" + trailer_url + '}';
    }


}
    
    
    

                
