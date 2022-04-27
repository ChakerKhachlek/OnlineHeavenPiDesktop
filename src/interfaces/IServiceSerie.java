/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Category;
import models.Serie;

/**
 *
 * @author Lord Solari
 */
public interface IServiceSerie {
    
    public int createSerie(Serie s);
    public Serie getSerieById(int id);
    public void addSerieCategory(int serieId,int CategoryId);
    public List<Serie> readSeries();
    public void updateSerie(Serie s, int id);
    public void deleteSerie(int id);
    public List<Category> getSerieCategories(int serieID);

    public void cleanAllSerieCategories(int id);
}
