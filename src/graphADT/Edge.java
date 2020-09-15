/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphADT;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.regex.Pattern;
import map.Point;

/**
 *
 * @author LENOVO
 */
public class Edge {

    private final static Stroke BASIC_STROKE = new BasicStroke();

    private Point from;
    private Point to;
    private double distance;
    private boolean visited;
    private Color color;
    private Stroke stroke;

    private static final Pattern EDGE_PATTERN = Pattern.compile("([A-Z, a-z]+(\\-[A-Z, a-z])*)+\\s([A-Z, a-z]+(\\-[A-Z, a-z])*)+");

    /**
     *
     * @param from
     * @param to 
     */
    public Edge(Point from, Point to) {
        this(from, to, from.getLon()< 0 ? Color.BLUE : Color.RED, BASIC_STROKE);
    }

    public Edge(Point from, Point to, Color color, Stroke stroke) {
        this.from = from;
        this.to = to;
        //if one of the vertices is null set distance to zero
        this.distance = from.distanceTo(to);
        this.visited = false;
        this.color = color;
        this.stroke = stroke;
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
    public Point getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    public void setFrom(Point from) {
        this.from = from;
    }

    /**
     *
     * @return
     */
    public Point getTo() {
        return to;
    }

    /**
     *
     * @param to
     */
    public void setTo(Point to) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "";//System.out.println("edge from " + from.getName() + " to " + to.getName() +  " distance " + distance);
    }

}
