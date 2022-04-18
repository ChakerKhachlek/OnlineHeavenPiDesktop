/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import database.DatabaseConnexion;
import interfaces.IServiceEpisode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Episode;

/**
 *
 * @author Lord Solari
 */
public class EpisodeService implements IServiceEpisode{
    
    DatabaseConnexion instance = DatabaseConnexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public void create_episode(Episode e) {
        
        //request
        String req;
        req = "INSERT INTO `episode`(`id`,`name`, `episode_number`, `video_url`) VALUES (?,?,?,?) ";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getid());
            st.setString(2, e.getname());
          // System.out.println("episode ajoutée avec succes.");
            st.setInt(3, e.getepisode_number());
            st.setString(4, e.getvideo_url());
         
           st.executeUpdate();
             System.out.println("episode ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }

     @Override
    public List<Episode> show_episodes() {
        ArrayList<Episode> episodes = new ArrayList<>();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM episode";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                episodes.add(new Episode(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
               
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return episodes;
    }
    
    @Override
    public void update_episode(Episode e){
     String req = "UPDATE episode SET name=? , episode_number=? , video_url=? WHERE id=? ";
    try {
        
         PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, e.getname());
            st.setInt(2, e.getepisode_number());
            st.setString(3, e.getvideo_url());
            st.setInt(4, e.getid());
            st.executeUpdate();
            System.out.println("update avec succes");
    } 
    catch (SQLException ex) {
            
            ex.printStackTrace();
    }
    
        

}
    @Override
    public void delete_episode(int i){
          String req = "DELETE FROM `episode` WHERE id=? ";
    try{
    PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, i);
            st.executeUpdate();
            System.out.println("delete");
    }
    catch  (SQLException ex) {
            
            ex.printStackTrace();
    
    }
    
    
    
}
    @Override
    public void updatev2_episode(int i){
     String req = "UPDATE episode SET name=? , episode_number=? , video_url=? WHERE id=? ";
       try {
           Scanner sc = new Scanner(System.in);
             Scanner sa = new Scanner(System.in);
           
           System.out.println("saisie un nom d'episode : ");
           String nom = sc.nextLine();
           
           System.out.println("saisie un numero d'episode : ");
           int numero = sc.nextInt();
           
           
           System.out.println("saisie un url : ");
           String url = sa.nextLine();
          /////////////////  rq: pour chaque insertion de chaine par clavier il faut creer un new scanner
           
         PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, nom);
            st.setInt(2, numero);
            st.setString(3, url);
            st.setInt(4, i);
            st.executeUpdate();
            System.out.println("update avec succes");
    } 
    catch (SQLException ex) {
            
            ex.printStackTrace();
    }
    }
    
    @Override
    public void recherche_episode(String nom){
             int i=0;
     try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM episode";
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {                
                
            if (rs.getString(2).equals(nom))
            { System.out.println(" name : "+rs.getString(2)+"\n episode_number : "+rs.getInt(3)+"\n video_url : "+rs.getString(4)+"\n") ;
            i=1;
           
            } 
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        if (i==0)
        {System.out.println("l'episode n'existe pas");}
    
    
    }
    @Override
    public boolean url_test(String url){
        boolean test=false;
        if (url.substring(0,7).equals("http://"))
            {test=true;}
    
        return test;
    }
    @Override
    public String imposer_form_url(String url){
        if(url_test(url)==false)
        { url="http://"+url; }
         return url;
    }
   
    
    @Override
    public boolean id_unique(int id){
         boolean test=true;
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM episode";
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {                
                
                if(rs.getInt(1)==id)
                { test=false; break; }
               
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
         return test;
    }
    
    @Override
    public int id_auto(){
        int id =1;
        while(id_unique(id)==false)
        {id=id+1;}
        return id;
    }
 
    @Override
    public boolean numero_episode_unique(String nom_episode,int num){
    
           boolean test=true;
         try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM episode";
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {                
                
                if(rs.getString(2).equals(nom_episode)) 
                    {if (rs.getInt(3)==num)
                        { test=false; break;}}
               
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
         return test;
    }
    
    @Override
    public void create_episode_final(Episode e){
    
           String req;
           if(numero_episode_unique(e.getname(),e.getepisode_number())==true){
        req = "INSERT INTO `episode`(`id`,`name`, `episode_number`, `video_url`) VALUES (?,?,?,?) ";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            //st.setInt(1, id_auto());
            //st.setString(2, e.getname());
          // System.out.println("episode ajoutée avec succes.");
            
                { st.setInt(1, id_auto());
            st.setString(2, e.getname());
            st.setInt(3, e.getepisode_number());}
           // else{/*st.setInt(3, 1);st.setString(4, "azerty"); delete_episode(e.getid());*/}
            if(url_test(e.getvideo_url())==true)
                {st.setString(4, e.getvideo_url());}
            else{st.setString(4, imposer_form_url(e.getvideo_url()));}
           st.executeUpdate();
             System.out.println("episode ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
    }
           else{ System.out.println("impossible d'ajouter l'episode"); }
    }
}
