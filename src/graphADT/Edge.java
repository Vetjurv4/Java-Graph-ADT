/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphADT;

import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class Edge {

    private Vertex from;
    private Vertex to;
    private float distance;
    private boolean visited;

    private static final Pattern EDGE_PATTERN = Pattern.compile("([A-Z, a-z]+(\\-[A-Z, a-z])*)+\\s([A-Z, a-z]+(\\-[A-Z, a-z])*)+");

    
    /**
     *
     * @param from
     * @param to
     */
    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
        //if one of the vertices is null set distance to zero
        this.distance = (from == null || to == null)? 0: Graph.getDistance(from.getLat(), from.getLon(), to.getLat(), to.getLon());
        this.visited = false;
    }
    
    /**
     *
     * @param data
     * @return
     */
    public static boolean isValid(String data) {
        return EDGE_PATTERN.matcher(data).matches();
    }


    /**
     *
     * @return way from
     */
    public Vertex getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(Vertex from) {
        this.from = from;
    }

    /**
     *
     * @return
     */
    public Vertex getTo() {
        return to;
    }

    /**
     *
     * @param to
     */
    public void setTo(Vertex to) {
        this.to = to;
    }

    /**
     *
     * @return
     */
    public double getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "";//System.out.println("edge from " + from.getName() + " to " + to.getName() +  " distance " + distance);
    }

}
