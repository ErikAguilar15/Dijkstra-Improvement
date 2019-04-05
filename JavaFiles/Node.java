import java.util.*;

public class Node {

    // Values
    private String srcPortID;
    private String destPortID;
    // a,b,c to be assigned,  b > c since we perfer more clips (makes Hoses of Higher cost)
    // c(x) = PipeLen*a + Clips*b + Hoses*c
    private float cost;
    private boolean visited;

    // Post Computed for Printing
    public int pannel;
    public int port;

    // This is where we'll store open connections
    private List<Node> neighbors = new ArrayList<>();

    // Default Constructor --  Sets to MAX VALUE, FLAGS FOR UNSET
    public Node(){
        srcPortID = "";
        destPortID = "";
        cost = Integer.MAX_VALUE;
        visited = false;
    }

    // Constructor
    public Node(String src, String dest, float cst, boolean vis) {
        srcPortID = src;
        destPortID = dest;
        cost = cst;
        visited = vis;
    }

    // Update Node cost
    public void updateCost(float c) {
        cost = c;
    }

    // Get cost of Node
    public float getCost() {
        return cost;
    }

    // Inserting to neighbors list
    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    public int compare(Node n1, Node n2) {

        if (n1.cost < n2.cost)
            return -1;
        if (n1.cost > n2.cost)
            return 1;
        return 0;
    }

}