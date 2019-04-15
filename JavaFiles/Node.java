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
        System.out.print("Shortest Path: " + shortestPath + "-> " + this);
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