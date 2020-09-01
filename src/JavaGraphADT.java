
import graphADT.Graph;
import graphADT.Vertex;
import gui.MainFrame;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class JavaGraphADT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph example = new Graph();

      /**  Vertex wd = new Vertex("westdene");
        Vertex ap = new Vertex("Auckland Park");
        Vertex braam = new Vertex("Braamfontein");
        Vertex park = new Vertex("Parktown");

        example.addVertex(wd);
        example.addVertex(ap);
        example.addVertex(braam);
        example.addVertex(park);

        example.addBiEdge(ap, braam, 300);
        example.addBiEdge(braam, park, 180);
        example.addBiEdge(braam, wd, 160);
        example.addBiEdge(wd, park, 225);

        System.out.println("Contents of Graph");
        example.display();

        System.out.println("\nDFS_Tree from Park Town");
        example.resetVisitedVertices();
        example.DFSTree(wd);

        System.out.println("\nDFS from Park Town");
        example.resetVisitedVertices();
        example.DFS(wd);

        System.out.println("\nBFS from Park town");
        example.resetVisitedVertices();
        example.BFS(wd);

////////////////////////////////////////////////
        System.out.println("\nDijkstra's Shortest Path First.  Graph:");
        example = new Graph();

        wd = new Vertex("wesdene");
        ap = new Vertex("Auckland Park");
        braam = new Vertex("Braamfontein");
        park = new Vertex("Parktown");

        Vertex kt = new Vertex("Katlehong");
        Vertex swt = new Vertex("Soweto");
        Vertex bxt = new Vertex("Brixton");
        Vertex bn = new Vertex("Benoni");
        Vertex gm = new Vertex("Germiston");

        example.addVertex(wd);
        example.addVertex(braam);
        example.addVertex(park);
        example.addVertex(kt);
        example.addVertex(swt);
        example.addVertex(bxt);
        example.addVertex(bn);
        example.addVertex(gm);

        example.addBiEdge(braam, bn, 134);
        example.addBiEdge(kt, bn, 63);
        example.addBiEdge(bn, gm, 42);
        example.addBiEdge(bn, park, 44);
        example.addBiEdge(gm, wd, 185);
        example.addBiEdge(kt, swt, 84);
        example.addBiEdge(swt, bxt, 49);
        example.addBiEdge(bxt, park, 54);
        example.addBiEdge(bn, bxt, 83);
        example.addBiEdge(kt, park, 74);
        example.addBiEdge(park, gm, 49);

        example.display();
        System.out.println("Start Dijkstra from " + gm.getName());
//example.Dijkstra(gm);

/////////////////////////////////////
        System.out.println("\nCompute Minimal Spanning Tree from Brixton.  Graph:");
        example.display();
//example.Prim(wd); **/

        MainFrame frame = new MainFrame();
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null); //center frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
