package graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a planar graph.
 * @author Ethan
 * @author Jack
 */
public class PlanarGraph implements Iterable<PlanarNode>{

    private Set<PlanarNode> nodes = new HashSet<>();

    /**
     * Get the set of nodes
     * @return the set of nodes.
     */
    public Set<PlanarNode> getNodes(){
        return this.nodes;
    }

    /**
     * Add a node
     * @param node the node being added
     */
    public void addNode(PlanarNode node) {
        nodes.add(node);
    }

    /**
     * Add a directed edge between planar nodes
     * @param n1 the start node
     * @param n2 the end node
     */
    public void addDirectedEdge(PlanarNode n1, PlanarNode n2) {
        n1.addNeighbor(n2);
    }

    /**
     * Add an undirected edge between planar nodes
     * @param n1 the first node
     * @param n2 the second node
     */
    public void addUndirectedEdge(PlanarNode n1, PlanarNode n2) {
        this.addDirectedEdge(n2, n1);
        this.addDirectedEdge(n1, n2);
    }

    /**
     * Merge two graphs
     * @param g1 the graph being merged.
     */
    public void mergeGraph(PlanarGraph g1){
        for (PlanarNode node: g1.getNodes()){
            this.addNode(node);
        }
    }

    /**
     * The iterator of graph
     * @return iterator of graph
     */
    @Override
    public Iterator<PlanarNode> iterator() {
        return nodes.iterator();
    }
}
