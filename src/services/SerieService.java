/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import database.DatabaseConnexion;
import interfaces.IServiceSerie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Category;
import models.Season;
import models.Serie;
import models.User;

/**
 *
 * @author Lord Solari
 */
public class SerieService implements IServiceSerie{
    
    //var
    DatabaseConnexion instance = DatabaseConnexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public int createSerie(Serie s) {
        int res=0;
        //request

        String SQL_INSERT = "INSERT INTO SERIE (`name`, `description`, `image_local_url`, `trailer`, `release_date`, `rating`, `views_count`,`created_at`, `studio_name`)"
                + " VALUES ('" + s.getName() + "','" + s.getDescription() + "','" + s.getImage_local_url() + "','" + s.getTrailer() + "','" + s.getRelease_date() + "'," + s.getRating() + "," + s.getViews_count() + ",'" + String.valueOf(LocalDate.now()) + "','" + s.getStudio_name() + "')";
        Statement ste;
        try {
            ste = cnx.createStatement();
            System.out.println(SQL_INSERT);
            ste.executeUpdate(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            System.out.println("Serie created !");
            try (ResultSet generatedKeys = ste.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    res= (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {

            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        }
        return res;
    }
        

    
   @Override
    public List<Category> getSerieCategories(int serieID) {
        ArrayList<Category> categories = new ArrayList<>();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT category.id,category.name FROM category,serie_category where serie_category.serie_id = '"+serieID+"' and category.id=serie_category.category_id";
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

    public List<Serie> userFavoriteSeries(int userID) {
        ArrayList<Serie> series = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT serie.* FROM serie,interest where interest.user_id = '"+userID+"' and serie.id=interest.serie_id and interest.is_favor = 1";
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

    public List<Season> getSerieSeasons(int serieID) {
        ArrayList<Season> seasons = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM season where serie_id = '"+serieID+"' ";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                seasons.add(new Season(rs.getInt(1),rs.getInt(2), rs.getString("name"),rs.getString("description"),rs.getString("image_url"),rs.getString("trailer_url")));

            }

            System.out.println(seasons);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return seasons;
    }




    @Override
    public void cleanAllSerieCategories(int id) {
        String req = "DELETE FROM serie_category WHERE serie_id ='"+id+"'";
        Statement ste;
        try {

            ste = cnx.createStatement();
            System.out.println(req);
            ste.executeUpdate(req);
            System.out.println("Serie categories cleaned "+id );


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<Serie> readSeries() {
        ArrayList<Serie> series = new ArrayList<>();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM serie";
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

    public List<Serie> searchSerie(String search) {
        ArrayList<Serie> series = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM serie WHERE name LIKE '%"+search+"%' OR description LIKE '%"+search+"%' OR release_date LIKE '%"+search+"%' OR studio_name LIKE '%"+search+"%'";
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

    public List<Serie> getTopRatedSeries() {
        ArrayList<Serie> series = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM serie order by rating DESC limit 6";
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

    public List<Serie> getMostWatchedSeries() {
        ArrayList<Serie> series = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM serie order by views_count DESC limit 6";
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

    public List<Serie> getLastReleased() {
        ArrayList<Serie> series = new ArrayList<>();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM serie order by release_date DESC limit 6";
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
   public Serie getSerieById(int id){
       Serie serie=new Serie();
      try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM serie"+" where ID = '"+id+"'";
            ResultSet rs = st.executeQuery(req);
             while (rs.next()) { 
            serie=new Serie(rs.getInt(1), rs.getString("name"), rs.getString("description"), rs.getString("image_local_url"), rs.getString("trailer"), rs.getString("release_date"),rs.getFloat("rating"),rs.getInt("views_count"),rs.getString("created_at"),rs.getString("studio_name"));
                }
         
            System.out.println(serie);
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
       return serie;
   }
   
      @Override
   public void addSerieCategory(int serieId,int CategoryId){
        String req = "INSERT INTO `serie_category` (`serie_id`,`category_id`) VALUES('"+serieId+"','"+CategoryId+"')";
         Statement ste;
      try {

              ste = cnx.createStatement();
              System.out.println(req);
             ste.executeUpdate(req);
            System.out.println("Category added to serie "+serieId );
            
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      
   }
   @Override
   public void updateSerie(Serie s, int id){
         String requete="UPDATE SERIE SET name='"+s.getName()+"',description='"+s.getDescription()+"',image_local_url='"+s.getImage_local_url()+"',trailer='"+s.getTrailer()+"',release_date='"+s.getRelease_date()+"',rating="+s.getRating()+",views_count="+s.getViews_count()+",studio_name='"+s.getStudio_name()+"'  where ID = '"+id+"'";
         
         try{
             Statement ste = cnx.createStatement();
             System.out.println(requete);
               ste.executeUpdate(requete);
          
           
            System.out.println("Serie updated !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
    @Override
     public void deleteSerie(int id) {
       
       
            String requete = "delete from SERIE where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Serie deleted !");
        } catch (SQLException ex) {
          
            System.out.println("Error while deleting "+ex.getMessage());
        }
       
    }
}
