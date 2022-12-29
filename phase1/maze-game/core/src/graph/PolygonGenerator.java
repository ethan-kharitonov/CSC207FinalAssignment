package graph;
import geometry.Point;

import java.util.ArrayList;

/**
 * A graph generator to generate polygon
 * @author Jack
 */
public class PolygonGenerator implements IGraphGenerator{
    private Point center;
    private int n;
    private float radius;
    private float angle;

    /**
     * Create a polygon generator.
     * @param center the center of polygon
     * @param n the number of edges
     * @param radius the radius from center to vertex
     * @param angle the angle of counterclockwise rotation
     */
    public PolygonGenerator(Point center, int n, float radius, float angle){
        this.center = center;
        this.n = n;
        this.radius = radius;
        this.angle = angle;
    }

    /**
     * Return an n-gon planar graph
     * @return n-gon planar graph
     */
    public PlanarGraph generate(){

        // generate cycle
        PlanarGraph graph = new PlanarGraph();
        //Convert to radian
        float initialRadian = (float)Math.toRadians(angle);
        float radianIncrement = (float) Math.toRadians(360.0/n);
        ArrayList<PlanarNode> lst = new ArrayList<>();
        //Create the vertices
        for (int i=0; i<n; i++){
            PlanarNode n1 = new PlanarNode(center.getX()+radius*(float)Math.cos(initialRadian+i*radianIncrement),
                    center.getY()+radius*(float)Math.sin(initialRadian+i*radianIncrement));
            lst.add(n1);
        }
        //Join undirected edges
        for (int i=0; i<n;i++){
            PlanarNode n1 = lst.get(i%n);
            PlanarNode n2 = lst.get((i+1)%n);
            n1.joinUndirected(n2);
        }
        //Add nodes to graph
        for(PlanarNode node: lst){
            graph.addNode(node);
        }

        return graph;
    }

}
