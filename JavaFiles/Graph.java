import java.util.ArrayList;

public class Graph {

    // Used in MNFLD case Dijkstra
    ArrayList<Node> pipes = new ArrayList<>();
    ArrayList<Edge> connections = new ArrayList<>();

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

    public void insertEdge(Edge conn) {
        this.connections.add(conn);
    }

    public Node getPipe(String pipeID) {
        for (Node pipe : this.pipes) {
            String pipeName = pipe.getName();
            if (pipeName.equals(pipeID)) {
                return pipe;
            }
        }
        return null;
    }

    // Returns results of a directed graph Port1->Port2
    public ArrayList<Edge> findNeighbors(String inPort) {

        ArrayList<Edge> edgeList = new ArrayList<>();

        for (Edge edge : connections) {
//          System.out.println(edge);
            String portName = edge.getSrcName();
            if (portName != null) {
                if (portName.equals(inPort)) {
                    edgeList.add(edge);
                }
            }
        }
        return edgeList;
    }

    public void printPipeLine(String n) {
        getPipe(n).printLine();
    }

    public void printDistanceTree() {
        for (Node pipe : pipes) {
            pipe.printLine();
        }
    }

    // ----------------


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
