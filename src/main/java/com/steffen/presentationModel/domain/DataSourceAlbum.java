package com.steffen.presentationModel.domain;

import java.util.ArrayList;
import java.util.List;

public class DataSourceAlbum {

    // ~Instance fields
    // ---------------------------------------------------------------------------------------------
    int id;
    String artist;
    String title;
    boolean classical;
    String composer;
    List<DataSourceAlbum> albumList;

    // ~Constructors
    // ---------------------------------------------------------------------------------------------

    public DataSourceAlbum(){};

    public DataSourceAlbum(int id, String artist, String title, boolean classical, String composer) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.classical = classical;
        this.composer = composer;
    }

    // ~Getters
    // ---------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClassical() {
        return classical;
    }

    public String getComposer() {
        return composer;
    }

    // ~Overrides
    // ---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "DataSourceAlbum{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", classical=" + classical +
                ", composer='" + composer + '\'' +
                '}';
    }

    // ~Methods
    // ---------------------------------------------------------------------------------------------

    public List<DataSourceAlbum> getAlbumDataSet() {
        albumList = new ArrayList<DataSourceAlbum>();

        DataSourceAlbum album1 = new DataSourceAlbum(1, "Ärzte", "Junge" , false, null);
        DataSourceAlbum album2 = new DataSourceAlbum(2, "Hosen", "Tote Hose" ,false, null);
        DataSourceAlbum album3 = new DataSourceAlbum(3, "Wizo", "Kopfschuss", false, null);
        DataSourceAlbum album4 = new DataSourceAlbum(4, "Bethoven", "Die 5-te", true, null);
        DataSourceAlbum album5 = new DataSourceAlbum(5, "Mozart", "Nachtmusik",true, null);

        albumList.add(album1);
        albumList.add(album2);
        albumList.add(album3);
        albumList.add(album4);
        albumList.add(album5);

        return albumList;
    }

    public DataSourceAlbum queryDataSource(String id) {


        try {
            int givenId = Integer.parseInt(id);
            return albumList.get(givenId-1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
