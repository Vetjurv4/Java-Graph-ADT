/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        //set frame properties
        setTitle("Map");
        setLayout(new BorderLayout());

        //setup top menu bar
        JMenuBar menuBar = new JMenuBar(); //Main menu bar
        JMenu fileMenu = new JMenu("Map File"); //file menu 
        //Menu Items
        JMenuItem openFile = new JMenuItem("import map");
        JMenuItem addPlace = new JMenuItem("Add Place");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        //set onclick for importing map places
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //choose file from the data folder
                JFileChooser mapChooser = new JFileChooser("data");
                int result = mapChooser.showOpenDialog(MainFrame.this);
                //check file
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                    //read coordinates to the map
                }
            }
        });
        //set add place onclick
        addPlace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Panel to ask user for places details and set layout
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

                JLabel lblPlace = new JLabel("Place name:");
                JTextField txtPlace = new JTextField();
                //Latitude
                JLabel lblLatitude = new JLabel("Latitude:");
                JTextField txtLatitude = new JTextField();
                //Longitude
                JLabel lblLongitude = new JLabel("Longitude:");
                JTextField txtLongitude = new JTextField();
                
                //add components to the panel
                inputPanel.add(lblPlace);
                inputPanel.add(txtPlace);
                inputPanel.add(lblLatitude);
                inputPanel.add(txtLatitude);
                inputPanel.add(lblLongitude);
                inputPanel.add(txtLongitude);
                
                //do something
            }

        });
        
        //set items to the file menu
        fileMenu.add(openFile);
        fileMenu.add(addPlace);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu); //add filemenu to the menu bar
        setJMenuBar(menuBar); 
        
        //show map
    }

}
