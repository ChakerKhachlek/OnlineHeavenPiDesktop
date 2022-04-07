/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import models.Season;

import services.SeasonService;


/**
 *
 * @author kalil
 */
public class MainTestSeason {

    
    public static void main(String[] args) {
        Season p = new Season(7,37,"kalil","fff","lkdsgmldqksmlgq","kgldqksgq");
        SeasonService ps = new SeasonService();
        ps.addSeason(p);
        
        System.out.println(ps.readSeasons());
        ps.updateSeason("m",1);
        ps.deleteSeason(27);
        
        
        
    }
    
}
