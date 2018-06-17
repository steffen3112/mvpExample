package com.steffen.presentationModel.views;

import com.steffen.presentationModel.domain.DataSourceAlbum;
import com.steffen.presentationModel.pModel.PModelAlbum;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDialog {


    // ~Instance fields
    // ---------------------------------------------------------------------------------------------
    private boolean isLoadingView = false;
    private PModelAlbum pModelAlbum;
    private JFrame viewFrame;
    private JComboBox jComboBoxAlbums;
    private JTable table;
    private JTextField textFieldArtist, textFieldTitle, textFieldComposer;
    private JCheckBox checkBoxClassical;
    private List<String> artists = new ArrayList<String>();
    private List<Integer> id = new ArrayList<Integer>();
    private List<String[]> values = new ArrayList<String[]>();
    private JButton buttonApply, buttonCancel, buttonAddAlbum;



    // ~Constructors
    // ---------------------------------------------------------------------------------------------

    public AlbumDialog(PModelAlbum pModelAlbum) {
        this.pModelAlbum = pModelAlbum;
    }

    // ~Getters
    // ---------------------------------------------------------------------------------------------

    public boolean isLoadingView() {
        return isLoadingView;
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


        JScrollPane leftPanel = new JScrollPane(table);
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        //TODO: Refactor Layout creation
        GridBagConstraints firstCol = new GridBagConstraints();
        firstCol.gridwidth = GridBagConstraints.REMAINDER;
        firstCol.weightx = 1.0;
        firstCol.fill = GridBagConstraints.HORIZONTAL;
        firstCol.insets = new Insets(5,20,5,5);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        textFieldArtist = new JTextField("default artist");
        textFieldComposer = new JTextField(pModelAlbum.getComposer());
        textFieldComposer.setEnabled(false);
        textFieldComposer = new JTextField("ggg");
        textFieldArtist.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                if(!textFieldComposer.isEnabled())
                    textFieldComposer.setEnabled(true);
            }

            public void keyReleased(KeyEvent e) {

            }
        });
        textFieldTitle = new JTextField("default title");
        textFieldTitle.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                if(!textFieldComposer.isEnabled())
                    textFieldComposer.setEnabled(true);
            }

            public void keyReleased(KeyEvent e) {

            }
        });
        checkBoxClassical = new JCheckBox("classical", isLoadingView);
        buttonApply = new JButton("Apply");
        buttonApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Apply Changed");
                syncWithPmod();
            }
        });
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldArtist.setText(pModelAlbum.getArtist());
                textFieldComposer.setText(pModelAlbum.getComposer());
                textFieldTitle.setText(pModelAlbum.getTitle());
                checkBoxClassical.setSelected(pModelAlbum.isClassical());
            }
        });
        buttonAddAlbum = new JButton("Add new Album");
        buttonAddAlbum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        rightPanel.add(textFieldArtist, firstCol);
        rightPanel.add(textFieldTitle, firstCol);
        rightPanel.add(checkBoxClassical, firstCol);
        rightPanel.add(textFieldComposer, firstCol);
        rightPanel.add(buttonApply, firstCol);
        rightPanel.add(buttonCancel, firstCol);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

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
        JButton jButton = new JButton("push");
        viewFrame.add(jButton);

    }

    private void saveToPmod() {
        String newArtist = textFieldArtist.getText();
        String newTitle = textFieldTitle.getText();
        boolean newClassical = checkBoxClassical.isSelected();
        String newComposer = textFieldComposer.getText();

        pModelAlbum.updateModel(newArtist, newTitle, newClassical, newComposer);


    }

    private void loadFromPmod() {

        if(!isLoadingView()) {

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    System.out.println(row + " : " + col + "data: " + table.getValueAt(row,col));
                    int selectedRow = table.getSelectedRow();
                    String id = (String) table.getValueAt(selectedRow, 0);

                    //DataSourceAlbum album = pModelAlbum.queryDataSource(id);

                    pModelAlbum.setSelectedArtist(Integer.parseInt(id)-1);
                    System.out.println("SELECTED ARTIST " + pModelAlbum.getSelectedArtist());
                    String newArtist = pModelAlbum.getArtist();
                    String newTitle = pModelAlbum.getTitle();
                    String newComposer = pModelAlbum.getComposer();
                    boolean newClassical = pModelAlbum.isClassical();

                    textFieldArtist.setText(newArtist);
                    textFieldTitle.setText(newTitle);
                    textFieldComposer.setText(newComposer);
                    textFieldComposer.setEnabled(pModelAlbum.isComposerFieldEnabled());
                    checkBoxClassical.setSelected(newClassical);
                    System.out.println(pModelAlbum.toString());

                }
            });
            isLoadingView = true;
        }
        else return;

    }



    private void syncWithPmod() {
        if(isLoadingView()) {
            saveToPmod();
            loadFromPmod();
        }
    }



}
