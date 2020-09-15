/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphADT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import map.Point;

/**
 *
 * @author Velile
 */
public class Graph {

    private ArrayList<Point> vertices;
    private ArrayList<Edge> edges;

    /**
     *
     */
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * 
     * @param vertex
     * @return 
     */
    public boolean addVertex(Point vertex) {
        //int size = vertices.size();
        vertices.add(vertex);
        return true;
    }

    /**
     * 
     * @param from
     * @param to
     * @param distance
     * @return 
     */
    public boolean addEdge(Point from, Point to) {
        Edge edge = new Edge(from, to);
        if (from.getEdgeTo(to) != null) { 
            return false; //alread have a connecting edge
        } else {
            from.addEdge(edge);
            to.addEdge(edge);
            edges.add(edge);
            return true;
        }
    }

    /**
     * 
     * @param from
     * @param to
     * @return 
     */
    public boolean addBiEdge(Point from, Point to) {
        return addEdge(from, to) && addEdge(to, from);
    }

    /**
     * 
     * @param source
     * @return 
     */
    public boolean removeVertex(Point source) {
        if (!vertices.contains(source)) {
            return false;
        }

        vertices.remove(source);

        while (source.getOutgoing().size() > 0) {
            Edge edge = (Edge) source.getOutgoing().get(0);
            source.getOutgoing().remove(edge);
            Point destination = edge.getTo();
            destination.removeEdge(edge);
            edges.remove(edge);
        }

        while (source.getIncoming().size() > 0) {
            Edge edge = (Edge) source.getIncoming().get(0);
            source.getIncoming().remove(edge);
            Point opposite = edge.getFrom();
            opposite.removeEdge(edge);
        }
        return true;
    }

    /**
     * 
     * @param from
     * @param to
     * @return 
     */
    public boolean removeEdge(Point from, Point to) {
        Edge edge = from.getEdgeTo(to);
        if (edge == null) {
            return false;
        } else {
            from.removeEdge(edge);
            to.removeEdge(edge);
            edges.remove(edge);
            return true;
        }
    }

    /**
     * 
     */
    public void resetVisitedVertices() {
        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = (Point) vertices.get(i);
            vertex.setVisited(false);
        }
    }

    /**
     * 
     */
    public void resetVisitedEdges() {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = (Edge) edges.get(i);
            edge.setVisited(false);
        }
    }

    /**
     * 
     * @param vertex 
     */
    public void DFS(Point vertex) {
        for (int i = 0; i < vertex.numEdges(); i++) {
            Edge edge = (Edge) vertex.getOutgoingEdge(i);
            if (!edge.getTo().isVisited()) {
                edge.getTo().setVisited(true);
                DFS(edge.getTo());
            }
        }
    }
    /**
     * 
     * @param vertex 
     */
    public void StackDFS(Point vertex) {
        Stack stack = new Stack();
        stack.push(vertex);
        vertex.setVisited(true);
        while (!stack.isEmpty()) {
            Edge edge = null;
            boolean found = false;

            vertex = (Point) stack.peek();
            for (int i = 0; i < vertex.numEdges(); i++) {
                edge = (Edge) vertex.getOutgoingEdge(i);
                if (!edge.getTo().isVisited()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                stack.pop();
            } else {
                stack.push(edge.getTo());
                edge.getTo().setVisited(true);
            }
        }
    }

    /**
     * 
     * @param vertex 
     */
    public void BFS(Point vertex) {
        Queue<Point> q = new LinkedList<Point>();
        q.add(vertex);
        vertex.setVisited(true);
        while (!q.isEmpty()) {
            vertex = (Point) q.remove();
            for (int i = 0; i < vertex.numEdges(); i++) {
                Edge edge = (Edge) vertex.getIncoming(i);
                if (!edge.getTo().isVisited()) {
                    q.add(edge.getTo());
                    edge.getTo().setVisited(true);
                }
            }
        }
    }

    /**
     * 
     */
    public void TopologicalSort() {
        while (vertices.size() > 0) {
            boolean found = false;

            for (int i = 0; i < vertices.size(); i++) {
                Point vertex = (Point) vertices.get(i);

                if (vertex.getIncoming().size() == 0) {
                    found = true;
                    System.out.println(vertex.getName());
                    removeVertex(vertex);
                    break;
                }
            }
            if (!found) {
                System.out.println("Loop in graph");
                //display
                return;
            }
        }
    }

    /**
     * 
     * @param vertex 
     */
    public void DFSTree(Point vertex) {
        vertex.setVisited(true);
        for (int i = 0; i < vertex.numEdges(); i++) {
            Edge edge = (Edge) vertex.getIncoming(i);
            if (!edge.getTo().isVisited()) {
                System.out.println(" " + vertex.getName() + " " + edge.getTo().getName());
                edge.setVisited(true);
                DFSTree(edge.getTo());
            }
        }
    }

   /**
    * 
    * @param name
    * @return 
    */
    public Point getVertex(String name) {
        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = (Point) vertices.get(i);
            if (vertex.getName().equals(name));
            {
                return vertex;
            }
        }
        return null;
    }

    /**
     * 
     * @return 
     */
    public boolean isEmpty() {
        return (vertices.size() == 0);
    }

    // Display a graphy
    public void display() {
        if (vertices.size() == 0) {
            System.out.println("empty graph\n");
            return;
        }

        System.out.println("");
        for (int i = 0; i < vertices.size(); i++) {
            Point v = (Point) vertices.get(i);
            v.display();
        }
        System.out.println("");
    }
    
    /**
     * 
     * @return 
     */
     public ArrayList<Point> getVertices() {
        return vertices;
    }

     /**
      * 
      * @param vertices 
      */
    public void setVertices(ArrayList<Point> vertices) {
        this.vertices = vertices;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * 
     * @param edges 
     */
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
    

}
