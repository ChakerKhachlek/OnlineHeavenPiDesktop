/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard.seasons.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kalil
 */
public interface IService  <T>{
   
   public void ajouter(T p)throws SQLException;
    public void supprimer(T p)throws SQLException;
    public void modifier(T p)throws SQLException;
    public List<T> getAll()throws SQLException;
    
}
