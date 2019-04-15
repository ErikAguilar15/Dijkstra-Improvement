import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class Dijkstra {

    // Finds shortest path from Node src on given Graph g
    public static Graph calcShortestPath(Graph g, Node src) {

        src.setCost(0);

        TreeSet<Node> visitedNodes = new TreeSet<>();
        TreeSet<Node> unVisitedNodes = new TreeSet<>();

        visitedNodes.add(src);
        unVisitedNodes.addAll(g.getNodes());

        while (!unVisitedNodes.isEmpty()) {
            Node openNode = unVisitedNodes.first();

            unVisitedNodes.remove(openNode);

            for (Map.Entry<Node, Integer> n :
                    openNode.getNeighborsList().entrySet()) {
                Node adjNode = n.getKey();
                Integer edgeCost = n.getValue();

                if (!visitedNodes.contains(adjNode)) {
                    if (openNode.getCost() + edgeCost < adjNode.getCost()) {
                        adjNode.setCost(openNode.getCost() + edgeCost);
                        LinkedList<Node> shortestPath = new LinkedList<>(openNode.getPath());
                        shortestPath.add(openNode);
                        adjNode.setShortestPath(shortestPath);
                    }
                    unVisitedNodes.add(adjNode);
                }
            }
            visitedNodes.add(openNode);
        }
        return g;
    }


}


/*

public class Dijkstra {

    private int dist[];
    private Set<String> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    List<List<Node> > adj;

    public void Dijkstra(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<String>();
        pq = new PriorityQueue<Node>(V, new Node());
    }
    // Funtion for Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int source){
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(String.valueOf(source), 0));

        // Distance to the source is 0
        dist[0] = 0;
        while (settled.size() != V) {

            // remove the minimum distance node
            // from the priority queue
            String u = pq.remove().getID();

            // adding the node whose distance is
            // finalized
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(String u){
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    // Driver code
    public static void main(String arg[]){
        int V = 5;
        int source = 0;

        // List of Adjacent Edges
        List<List<Node> > adj = new ArrayList<List<Node> >();

        // Inserting full list of nodes
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculate the single source shortest path
        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }
}
*/