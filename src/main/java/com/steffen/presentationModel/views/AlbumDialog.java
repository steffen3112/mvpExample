package com.steffen.presentationModel.views;

import com.steffen.presentationModel.domain.DataSourceAlbum;
import com.steffen.presentationModel.pModel.PModelAlbum;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AlbumDialog {


    // ~Instance fields
    // ---------------------------------------------------------------------------------------------
    private boolean isLoadingView = false;
    PModelAlbum pModelAlbum;
    JScrollPane leftPanel;
    JPanel rightPanel;
    JFrame viewFrame;
    JSplitPane splitPane;
    JComboBox jComboBoxAlbums;
    JTable table;
    JTextField textFieldArtist, textFieldTitle, textFieldComposer;
    JCheckBox checkBoxClassical;
    JButton buttonApply, buttonCancel, jButton;
    List<String> artists = new ArrayList<String>();
    List<Integer> id = new ArrayList<Integer>();
    List<String[]> values = new ArrayList<String[]>();



    // ~Constructors
    // ---------------------------------------------------------------------------------------------

    public AlbumDialog(PModelAlbum pModelAlbum) {
        this.pModelAlbum = pModelAlbum;
    }

    // ~Getters
    // ---------------------------------------------------------------------------------------------

    public boolean isLoadingView() {
        return !isLoadingView;
    }

    // ~Setters
    // ---------------------------------------------------------------------------------------------


    // ~Overrides
    // ---------------------------------------------------------------------------------------------


    // ~Methods
    // ---------------------------------------------------------------------------------------------

    public void createView() {

        viewFrame = new JFrame("Anzeige");

        //TODO: Load data from Presentation Model in JTable
        List<DataSourceAlbum> dataSourceAlbumList = pModelAlbum.getDataSourceAlbums();
        for(DataSourceAlbum album : dataSourceAlbumList) {
            artists.add(album.getArtist());
            id.add(album.getId());
        }

        for(int i = 0; i<id.size(); i++) {
            values.add(new String[]{String.valueOf(id.get(i)), artists.get(i)});
        }


        final Object headers[] = { "#" , "Artist" };
        table = new JTable(values.toArray(new Object[][] {}), headers);


        leftPanel = new JScrollPane(table);
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        //TODO: Refactor Layout creation
        GridBagConstraints firstCol = new GridBagConstraints();
        firstCol.gridwidth = GridBagConstraints.REMAINDER;
        firstCol.weightx = 1.0;
        firstCol.fill = GridBagConstraints.HORIZONTAL;
        firstCol.insets = new Insets(5,20,5,5);

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        textFieldArtist = new JTextField("default artist");
        textFieldTitle = new JTextField("default title");
        checkBoxClassical = new JCheckBox("classical", isLoadingView);
        textFieldComposer = new JTextField("ggg");
        textFieldComposer.setEnabled(false);
        buttonApply = new JButton("Apply");
        buttonCancel = new JButton("Cancel");
        rightPanel.add(textFieldArtist, firstCol);
        rightPanel.add(textFieldTitle, firstCol);
        rightPanel.add(checkBoxClassical, firstCol);
        rightPanel.add(textFieldComposer, firstCol);
        rightPanel.add(buttonApply, firstCol);
        rightPanel.add(buttonCancel, firstCol);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

        splitPane.setDividerLocation(300);

        viewFrame.add(splitPane);
        viewFrame.pack();
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setSize(new Dimension(800,500));
        viewFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //createViewElements();
        loadFromPmod();
        viewFrame.setVisible(true);
    }

    private void createViewElements() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        jButton = new JButton("push");
        viewFrame.add(jButton);

    }

    private void saveToPmod() {
        //TODO: write function which saves the changed view fields to the Presentation Model
    }

    private void loadFromPmod() {
        //TODO: write function which retrieves the necessary data from the Presentation Model
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                System.out.println(row + " : " + col + "data: " + table.getValueAt(row,col));
                int selectedRow = table.getSelectedRow();
                String id = (String) table.getValueAt(selectedRow, 0);
                DataSourceAlbum album = pModelAlbum.queryDataSource(id);

                String newArtist = album.getArtist();
                String newTitle = album.getTitle();
                String newComposer = album.getComposer();
                boolean newClassical = album.isClassical();

                textFieldArtist.setText(newArtist);
                textFieldTitle.setText(newTitle);
                textFieldComposer.setText(newComposer);
                checkBoxClassical.setSelected(newClassical);
                System.out.println(album.toString());

            }
        });

    }

    private void syncWithPmod() {
        if(isLoadingView) {
            saveToPmod();
            loadFromPmod();
        }
    }



}
