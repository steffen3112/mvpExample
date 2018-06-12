package com.steffen.presentationModel.pModel;

import com.steffen.presentationModel.domain.DataSourceAlbum;

import java.util.List;

public class PModelAlbum {

    // ~Instance fields
    // ---------------------------------------------------------------------------------------------

    private List<DataSourceAlbum> dataSourceAlbums;
    private int selectedArtist;

    // ~Constructors
    // ---------------------------------------------------------------------------------------------

    public PModelAlbum(List<DataSourceAlbum> dataSourceAlbums) {
        this.dataSourceAlbums = dataSourceAlbums;
        selectedArtist = 0;
    }


    // ~Getters
    // ---------------------------------------------------------------------------------------------

    public DataSourceAlbum getSelectedAlbum() {
        DataSourceAlbum selectedAlbum = dataSourceAlbums.get(selectedArtist);
        return selectedAlbum;
    }


    public int getSelectedArtist() {
        return selectedArtist;
    }

    public String getTitle() {
        return dataSourceAlbums.get(selectedArtist).getTitle();
    }

    public String getArtist() {
        return dataSourceAlbums.get(selectedArtist).getArtist();
    }

    public boolean isClassical() {
        return dataSourceAlbums.get(selectedArtist).isClassical();
    }

    public String getComposer() {
        return dataSourceAlbums.get(selectedArtist).getComposer();
    }

    public String getWindowTitle() {
        return "Album: " + getTitle();
    }

    public boolean isComposerFieldEnabled() {
        return isClassical();
    }

    public boolean isApplyEnabled() {
        return hasRowChanged();
    }

    public boolean isCancelEnabled() {
        return hasRowChanged();
    }

    //TODO: Check if the row has changed
    public boolean hasRowChanged() {
        return false;
    }

    public String[] getAlbumList() {
        String[] result = new String[this.dataSourceAlbums.size()];
        for(int i=0; i < result.length; i++) {
            result[i] = this.dataSourceAlbums.get(i).getArtist();
        }

        return result;
    }

    // ~Setters
    // ---------------------------------------------------------------------------------------------

    public void setSelectedArtist(int selectedArtist) {
        this.selectedArtist = selectedArtist;
    }



}
