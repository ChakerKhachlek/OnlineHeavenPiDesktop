/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import database.DatabaseConnexion;
import interfaces.IServicePost;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Post;
/**
 *
 * @author Lord Solari
 */
public class PostService implements IServicePost{
    DatabaseConnexion instance = DatabaseConnexion.getInstance();
    Connection cnx = instance.getCnx();
     @Override
    public void addPost(Post p) {
        try {
            String req="INSERT INTO `post`(`description`, `image_url`, `created_at`) VALUES ('"+ p.getDescription() +"','"+ p.getImage_url() +"','"+ p.getCreated_at()+"')";
            
            Statement st= cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ST : Post added with success!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public List<Post> fetchAllPost() {
        List<Post> posts= new ArrayList<>();
        
        String req="select * from post";
        
        try {
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while (rs.next())
                posts.add(new Post(rs.getInt("id"), rs.getInt("user_id"), rs.getString(3), rs.getString(4), rs.getString(5)));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return posts;
    }

    @Override
    public void addPost2(Post p) {
        try {
            String req="INSERT INTO `post`(`description`, `image_url`, `created_at`) VALUES (?,?,?)";
            PreparedStatement pst= cnx.prepareStatement(req);
            pst.setString(1, p.getDescription());
            pst.setString(2, p.getImage_url());
            pst.setString(3, p.getCreated_at());
            pst.executeUpdate();
            System.out.println("PST : Post added!!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void modifyPost(Post p) {
         String query = "update post set description=?, image_url=? where id=?";       
        try{
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, p.getDescription());
            pst.setString(2, p.getImage_url());
            pst.setString(3, p.getCreated_at());
            pst.executeUpdate();
            System.out.println("Post Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
    
    @Override
    public void deletePost(Post p) {
        String query = "delete from post where id=?";
        try{
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, p.getDescription());
            pst.setString(2, p.getImage_url());
            pst.setString(3, p.getCreated_at());
            pst.executeUpdate();
            System.out.println("Product Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
