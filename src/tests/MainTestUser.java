/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import services.UserService;
import models.User;
/**
 *
 * @author Lord Solari
 */
public class MainTestUser {
    
     public static void main(String[] args) {

       
         UserService pu=new UserService();
         User user=new User("Test", "Test", "Test@gmail.com", "Male", "Test", "Test", 20, "waaa", "Tunisia", false);
         pu.createUser(user);
         System.out.println(pu.diasplayAllN());
       
        
        
    }
}
