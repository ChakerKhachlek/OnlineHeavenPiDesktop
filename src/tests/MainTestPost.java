/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import models.Post;
import services.PostService;

import java.time.LocalDate;

/**
 *
 * @author Lord Solari
 */
public class MainTestPost {
       public static void main(String[] args) {
         PostService ps=new PostService();
         Post post =new Post();
         post.setDescription("I am watching "+ "<a href=\"/streaming/episode/watch/"+49+"\">"+" Season 1  "+" Episode 1"+"</a>");
         post.setImage_url("https://www.nautiljon.com/images/more/01/40/141704.jpg");
         post.setUser_id(114);
         post.setCreated_at(String.valueOf(LocalDate.now()));
         ps.addPost(post);

          System.out.println(ps.fetchAllPost());
    }
}
