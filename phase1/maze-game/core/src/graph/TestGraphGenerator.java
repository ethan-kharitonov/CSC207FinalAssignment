package graph;

public class TestGraphGenerator implements IGraphGenerator{
    @Override
    public PlanarGraph generate() {
        PlanarGraph graph = new PlanarGraph();
        PlanarNode n1 = new PlanarNode(-1,-2);
        PlanarNode n2 = new PlanarNode(2,-2);
        PlanarNode n3 = new PlanarNode(1,-1);
        PlanarNode n4 = new PlanarNode(4,-1);
        PlanarNode n5 = new PlanarNode(-2,0);
        PlanarNode n6 = new PlanarNode(0,0);
        PlanarNode n7 = new PlanarNode(2,0);
        PlanarNode n8 = new PlanarNode(5,0);
        PlanarNode n9 = new PlanarNode(7,1);
        PlanarNode n10 = new PlanarNode(-1,2);
        PlanarNode n11 = new PlanarNode(3,2);
        PlanarNode n12 = new PlanarNode(-1,3);
        PlanarNode n13 = new PlanarNode(1,3);
        PlanarNode n14 = new PlanarNode(4,3);

        n1.joinUndirected(n5);
        n1.joinUndirected(n3);
        n2.joinUndirected(n5);
        n3.joinUndirected(n7);
        n4.joinUndirected(n7);
        n4.joinUndirected(n8);
        n5.joinUndirected(n6);
        n6.joinUndirected(n10);
        n6.joinUndirected(n11);
        n7.joinUndirected(n11);
        n8.joinUndirected(n9);
        n8.joinUndirected(n11);
        n10.joinUndirected(n12);
        n10.joinUndirected(n13);
        n11.joinUndirected(n14);
        n12.joinUndirected(n13);
        n13.joinUndirected(n14);


        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);
        graph.addNode(n9);
        graph.addNode(n10);
        graph.addNode(n11);
        graph.addNode(n12);
        graph.addNode(n13);
        graph.addNode(n14);

        return graph;
    }
}
