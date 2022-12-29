package graph;

import geometry.Point;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a planar node
 * @author Ethan
 * @author Jack Sun
 */
public class PlanarNode extends Point {
    private Set<PlanarNode> neighbors;

    /**
     * Create a planar node
     * @param x x-coordinate of node
     * @param y y-coordinate of node
     */
    public PlanarNode(float x, float y){
        super(x,y);
        neighbors = new HashSet<>();
    }

    /**
     * Get all adjacent nodes
     * @return set of all adjacent nodes.
     */
    public Set<PlanarNode> getNeighbors(){
        return neighbors;
    }

    /**
     * Add a neighbour
     * @param node node being added
     */
    public void addNeighbor(PlanarNode node){
        neighbors.add(node);
    }

    /**
     * Join two nodes with undirected edge
     * @param n2 node to be joined
     */
    public void joinUndirected(PlanarNode n2){
        this.addNeighbor(n2);
        n2.addNeighbor(this);
    }

    /**
     * Determine whether the point equals the object
     * @param p the node being compared
     * @return whether they are equal
     */

    public boolean equals(PlanarNode p)
    {
        return super.equals(p); //implicitly casts p to point, its parent class.
    }

    /**
     * Return integer hash code of the coordinate of the node
     * @return hash code of the coordinate of the node
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
    }
}
