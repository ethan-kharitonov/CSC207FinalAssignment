package graph;

/**
 * Represents a graph generator interface
 * @author Ethan
 * @author Jack Sun
 */
public interface IGraphGenerator {
    /**
     * Generate a planar graph
     * @return planar graph
     */
    PlanarGraph generate();
}
