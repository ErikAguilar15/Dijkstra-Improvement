import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph {

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

    public Node getNode(Node n) {

        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            if (it.next().equals(n.getName())) {
                return it.next();
            }

        }
        return null;
    }

}
