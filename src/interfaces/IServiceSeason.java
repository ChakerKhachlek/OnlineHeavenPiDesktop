/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Season;

/**
 *
 * @author Lord Solari
 */
public interface IServiceSeason {
     public void addSeason(Season s);
    public List<Season> readSeasons();
    public void updateSeason(String name, int id);
    public void deleteSeason(int i);
}
