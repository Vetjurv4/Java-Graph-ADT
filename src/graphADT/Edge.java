/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphADT;

/**
 *
 * @author LENOVO
 */
public class Edge {

    private Vertex from;
    private Vertex to;
    private double distance;
    private boolean visited;

    /**
     * 
     * @param from
     * @param to
     * @param distance 
     */
    public Edge(Vertex from, Vertex to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.visited = false;
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
    public void setDistance(double distance) {
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
