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
        ArrayList<String> upcomingPlaces = new ArrayList<>();
        File mapFile = new File(filename);
        if (!mapFile.exists()) {
            return map;
        }

        Scanner mapScanner = null;
        try {
            mapScanner = new Scanner(mapFile); //read map file
            while (mapScanner.hasNext()) {
                String line = mapScanner.nextLine(); //read each line in a file

                if (Vertex.isValid(line)) { //PLACE_NAME LATITUDE LONGITUDE ?DEST_PLACE_NAME
                    //read map data from data
                    StringTokenizer vertexTokens = new StringTokenizer(line);
                    String place = vertexTokens.nextToken();
                    float latitude = Float.parseFloat(vertexTokens.nextToken());
                    float longitude = Float.parseFloat(vertexTokens.nextToken());
                    String dest = vertexTokens.nextToken();
                    
                    //read new place (vertex)
                    Vertex newPlace = new Vertex(place, latitude, longitude);
                    //add place to map
                    if (dest.equals("")) {
                        map.addVertex(newPlace);
                    } else {
                        //connected places
                        Vertex destPlace = map.getVertex(dest);
                        //destination not yet read from file
                        if (destPlace == null) {
                            //if not already noted
                            if (!upcomingPlaces.contains(dest)) {
                                upcomingPlaces.add(dest);
                            }
                        } else {
                            map.addBiEdge(newPlace, destPlace);
                        }
                    }
                }else if(Edge.isValid(line)){ //FROM_VERTEX_NAME TO_VERTEX_NAME DISTANCE
                    //read Edge from file
                    StringTokenizer edgeTokens = new StringTokenizer(line);
                    String from = edgeTokens.nextToken();
                    String to = edgeTokens.nextToken();
                    
                    Vertex vertexFrom = map.getVertex(from);
                    Vertex vertexTo = map.getVertex(to);
                    
                }else{
                    System.out.println("Error could not validate line");
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

    public static void writeToMap(Graph map, String mapName) {
        if (map != null) {
            //write new place to map file
            File mapFile = new File("data\\" + mapName + ".txt");
            PrintWriter mapWriter = null;
            try {
                mapWriter = new PrintWriter(mapFile);

                //write map
                for (int i = 0; i < map.getVertices().size(); i++) {
                    if (map.getVertices().get(i).getOutgoing().size() > 0) {
                        for (int j = 0; j < map.getVertices().get(i).getOutgoing().size(); j++) {

                        }
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

}
