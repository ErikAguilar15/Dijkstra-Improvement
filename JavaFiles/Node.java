import java.util.*;

public class Node implements Comparable {

    private String nodeID;
    private Integer cost = Integer.MAX_VALUE;

    private LinkedList<Node> shortestPath = new LinkedList<>();
    private Map<Node, Integer> neighbors = new HashMap<>();

    // Constructor with Max Value
    public Node(String n) {
        this.nodeID = n;
    }

    // Constructor with given cost
    public Node(String n, Integer c) {
        this.nodeID = n;
        this.cost = c;
    }

    public String getName() {
        return nodeID;
    }

    public void setName(String s) {
        this.nodeID = s;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer c) {
        this.cost = c;
    }

    public Map<Node, Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Map<Node, Integer> aN) {
        this.neighbors = aN;
    }

    public Map<Node, Integer> getNeighborsList() {
        List<Map.Entry<Node, Integer>> list =
                new LinkedList<>(neighbors.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Node, Integer>>() {
            public int compare(Map.Entry<Node, Integer> o1,
                               Map.Entry<Node, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<Node, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Node, Integer> n : list) {
            temp.put(n.getKey(), n.getValue());
        }
        return temp;
    }

    public void addAdjacentNode(Node n, Integer c) {
        neighbors.put(n, c);
    }

    public List<Node> getPath() {
        return this.shortestPath;
    }

    public void printPath() {
        System.out.print("Shortest Path: ");
        for (Node n :
                shortestPath) {
            System.out.print(n + " ");
        }
        System.out.print(this);
    }

    public void setShortestPath(LinkedList<Node> l) {
        this.shortestPath = l;
    }

    @Override
    public int compareTo(Object o) {
        if (this.cost.equals(((Node) o).cost)) {
            return 0;
        } else if (this.cost > ((Node) o).cost) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return (this.nodeID + " " + this.cost);
    }

    public void print() {
        System.out.print(this.nodeID + " " + this.cost + ", ");
    }

}

/*
public class Node implements Comparator<Node> {

    // Values
    private Integer sysID;
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
    public Node(Integer id, float cst) {
        sysID = id;
        srcPortID = "";
        destPortID = "";
        cost = cst;
        visited = false;
    }

    // Constructor
    public Node(String src, String dest, float cst) {
        srcPortID = src;
        destPortID = dest;
        cost = cst;
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

    // Get src name
    public String getID(){return srcPortID;}

    // Inserting to neighbors list
    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    @Override
    public int compare(Node n1, Node n2) {

        if (n1.cost < n2.cost)
            return -1;
        if (n1.cost > n2.cost)
            return 1;
        return 0;
    }

}

*/