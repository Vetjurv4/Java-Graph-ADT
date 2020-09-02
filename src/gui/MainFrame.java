/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import file.MapFile;
import graphADT.Graph;
import graphADT.Vertex;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Velile
 */
public class MainFrame extends JFrame{

    ArrayList<Vertex> places = new ArrayList<Vertex>();
    Graph map = null;
    //MapWindow frame = new MapWindow();

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
        //
        JPanel mapView = new JPanel();
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
                        map = MapFile.readMap(mapChooser.getSelectedFile().getAbsolutePath());

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
                //Destination Place
                JLabel lblDestPlace = new JLabel("Connected to:");
                String[] neighbours = new String[places.size()];
                for (int i = 0; i < neighbours.length; i++) {
                    neighbours[i] = places.get(i).getName();
                }
                JComboBox<String> cmbNeighbours = new JComboBox<>(neighbours);
                cmbNeighbours.setEditable(false);

                //add components to the panel
                inputPanel.add(lblPlace);
                inputPanel.add(txtPlace);
                inputPanel.add(lblLatitude);
                inputPanel.add(txtLatitude);
                inputPanel.add(lblLongitude);
                inputPanel.add(txtLongitude);
                inputPanel.add(lblDestPlace);
                inputPanel.add(cmbNeighbours);

                String[] buttons = {"Add Place", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, inputPanel, "Add place to map:", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                if (option == 0) {

                    //create place
                    Vertex place = new Vertex(txtPlace.getText(), Float.parseFloat(txtLatitude.getText()), Float.parseFloat(txtLongitude.getText()));

                    if (cmbNeighbours.getSelectedItem() != null) {
                        //connect place to it destination
                        Vertex dest = map.getVertex((String) cmbNeighbours.getSelectedItem());//find destination using name
                        map.addBiEdge(place, dest);
                    } else {
                        map.addVertex(place);
                    }
                    JOptionPane.showMessageDialog(null, "Place successfully added...\nView your place from the map");
                }
            }

        });

        //set items to the file menu
        fileMenu.add(openFile);
        fileMenu.add(addPlace);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu); //add filemenu to the menu bar
        setJMenuBar(menuBar);
        pack();
    }

}
