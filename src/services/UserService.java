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

import models.Serie;
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

    public User getUserByToken(String token){
        User user =new User();
        user.setEmail("not set");
        String req = "SELECT * from user where db_token ='"+token+"' limit 1";
        try {
            Statement st = cnx.createStatement();
            rs=st.executeQuery(req);
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setProfile_image("http://localhost:8000/uploads/users-images/"+rs.getString("profile_image"));
                user.setCover_image(rs.getString("cover_image"));
                user.setIs_verified(rs.getBoolean("is_verified"));
                user.setUserRoles(rs.getString("roles"));
                user.setDb_token(rs.getString("db_token"));
            }




        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }


    public boolean getIsUserFavoriteSerie(int userId,int serieId){
        boolean res=false;
        String req = "SELECT * from interest where user_id ='"+userId+"' and serie_id = '"+serieId+"' and is_favor = 1";
        ResultSet rs;
        try {
            Statement st = cnx.createStatement();
            rs=st.executeQuery(req);
            if(rs.next()){
                res=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public int getIsUserRatedSerie(int userId,int serieId){
        int res=-1;
        String req = "SELECT * from interest where user_id ='"+userId+"' and serie_id = '"+serieId+"'"+" and rating IS NOT NULL";
        ResultSet rs;
        try {
            Statement st = cnx.createStatement();
            rs=st.executeQuery(req);
            if(rs.next()){
                res=rs.getInt("rating");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public void setUserFavorite(int userId,int serieId){
        UserService userService=new UserService();
        String req="";
        if(userService.getIsUserRatedSerie(userId,serieId) !=-1){
           req = "UPDATE interest set is_favor = 1 where user_id = '"+userId+"' and serie_id = '"+serieId+"'";
        }else{
           req = "INSERT INTO interest (user_id,serie_id,is_favor) values('"+userId+"','"+serieId+"',1)";
        }

        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);


        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setUserUnfavorite(int userId,int serieId){

        UserService userService=new UserService();
        String req="";
        if(userService.getIsUserRatedSerie(userId,serieId) !=-1){
            req = "UPDATE interest set is_favor = 0 where user_id = '"+userId+"' and serie_id = '"+serieId+"'";
        }else{
            req = "INSERT INTO interest (user_id,serie_id,is_favor) values('"+userId+"','"+serieId+"',1)";
        }
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setUserRate(int userId, Serie serie, float rate){
        UserService userService=new UserService();
        String req="";
        if(userService.getIsUserFavoriteSerie(userId,serie.getId())){
            req = "UPDATE interest set rating = '"+rate+"' where user_id = '"+userId+"' and serie_id = '"+serie.getId()+"'";
        }else{
            req = "INSERT INTO interest (user_id,serie_id,rating) values('"+userId+"','"+serie.getId()+"','"+rate+"')";
        }
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            String req2="Update serie set rating = '"+(serie.getRating()+rate)/2+"' where id = '"+serie.getId()+"'";
            st.executeUpdate(req2);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public User getUserByEmail(String email){
        User user =new User();
        user.setEmail("not set");
        String req = "SELECT * from user where email ='"+email+"' limit 1";
        try {
            Statement st = cnx.createStatement();
            rs=st.executeQuery(req);
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setProfile_image("http://localhost:8000/uploads/users-images/"+rs.getString("profile_image"));
                user.setCover_image(rs.getString("cover_image"));
                user.setIs_verified(rs.getBoolean("is_verified"));
                user.setUserRoles(rs.getString("roles"));
                user.setDb_token(rs.getString("db_token"));
            }




        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void updateUserToken(String email,String token){

        String req = "update user set db_token ='"+token+"' where email ='"+email+"'";
        try {
            Statement st = cnx.createStatement();
            int rs=st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
