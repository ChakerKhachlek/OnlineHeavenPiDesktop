/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import database.DatabaseConnexion;
import interfaces.IServiceUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author Lord Solari
 */
public class UserService implements IServiceUser{
    DatabaseConnexion instance = DatabaseConnexion.getInstance();
    Connection cnx = instance.getCnx();
    
    Statement sta;
    ResultSet rs;
    
    //ajouter un utilisateur
    public void createUser(User u) {
        String req = "INSERT INTO `user` (`first_name`, `last_name`, `email`, `gender`, `profile_image`,`cover_image`,`age`,`bio`,`country`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getFirst_name());
            st.setString(2, u.getLast_name());
            st.setString(3, u.getEmail() );
            st.setString(4, u.getGender());
            st.setString(5, u.getProfile_image());
            st.setString(6, u.getCover_image());
            st.setInt(7, u.getAge());
            st.setString(8, u.getBio());
            st.setString(9, u.getCountry());
            st.executeUpdate(req);
               System.out.print(st.executeUpdate(req));
            System.out.println("User ajoutée avec succes.");
            
        } catch (SQLException ex) {
         
            ex.printStackTrace();
        }
        
        
    }
    //afficher tous l'utilisateur 
    public List<User> readUser() {
         ArrayList<User> users = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM user";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                users.add(new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("gender"), rs.getString("profile_image"), rs.getString("cover_image"), rs.getInt("age"),rs.getString("bio"), rs.getString("country")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return users;
    }
    
    //fonction supprimer
    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ste = cnx.prepareStatement("DELETE FROM user where id = '"+id+"'");
            ste.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // fonction de mise a jour 
    @Override
    public boolean update(User u) {
        
        String req="UPDATE user SET first_name = '"+u.getFirst_name()+"', last_name = '"+u.getLast_name()+"', email = '"+u.getEmail()+
                "', gender = '"+u.getGender()+"', profile_image = '"+u.getProfile_image()+"', cover_image = '"+u.getCover_image()+"', age = '"+u.getAge()+
                        "', bio = '"+u.getBio()+"', contry = '"+u.getCountry()+"'";
        try{
            if (sta.executeUpdate(req)>0){
                return true;
            }
        }catch(SQLException ex ){
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void clean() {
        try {
            String req = "DELETE FROM user";
            sta.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //afficher liste d'utilisateur avec ordre de nom
    @Override
    public List<User> diasplayAllN() {
        
            String req = "SELECT * FROM user Order by first_name asc";
            List<User> list = new ArrayList();
           
            try {   
                 sta = cnx.createStatement();
                rs=sta.executeQuery(req);
                while(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt(1));
                    u.setFirst_name(rs.getString(2));
                    u.setLast_name(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setGender(rs.getString(5));
                    u.setProfile_image(rs.getString(6));
                    u.setCover_image(rs.getString(7));
                    u.setAge(rs.getInt(9));
                    u.setBio(rs.getString(10));
                    u.setCountry(rs.getString(11));
                    list.add(u);

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return list;
    }
    //afficher liste d'utilisateur avec ordre de prenom
    @Override
    public List<User> diasplayAllP() {
        String req = "SELECT * from user Order by last_name asc";
        List<User> list = new ArrayList();
        try {   
            sta = cnx.createStatement();
                rs=sta.executeQuery(req);
                while(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt(1));
                    u.setFirst_name(rs.getString(2));
                    u.setLast_name(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setGender(rs.getString(5));
                    u.setProfile_image(rs.getString(6));
                    u.setCover_image(rs.getString(7));
                    u.setAge(rs.getInt(9));
                    u.setBio(rs.getString(10));
                    u.setCountry(rs.getString(11));
                    list.add(u);

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return list;
        
    }
    //afficher liste d'utilisateur avec ordre d'age  
    @Override
    public List<User> diasplayAllA() {
        String req="SELECT * from user Order by age asc";
        List<User> list = new ArrayList();
        try {   
            sta = cnx.createStatement();
                rs=sta.executeQuery(req);
                while(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt(1));
                    u.setFirst_name(rs.getString(2));
                    u.setLast_name(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setGender(rs.getString(5));
                    u.setProfile_image(rs.getString(6));
                    u.setCover_image(rs.getString(7));
                    u.setAge(rs.getInt(9));
                    u.setBio(rs.getString(10));
                    u.setCountry(rs.getString(11));
                    list.add(u);

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return list;
    }
    //get by mail
    @Override
    public String getByMail(String name) {
        String m=null;
        String req = "SELECT email from user where first_name ='"+name+"'";
        try {
            
            rs=sta.executeQuery(req);
            rs.next();
            m = rs.getString(4);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
}
