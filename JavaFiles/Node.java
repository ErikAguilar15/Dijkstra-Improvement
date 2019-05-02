import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable {

    // Database Data
    private String nodeID;            // Pipe ID
    private Float srcPortID;          // Port IN
    private Float dstPortID;          // Port Out
    private String srcName;           // Port Name
    private String dstName;           // Port Name
    private Float weight; // Pipe Length

    // Dijkstra Data
    private Float distCost = Float.MAX_VALUE; // Distance from src
    private LinkedList<Node> shortestPath = new LinkedList<>(); // Stores shortest path from src

    
    public Node() {
    }
    // Create node from just ID
    public Node(String pipeID) {
        this.nodeID = pipeID;
    }

    // Create node with ID and Weights
    public Node(Float pipeID, Float pipeLength) {
        this.nodeID = String.valueOf( pipeID );
        this.weight = pipeLength;
    }

    // Create nodes with Src, Dest, and Weight (Assigns ID from Src and Dst)
    public Node(Float src, Float dst, Float pipeLength) {
        this.nodeID = src + "==" + dst;
        this.srcPortID = src;
        this.dstPortID = dst;
        this.weight = pipeLength;
    }

    // Create nodes with ID, Src, Dest, and Weight
    public Node(String pipeID, Float portSrc, Float portDst, Float pipeLength) {
        this.nodeID = pipeID;
        this.srcPortID = portSrc;
        this.dstPortID = portDst;
        this.weight = pipeLength;

    }

    public Node(Node n) {
        this.nodeID = n.nodeID;
        this.srcPortID = n.srcPortID;
        this.dstPortID = n.dstPortID;
        this.weight = n.weight;
        this.distCost = n.distCost;
        this.shortestPath = n.shortestPath;
    }


    // Returns nodes ID
    public String getID() {
        return nodeID;
    }

    // Sets the node ID
    public void setID(String s) {
        this.nodeID = s;
    }

    // Returns the Pipe's incoming Port
    Float getPortIn() {
        return this.srcPortID;
    }

    // Returns the Pipe's outgoing Port
    Float getPortOut() {
        return this.dstPortID;
    }

    // Returns the distance from the Src
    Float getDistCost() {
        return distCost;
    }

    // Sets the distance from the Src
    void setDistCost(Float c) {
        this.distCost = c;
    }

    // Returns the length of the Pipes
    Float getWeight() {
        return this.weight;
    }

    // Returns a list of nodes which contains shortest path from src
    List<Node> getPath() {
        return this.shortestPath;
    }

    // Sets the shortest path from list of nodes passed
    void setShortestPath(LinkedList<Node> l) {
        this.shortestPath = l;
    }

    // Clears the routes of the nodes
    void clearRoute() {
        this.shortestPath.clear();
    }

    // Returns the number of connections needed for path
    int pipesInRoute() {
        return shortestPath.size();
    }

    // Returns a specified(by index) node in path
    Node getRoute(Integer index) {
        return shortestPath.get(index);
    }

    // Object comparator
    @Override
    public int compareTo(Object o) {
        if (this.distCost == ((Node) o).distCost) {
            return 0;
        } else if (this.distCost > ((Node) o).distCost) {
            return 1;
        } else {
            return -1;
        }
    }

    // Object print function, gives the node a string value, useful for debug and printing
    @Override
    public String toString() {
        if (this.srcPortID != null && this.dstPortID != null)
            return ("[" + this.srcPortID + "==" + this.dstPortID + "] "
                    + String.valueOf( this.distCost ));
        else
            return ("[" + this.nodeID + "] " + String.valueOf( this.distCost ));
    }


    // Prints the shortest path to screen
    void printLine() {
        for (Node n : shortestPath) {
            System.out.print("(" + n + ") -> ");
        }
        System.out.print("(" + this + ")\n");
//        System.out.print(shortestPath + "-> " + this + "\n");
    }

    //    public Map<Node, Integer> getNeighbors() {
//        return neighbors;
//    }
//
//    public void setNeighbors(Map<Node, Integer> aN) {
//        this.neighbors = aN;
//    }
//
//    public Map<Node, Integer> getNeighborsList() {
//        List<Map.Entry<Node, Integer>> list =
//                new LinkedList<>(neighbors.entrySet());
//
//        // Sort the list
//        Collections.sort(list, Comparator.comparing(o -> (o.getValue())));
//
//        HashMap<Node, Integer> temp = new LinkedHashMap<>();
//        for (Map.Entry<Node, Integer> n : list) {
//            temp.put(n.getKey(), n.getValue());
//        }
//        return temp;
//    }

//    public void addAdjacentNode(Node n, Integer c) {
//        neighbors.put(n, c);
//    }

//    public void addPossibleConnection(Graph g, String graphNode, Integer c) {
//        Node n = g.getNode(graphNode);
//        if (n == null) {
//            System.out.println("Can't add possible connection, Node is null");
//        } else {
//            n.setDistCost(c + n.getDistCost());
//            edges.add(n);
////            edges.add(n, g.pipes.get(n.nodeID).length);
//        }
//
//    }





}