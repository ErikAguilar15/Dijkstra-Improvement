import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Graph {

    // Used in MNFLD case Dijkstra
    Hashtable<String, Node> ports = new Hashtable<>();                          // Stores the port's address, fast hashing
    Hashtable<String, Edge> pipes = new Hashtable<>();       // Hashes pipes
    // Used in base case Dijkstra
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {

        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Node getNode(String nodeName) {

        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            Node current = it.next();
            if (current.getName() == nodeName) {
                return current;
            }

        }
        return null;
    }

    public void printPath(String n) {
        getNode(n).printPath();
    }

}
