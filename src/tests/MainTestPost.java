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
         System.out.println(ps.fetchAllEpisodePosts("I am watching <a href=\"/streaming/episode/watch/49\"> Season 1 Seven Deadly Sins Episode 1 </a>",114));
           System.out.println(ps.readPostComments(7));
    }
}
