/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author inesz
 */
public class Comment {
    
    private int id;
    private int post_id;
    private String content;
    private String created_at;
    private int user_id;

    public Comment() {
    }

    public Comment(int post_id, String content, String created_at, int user_id) {
        this.post_id = post_id;
        this.content = content;
        this.created_at = created_at;
        this.user_id = user_id;
    }

    public Comment(int id, int post_id, String content, String created_at, int user_id) {
        this.id = id;
        this.post_id = post_id;
        this.content = content;
        this.created_at = created_at;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", post_id=" + post_id + ", content=" + content + ", created_at=" + created_at + ", user_id=" + user_id + '}';
    }
    
    
    
}
