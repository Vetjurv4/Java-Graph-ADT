/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import graphADT.Edge;
import graphADT.Graph;
import graphADT.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import map.Point;

/**
 *
 * @author Velile
 */
public class MapFile {

    /**
     *
     * @param filename
     * @return
     */
    public static Graph readMap(String filename) {
        //
        Graph map = new Graph();
        File mapFile = new File(filename);
        if (!mapFile.exists()) {
            return map;
        }
        Point prevPlace = null;
        Scanner mapScanner = null;
        ArrayList<String> laterAdded = new ArrayList<>();
        try {
            mapScanner = new Scanner(mapFile); //read map file
            while (mapScanner.hasNext()) {
                String line = mapScanner.nextLine(); //read each line in a file

                if (Vertex.isValid(line)) { //PLACE_NAME LATITUDE LONGITUDE
                    //read map data from data
                    StringTokenizer vertexTokens = new StringTokenizer(line);
                    String place = vertexTokens.nextToken();
                    double latitude = Double.parseDouble(vertexTokens.nextToken());
                    double longitude = Double.parseDouble(vertexTokens.nextToken());

                    //read new place (vertex)
                    Point newPlace = new Point(place, latitude, longitude);
                    map.addVertex(newPlace);
                    if(prevPlace != null) map.addEdge(newPlace, newPlace);
				prevPlace = newPlace;
                } else if (Edge.isValid(line)) { //FROM_VERTEX_NAME TO_VERTEX_NAME DISTANCE
                    //read Edge from file
                    StringTokenizer edgeTokens = new StringTokenizer(line);
                    String strFrom = edgeTokens.nextToken();
                    String strTo = edgeTokens.nextToken();

                    //find to and from vertex
                    Point from = map.getVertex(strFrom);
                    Point to = map.getVertex(strTo);
                    //
                    if (from != null && to != null) {
                        map.addBiEdge(from, to);
                    }

                } else {
                    System.out.println("Error could not validate line: " + line);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (mapScanner != null) {
                mapScanner.close();
            }
        }
        return map;
    }

    /**
     *
     * @param map
     * @param mapName
     */
    public static void writeToMap(Graph map, String mapName) {
        if (map != null) {
            //write new place to map file
            File mapFile = new File("data\\" + mapName + ".txt");
            PrintWriter mapWriter = null;
            try {
                mapWriter = new PrintWriter(mapFile);

                //write map 
                for (int i = 0; i < map.getVertices().size(); i++) {
                    //write vertex
                    Vertex tempVertex = map.getVertices().get(i);
                    mapWriter.println(tempVertex.getName() + " " + tempVertex.getLat() + " " + tempVertex.getLon());
                    //write outgoing edges
                    for(int j = 0; j < tempVertex.getOutgoing().size(); j++){
                        Edge tempOutgoing = tempVertex.getOutgoingEdge(i);
                        mapWriter.println(tempOutgoing.getFrom().getName()+" "+tempOutgoing.getTo().getName());
                    }
                    //write incoming edges
                    for(int k = 0; k < tempVertex.getIncoming().size(); k++){
                        Edge tempIncoming = tempVertex.getIncoming(k);
                        mapWriter.println(tempIncoming.getFrom().getName()+" "+tempIncoming.getTo().getName());
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                if (mapWriter != null) {
                    mapWriter.close();
                }
            }
        }

    }

    public static void deleteMap(String filename){
        File deleteFile = new File("data\\"+ filename+".txt");
        deleteFile.delete();
        JOptionPane.showMessageDialog(null, "Deleted: "+filename);
    }
}
