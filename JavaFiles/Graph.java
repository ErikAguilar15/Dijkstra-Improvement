import java.util.ArrayList;

public class Graph {

    // Used in MNFLD case Dijkstra
    private ArrayList<Node> pipes = new ArrayList<>();
    private ArrayList<Edge> connections = new ArrayList<>();
    private ArrayList<Edge> droppedConnection = new ArrayList<>();

    // MNFLD Dijkstra functions
    ArrayList<Node> getPipes() {
        return this.pipes;
    }

    public void setPipes(ArrayList<Node> pipes) {
        this.pipes = pipes;
    }

    // Adds a node
    void addPipe(Node pipe) {
        this.pipes.add(pipe);
    }

    // Adds an edge
    void insertConnection(Edge conn) {
        this.connections.add(conn);
    }

    // Linear search for pipe
    Node getPipe(String pipeID) {

        for (Node pipe : this.pipes) {

            String pipeName = pipe.getName();

            if (pipeName.equals(pipeID)) {
                return pipe;
            }
        }
        return null;
    }

    // Returns results of a directed graph Port1->Port2
    ArrayList<Edge> findNeighbors(String inPort) {

        ArrayList<Edge> edgeList = new ArrayList<>();

        for (Edge edge : connections) {
//          System.out.println(edge);
            if (inPort.equals(edge.getSrcName()) /*|| inPort.equals(edge.getDstName())*/)
                edgeList.add(edge);
        }
        return edgeList;
    }

    // Only print the path from the source to the destination
    void printPipeLine(String n) {
        getPipe(n).printLine();
    }

    // Prints all the paths from Source(Tanks)
    void printDistanceTree() {
        for (Node pipe : pipes) {
            pipe.printLine();
        }
    }

    // ----------------

    boolean dropConnection(String eSrc, String eDst) {
        boolean res = false;
        for (Edge edge : findNeighbors(eSrc)) {
            System.out.println(edge);
            if (eDst.equals(edge.getDstName())) {
                droppedConnection.add(edge);
                connections.remove(edge);
                res = true;
                break;
            }
        }
        return res;
    }

    boolean resetConnection(String eSrc, String eDst) {
        boolean res = false;
        for (Edge edge : droppedConnection) {
//          System.out.println(edge);
            if (eSrc.equals(edge.getSrcName()) || eDst.equals(edge.getDstName())) {
                connections.add(edge);
                res = true;
                break;
            }
        }
        return res;
    }



    // Used in base case Dijkstra
//    private Set<Node> nodes = new HashSet<>();
//    public void addNode(Node nodeA) {
//        nodes.add(nodeA);
//    }
//    public Set<Node> getNodes() {
//        return nodes;
//    }
//    public void setNodes(Set<Node> nodes) {
//        this.nodes = nodes;
//    }
//    public void printPath(String n) {
//        getNode(n).printPath();
//    }
//    public Node getNode(String nodeName) {
//
//        Iterator<Node> it = nodes.iterator();
//        while (it.hasNext()) {
//            Node current = it.next();
//            if (current.getName() == nodeName) {
//                return current;
//            }
//
//        }
//        return null;
//    }


}
