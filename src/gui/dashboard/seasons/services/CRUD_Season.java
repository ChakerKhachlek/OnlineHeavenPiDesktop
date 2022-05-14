/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons.services;

import database.DatabaseConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gui.dashboard.seasons.entities.season;
import gui.dashboard.seasons.entities.serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kalil
 */
public class CRUD_Season implements IService<season>{

    public static CRUD_Season getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Connection cnx = DatabaseConnexion.getInstance().getCnx();
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet ls;
     private static CRUD_Season instance;
   
    private ResultSet rs;
    
    public void ajouter2() throws SQLException{
    String requete = 
            "INSERT INTO season (serie_id ,Name, description, image_url,  trailer_url,  finished)"
            + "VALUES('test1' 'test2' 'test3' 'test4' 'test5' 'test6',6)";
    
    try {
        Statement st= cnx.createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
        System.out.println("Season ajoutée methode 1");
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}
    
    
    public void ajouter(season c) throws SQLException{
    try {
        String requete2 =
                "INSERT INTO categorie (serie_id ,Name, description, image_url,  trailer_url,  finished)"
                + "VALUES(?,?,?,?,?,?)";
        //dynamic values ?,?
        PreparedStatement pst = cnx.prepareStatement(requete2);
        pst.setString(1,c.getName());
        pst.executeUpdate();
         System.out.println("Season ajoutée ");
    } catch (SQLException ex) {
   System.out.println(ex.getMessage());
    }
}


///////////////////////////////ajout reclamation methode 2
public void ajouterp(season abo) throws SQLException{
    String query = "insert into season(serie_id,name,description,image_url,trailer_url,finished) values('"+abo.getsert().getId()+"','"+abo.getName()+"','"+abo.getDescription()+"','"+abo.getImage_url()+"','"+abo.getTrailer_url()+"','"+abo.getFinished()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("season Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}



/////////////////////////////delete reclamation/////////////////////////////////

public void modifier(season C){ 
    try {  
        System.out.println("updating season  : "+C);
        String requete = 
                "UPDATE season SET Name = '"+C.getName()+"' where id="+C.getId();
         System.out.println("updating season  : "+requete);
          pst = cnx.prepareStatement(requete);
        
        //pst.setInt(1,R.getId());
         st= cnx.createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
        
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}

     @Override
    public List<season> getAll() throws SQLException {
        List<season> list = new ArrayList<>();
        try {
            String req = "SELECT  season s serie e where e.id= s.serie_id FROM ";
             st = cnx.createStatement();
             rs = st.executeQuery(req);
            while(rs.next()){
                System.out.println("id = "+rs.getString("id")+  "  serie_id = "+rs.getString("serie_id")+  "  name ="+ rs.getString("name") + "  description =" + rs.getString("description") + "  image_url =" + rs.getString("image_url") + "  trailer_url =" + rs.getString("trailer_url") + "  finished=" + rs.getString("finished"));
                
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

  
   
    

public void supprimer(season R) throws SQLException {
       try {
        String requete = 
        "DELETE from season where id="+R.getId();
        PreparedStatement pst = cnx.prepareStatement(requete);

        //pst.setInt(1,R.getId());
        Statement st= cnx.createStatement();
        st.executeUpdate(requete);
        //execute update for "insert" "update" or "delete requete
        //or executequery for "select" requete
       // System.out.println("deleting reclamation id :"+ R.getId()+" "+requete);
    } catch (SQLException ex) {
System.err.println(ex.getMessage()); 
    }

}
    
    public List<season> ListClasse(  ) {
        List<season> Mylist = new ArrayList<>();
        try {
            String requete = "SELECT * FROM  season s , serie e where e.id= s.serie_id ORDER BY 'name' ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                season pre = new season();
                serie s = new serie(e.getInt("e.id"), e.getString("e.name"));
                
                pre.setsert(s);
                
                pre.setName(e.getString("Name"));
                pre.setDescription(e.getString("description"));
                pre.setImage_url(e.getString("image_url"));
                pre.setTrailer_url(e.getString("Trailer_url"));
                pre.setFinished(e.getString("finished"));

                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

   

    public int countoui() {
    String etud = "oui";
        String sql = "SELECT * FROM season WHERE finished = '" + etud + "' ";
        int count = 0;
        try {

            rs = st.executeQuery(sql);
            while (rs.next()) {
                count++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Season.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahah");
        }

        return count;
    }

    public Integer countnon() {
        String prof = "non";
        String sql = "SELECT * FROM season WHERE finished = '" + prof + "' ";
        int count = 0;
        try {

            rs = st.executeQuery(sql);
            while (rs.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Season.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahah");
        }

        return count;
    }

    

    
}
