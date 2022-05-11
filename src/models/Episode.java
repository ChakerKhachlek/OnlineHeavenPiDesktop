/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Lord Solari
 */
public class Episode {

    private int id;
    private int season_id;
    private String name;

    private String video_url;
    private int episode_number;

    public Episode() {

    }

    public Episode(int id,int season_id, String name, int episode_number, String video_url) {
        this.id = id;
        this.season_id=season_id;
        this.name = name;
        this.episode_number = episode_number;
        this.video_url = video_url;
    }
    public Episode(int season_id, String name, int episode_number, String video_url) {

        this.season_id=season_id;
        this.name = name;
        this.episode_number = episode_number;
        this.video_url = video_url;
    }
    public Episode(String name, int episode_number, String video_url) {
        this.name = name;
        this.episode_number = episode_number;
        this.video_url = video_url;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }



    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    //////////////////////////////////////////////////////////////////////
    public int getseason_id() {
        return season_id;
    }

    public void setseason_id(int season_id) {
        this.season_id = season_id;
    }
    ////////////////////////////////////////////////////////////////////////

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    //////////////////////////////////////////////////////////////////////////////

    public int getepisode_number() {
        return episode_number;
    }

    public void setepisode_number(int episode_number) {
        this.episode_number = episode_number;
    }


    /////////////////////////////////////////////////////////////////////

    public String getvideo_url() {
        return video_url;
    }

    public void setvideo_url(String video_url) {
        this.video_url = video_url;
    }



    @Override
    public String toString() {
        return "episode{" + "id=" + id + ", name=" + name + ", episode_number=" + episode_number + ", video_url=" + video_url + "}";
    }

}
