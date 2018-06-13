package com.steffen.presentationModel;

import com.steffen.presentationModel.domain.DataSourceAlbum;
import com.steffen.presentationModel.pModel.PModelAlbum;
import com.steffen.presentationModel.views.AlbumDialog;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        /*DataSourceAlbum dataSourceAlbum = new DataSourceAlbum();
        List<DataSourceAlbum> albumList = dataSourceAlbum.getAlbumDataSet();

        PModelAlbum pModel = new PModelAlbum(albumList);
        pModel.setSelectedArtist(3);

        System.out.println(pModel.getArtist() + "\n" +
                            pModel.getComposer() + "\n" +
                            pModel.getTitle() + "\n" +
                            pModel.getSelectedAlbum() + "\n" +
                            pModel.getSelectedArtist() + "\n" +
                            pModel.getWindowTitle() + "\n" +
                            pModel.getAlbumList()[pModel.getSelectedArtist()]
                            );*/

        DataSourceAlbum dataSourceAlbum = new DataSourceAlbum();
        List<DataSourceAlbum> albumList =  dataSourceAlbum.getAlbumDataSet();

        PModelAlbum pModelAlbum = new PModelAlbum(albumList);

        AlbumDialog albumDialog = new AlbumDialog(pModelAlbum);
        albumDialog.createView();




    }
}
