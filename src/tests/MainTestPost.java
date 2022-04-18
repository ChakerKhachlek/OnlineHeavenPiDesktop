/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import services.PostService;

/**
 *
 * @author Lord Solari
 */
public class MainTestPost {
       public static void main(String[] args) {
         PostService pp=new PostService();
          System.out.println(pp.fetchAllPost());
    }
}
