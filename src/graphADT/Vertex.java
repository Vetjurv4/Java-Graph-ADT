/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphADT;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Vertex {

    private ArrayList<Edge> outgoing;
    private ArrayList<Edge> incoming;
    private String name;
    private boolean visited;
    private float lat;
    private float lon;

    /**
     *
     * @param name
     */
    public Vertex(String name, float lat, float lon) {
        outgoing = new ArrayList<>();
        incoming = new ArrayList<>();
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.visited = false;
    }

    /**
     *
     */
    public Vertex(float lat, float lon) {
        outgoing = new ArrayList<>();
        incoming = new ArrayList<>();
        this.name = "";
        this.lat = lat;
        this.lon = lon;
        this.visited = false;
    }

    /**
     * Add Edge to this vertex
     *
     * @param edge
     * @return
     */
    public boolean addEdge(Edge edge) {
        if (edge.getFrom() == this) {
            outgoing.add(edge);
        } else if (edge.getTo() == this) {
            incoming.add(edge);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Remove edge from this vertex
     *
     * @param edge
     * @return
     */
    public boolean removeEdge(Edge edge) {
        if (edge.getFrom() == this) {
            outgoing.remove(edge);
        } else if (edge.getTo() == this) {
            incoming.remove(edge);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Check if edge belongs to this vertex
     *
     * @param edge
     * @return
     */
    public boolean hasEdge(Edge edge) {
        if (edge.getFrom() == this) {
            return outgoing.contains(edge);
        } else if (edge.getTo() == this) {
            return incoming.contains(edge);
        } else {
            return false;
        }
    }

    /**
     * get the edge that connects this vertex to the destination vertex
     *
     * @param destination
     * @return
     */
    public Edge getEdgeTo(Vertex destination) {
        for (int i = 0; i < outgoing.size(); i++) {
            Edge edge = (Edge) outgoing.get(i);
            if (edge.getTo() == destination) {
                return edge;
            }
        }
        return null;
    }

    /**
     * get distance from this vertex to the destination vertex
     *
     * @param destination
     * @return
     */
    public double getDistanceTo(Vertex destination) {
        if (destination == this) {
            return 0;
        }

        Edge edge = getEdgeTo(destination);
        if (edge != null) {
            return edge.getDistance();
        } else {
            return 0;
        }
    }

    /**
     * Get outgoing edge at a certain index
     *
     * @param i
     * @return
     */
    public Edge getOutgoingEdge(int i) {
        if ((i >= 0) && (i < outgoing.size())) {
            return outgoing.get(i);
        }
        return null;
    }

    /**
     * get incoming edge at a certain index
     *
     * @param i
     * @return
     */
    public Edge getIncoming(int i) {
        if ((i >= 0) && (i < incoming.size())) {
            return incoming.get(i);
        }
        return null;
    }

    /**
     * get the total number of outgoing edges
     *
     * @return
     */
    public int numEdges() {
        return outgoing.size();
    }

    /**
     * Checks if we have edge to the destination vertex
     *
     * @param destination
     * @return
     */
    public boolean hasEdgeTo(Vertex destination) {
        return (getEdgeTo(destination) != null);
    }

    /**
     *
     * @return
     */
    public ArrayList<Edge> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(ArrayList<Edge> outgoing) {
        this.outgoing = outgoing;
    }

    public ArrayList<Edge> getIncoming() {
        return incoming;
    }

    public void setIncoming(ArrayList<Edge> incoming) {
        this.incoming = incoming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // Display the vertex
    public void display() {
        System.out.print(name + ":");
        for (int i = 0; i < incoming.size(); i++) {
            Edge e = (Edge) incoming.get(i);
            System.out.print(" \t" + e.getTo().getName() + " " + e.getDistance());
        }
        System.out.println("");
    }

    /**
     * 
     * @return 
     */
    public String toString() {
        return "";
    }
    
    /**
     * 
     * @return 
     */
     public float getLat() {
        return lat;
    }

     /**
      * 
      * @param lat 
      */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return 
     */
    public float getLon() {
        return lon;
    }

    /**
     * 
     * @param lon 
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

}
