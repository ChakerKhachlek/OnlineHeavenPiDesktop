/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import interfaces.IServiceCategory;

import models.Category;
import database.DatabaseConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import models.Serie;

/**
 *
 * @author Lord Solari
 */
public class CategoryService implements IServiceCategory{
       //var
    DatabaseConnexion instance = DatabaseConnexion.getInstance();
    Connection cnx = instance.getCnx();
       
    @Override
    public void createCategory(Category c) {
        
        //request

       String SQL_INSERT = "INSERT INTO `CATEGORY` (`name`) VALUES ('"+c.getName()+"')";
       Statement ste;
        try{
           
           ste = cnx.createStatement();
             ste.executeUpdate(SQL_INSERT);
            System.out.println("Category created !");
            
            
        } catch (SQLException e) {
           
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                    
          }catch (Exception e) {
            e.printStackTrace();
            
        }
        
        
    }
    
  @Override
    public List<Serie> getCategorySeries(int categoryId) {
        ArrayList<Serie> series = new ArrayList<>();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT serie.* FROM serie,serie_category where serie_category.category_id = '"+categoryId+"' and serie.id=serie_category.serie_id";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                series.add(new Serie(rs.getInt(1), rs.getString("name"), rs.getString("description"), rs.getString("image_local_url"), rs.getString("trailer"), rs.getString("release_date"),rs.getFloat("rating"),rs.getInt("views_count"),rs.getString("created_at"),rs.getString("studio_name")));
                
            }
            System.out.println(series);
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return series;
    }
    
    @Override
      public Category getCategoryById(int id){
       Category category=new Category();
      try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM category"+" where ID = '"+id+"'";
            ResultSet rs = st.executeQuery(req);
             while (rs.next()) { 
            category=new Category(rs.getInt(1), rs.getString("name"));
                }
         
            System.out.println(category);
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
       return category;
   }

    @Override
    public List<Category> readCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM category";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                categories.add(new Category(rs.getInt(1), rs.getString("name")));
                
            }
            
            System.out.println(categories);
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return categories;
    }
    @Override
    public void updateCategory(String name, int id){
         String requete="UPDATE CATEGORY SET name='"+name+"' where ID = '"+id+"'";
         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("Catgory updated !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
    
    @Override
     public void deleteCategory(int id) {
       
       
            String requete = "delete from CATEGORY where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("category deleted !");
        } catch (SQLException ex) {
          
            System.out.println("Error while deleting "+ex.getMessage());
        }
       
    }

    

    
 
}
