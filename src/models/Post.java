/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Lord Solari
 */
public class Post {
    private int id;
    private int user_id;
    private String description;
    private String image_url;
    private String created_at;

    public Post() {
    }

    public Post( String description, String image_url, String created_at) {
        this.description = description;
        this.image_url = image_url;
        this.created_at = created_at;
    }
    
    

    public Post(int id, int user_id, String description, String image_url, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.image_url = image_url;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", user_id=" + user_id + ", description=" + description + ", image_url=" + image_url + ", created_at=" + created_at + '}'+"\n";
    }
    
}
