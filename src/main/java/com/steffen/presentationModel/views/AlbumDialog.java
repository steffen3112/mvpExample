package com.steffen.presentationModel.views;

import com.steffen.presentationModel.pModel.PModelAlbum;

public class AlbumDialog {


    // ~Instance fields
    // ---------------------------------------------------------------------------------------------
    private boolean isLoadingView = false;

    PModelAlbum pModelAlbum;


    // ~Constructors
    // ---------------------------------------------------------------------------------------------


    // ~Getters
    // ---------------------------------------------------------------------------------------------

    public boolean isLoadingView() {
        return !isLoadingView;
    }


    // ~Overrides
    // ---------------------------------------------------------------------------------------------


    // ~Methods
    // ---------------------------------------------------------------------------------------------

    private void saveToPmod() {
        //TODO: write function which saves the changed view fields to the Presentation Model
    }

    private void loadFromPmod() {
        //TODO: write function which retrieves the necessary data from the Presentation Model
    }

    private void syncWithPmod() {
        if(isLoadingView) {
            saveToPmod();
            loadFromPmod();
        }
    }



}
