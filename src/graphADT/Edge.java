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

    public Edge(Vertex from, Vertex to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.visited = false;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String toString() {
        return "";//System.out.println("edge from " + from.getName() + " to " + to.getName() +  " distance " + distance);
    }

}
