package com.steffen.presentationModel.domain;

import java.util.ArrayList;
import java.util.List;

public class DataSourceAlbum {

    // ~Instance fields
    // ---------------------------------------------------------------------------------------------
    private int id;
    private String artist;
    private String title;
    private boolean classical;
    private String composer;
    private List<DataSourceAlbum> albumList;

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

    // ~Setters
    // ---------------------------------------------------------------------------------------------

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassical(boolean classical) {
        this.classical = classical;
    }

    public void setComposer(String composer) {
        this.composer = composer;
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

        DataSourceAlbum album1 = new DataSourceAlbum(1, "Ärzte", "Junge" , true, "Farin Urlaub");
        DataSourceAlbum album2 = new DataSourceAlbum(2, "Hosen", "Tote Hose" ,false, null);
        DataSourceAlbum album3 = new DataSourceAlbum(3, "Wizo", "Kopfschuss", false, null);
        DataSourceAlbum album4 = new DataSourceAlbum(4, "Bethoven", "Die 5-te", true, null);
        DataSourceAlbum album5 = new DataSourceAlbum(5, "Mozart", "Nachtmusik",true, null);
        DataSourceAlbum album6 = new DataSourceAlbum(6, "Bach J.S.", "Toccata und Fuge ..",true, null);
        DataSourceAlbum album7 = new DataSourceAlbum(7, "Händel", "Wassermusik",true, null);
        DataSourceAlbum album8 = new DataSourceAlbum(8, "Brahms J.", "Guten Abend gut Nacht",true, null);
        DataSourceAlbum album9 = new DataSourceAlbum(9, "Schubert F.", "Erlkönig",true, null);
        DataSourceAlbum album10 = new DataSourceAlbum(10, "Haydn J.", "Die Schöpfung",true, null);

        albumList.add(album1);
        albumList.add(album2);
        albumList.add(album3);
        albumList.add(album4);
        albumList.add(album5);
        albumList.add(album6);
        albumList.add(album7);
        albumList.add(album8);
        albumList.add(album9);
        albumList.add(album10);

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
