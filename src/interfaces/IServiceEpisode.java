/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Episode;

/**
 *
 * @author Lord Solari
 */
public interface IServiceEpisode {
    
    public void create_episode(Episode e);
    public List<Episode> show_episodes();
    public void update_episode(Episode e);
    public void delete_episode(int i);
    public void updatev2_episode(int i);
    public String recherche_episode(String nom);
    public boolean url_test(String url);
    public String imposer_form_url(String url);
    public boolean id_unique(int id);
    public int id_auto();
    public boolean numero_episode_unique(String nom_episode,int num);
    public int create_episode_final(Episode e);
    public void delete_episode_f(String nom, int num);
    public void update_episode_final(Episode e);
    
    
}
