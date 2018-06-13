package com.steffen.presentationModel.views;

import com.steffen.presentationModel.pModel.PModelAlbum;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlbumDialog {


    // ~Instance fields
    // ---------------------------------------------------------------------------------------------
    private boolean isLoadingView = false;
    //TODO: refactor instance fields
    PModelAlbum pModelAlbum;
    JScrollPane leftPanel;
    JPanel rightPanel;
    JFrame viewFrame;
    JButton jButton;
    JSplitPane splitPane;
    JComboBox jComboBoxAlbums;
    JTable table;
    JTextField textFieldArtist;
    JTextField textFieldTitle;
    JCheckBox checkBoxClassical;
    JTextField textFieldComposer;
    JButton buttonApply;
    JButton buttonCancel;



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
        final Object rows[][] = { {"111111111111111111111111111111111111111"}, {2}, {3}, {4} };
        final Object headers[] = { "Artist" };
        table = new JTable( rows, headers);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                System.out.println(row + " : " + col);
            }
        });

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
        textFieldArtist = new JTextField("Killa");
        textFieldTitle = new JTextField("Lemonade and Buns");
        checkBoxClassical = new JCheckBox("classical", false);
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
    }

    private void syncWithPmod() {
        if(isLoadingView) {
            saveToPmod();
            loadFromPmod();
        }
    }



}
