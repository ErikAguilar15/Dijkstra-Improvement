import java.util.*;

public class Node {

    public int panel;
    public int port;

    public int srcPortID;
    public int destPortID;
    public int value;

    public List<Node> neighbors = new ArrayList<Node>();

    //These actually need to be priority queues rather than HashSets
    Set<Node> visitedNodes = new HashSet<Node>();
    Set<Node> unvisitedNodes = new HashSet<Node>();

    // Constructor
    public Node(int s, int d, int v) {
        this.srcPortID = s;
        this.destPortID = d;
        this.value = v;
    }

    //the actual shortest path from source to destination
    private List<Node> shortestPath = new LinkedList<>();

    // Update Node cost
    public void updateCost(int v) {
        this.value = v;
    }


    // Get cost of Node
    public int getCost() {
        return value;
    }

    // Inserting to neighbors list
    public void  addNeighbor(Node n) {
        neighbors.add(n);
    }

    public int compare(Node node1, Node node2) {
        if (node1.value < node2.value)
            return -1;
        if (node1.value > node2.value)
            return 1;
        return 0;
    }


}