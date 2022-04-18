/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import models.Season;
import services.EpisodeService;

import services.SeasonService;


/**
 *
 * @author kalil
 */
public class MainTestSeasonEpisode {

    
    public static void main(String[] args) {

       SeasonService ps = new SeasonService();
       EpisodeService pe = new EpisodeService();
       System.out.println(ps.readSeasons());
       System.out.println(pe.show_episodes());
       
        
        
    }
    
}
