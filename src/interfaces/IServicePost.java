/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Post;

/**
 *
 * @author Lord Solari
 */
public interface IServicePost {
       //add
    void addPost (Post p);
    void addPost2 (Post p);
    void modifyPost(Post p);
    void deletePost(Post p);
    
    //select all
    List<Post> fetchAllPost();
    
}
