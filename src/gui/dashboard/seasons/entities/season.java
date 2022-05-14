/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons.entities;

/**
 *
 * @author kalil
 */
public class season {
    
    private int id;
    private serie sert;
    private String name;
    private String description;
    private String image_url;
    private String trailer_url;
    private String finished;

    public season() {
    }

    public season(int id) {
        this.id = id;
    }

    public season(int id, serie sert, String name, String description, String image_url, String trailer_url, String finished) {
        this.id = id;
        this.sert = sert;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.trailer_url = trailer_url;
        this.finished = finished;
    }

    public season(serie sert, String name, String description, String image_url, String trailer_url, String finished) {
        this.sert = sert;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.trailer_url = trailer_url;
        this.finished = finished;
    }

    public season(int id, String name, String description, String image_url, String trailer_url, String finished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.trailer_url = trailer_url;
        this.finished = finished;
    }

    public season(String name, String description, String image_url, String trailer_url, String finished) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.trailer_url = trailer_url;
        this.finished = finished;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public serie getsert() {
        return sert;
    }

    public void setsert(serie sert) {
        this.sert = sert;
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

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "season{" + "id=" + id + ", sert=" + sert + ", name=" + name + ", description=" + description + ", image_url=" + image_url + ", trailer_url=" + trailer_url + ", finished=" + finished + '}';
    }

    public serie getSert() {
        return sert;
    }

    public void setSert(serie sert) {
        this.sert = sert;
    }

    

    

    
     
    
}
