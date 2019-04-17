import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph {

    // Used in MNFLD case Dijkstra
    ArrayList<Node> pipes = new ArrayList<>();
    ArrayList<Edge> connections = new ArrayList<>();


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


    // MNFLD Dijkstra functions
    public ArrayList<Node> getPipes() {
        return this.pipes;
    }

    public void setPipes(ArrayList<Node> pipes) {
        this.pipes = pipes;
    }

    public void addPipe(Node pipe) {
        this.pipes.add(pipe);
    }

    public void addPossibleConnection(Edge conn) {
        this.connections.add(conn);
    }

    public Node getPipe(String pipeID) {
        for (Node pipe :
                this.pipes) {
            String pipeName = pipe.getName();
            if (pipeName.equals(pipeID)) {
                return pipe;
            }
        }
        return null;
    }

    public ArrayList<Edge> getPossibleConnections(String srcPipe) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (Edge e :
                connections) {
//            System.out.println(e);
            String name = e.getSrcName();
            if (name != null) {
                if (name.equals(srcPipe)) {
                    edges.add(e);
                }
            }
        }
        return edges;
//        return null;
    }

    public void printPipeLine(String n) {
        getPipe(n).printLine();
    }
}
