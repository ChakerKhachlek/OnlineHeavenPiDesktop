/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.DatabaseConnexion;
import interfaces.IServiceSeason;
import models.Episode;
import models.Season;
import models.Serie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class SeasonService implements IServiceSeason{
    
    DatabaseConnexion instance = DatabaseConnexion.getInstance();
    Connection cnx = instance.getCnx();
    
    public SeasonService() {
     
    }
 public void addSeason(Season p){
        String query ="insert into season(serie_id,name,description,image_url, trailer_url) values('"+p.getSerie_id()+"','"+p.getName()+"','"+p.getDescription()+"','"+p.getImage_url()+"','"+p.getTrailer_url()+"')";
        
            Statement ste;
        try {
            ste = cnx.createStatement();
             ste.executeUpdate(query);
            System.out.println("Test Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        
    }

    public List<Episode> getSeasonEpisodes(int seasonsID) {
        ArrayList<Episode> episodes = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM episode where season_id = '"+seasonsID+"' ";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                episodes.add(new Episode(rs.getInt(1),rs.getInt(2), rs.getString("name"), rs.getInt(4), rs.getString("video_url")));

            }
            System.out.println(episodes);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return episodes;
    }

 public List<Season> readSeasons(){
        List<Season> season = new ArrayList<>();
        String sql ="select * from season";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               Season p = new Season();
                p.setId(rs.getInt("id"));
                p.setSerie_id(rs.getInt(2));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setImage_url(rs.getString("image_url"));
                p.setTrailer_url(rs.getString("trailer_url"));
               season.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return season;
    }

 public void updateSeason(String name, int id){
                String requete="UPDATE season SET Name='"+name+"' where ID = '"+id+"'";
         

         
         try{
             Statement ste = cnx.createStatement();
               ste.executeUpdate(requete);
          
           
            System.out.println("season modifié");
        } catch (SQLException ex) {
            Logger.getLogger(SeasonService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
 public void deleteSeason(int id) {
       
       
            String requete = "delete from season where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
       
    }



 


}

