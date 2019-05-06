import java.util.ArrayList;

public class Graph {

    // Stores list of Nodes and Edges
    private ArrayList<Node> pipes = new ArrayList<>();
    private ArrayList<Edge> connections = new ArrayList<>();
    private ArrayList<Edge> droppedConnection = new ArrayList<>();

    // MNFLD Dijkstra functions
    ArrayList<Node> getPipes() {
        return this.pipes;
    }

    public void setPipes(ArrayList<Node> pipeList) {
        this.pipes = pipeList;
    }

    // Adds a node
    void addPipe(Node pipe) {
//        System.out.println( pipe );
        this.pipes.add( pipe );
    }

    // Adds an edge
    void insertConnection(Edge conn) {
//        System.out.println( conn );
        this.connections.add( conn );
    }
    
    public void setEdges(ArrayList<Edge> edges) {
    	this.connections = edges;
    }

    // Linear search for pipe
  /*  Node getPipe(String id) {
        String portID = id.toString();
        for (Node pipe : this.pipes) {

            if (pipe.getPortIn() != null && pipe.getPortOut() != null) {
                if (pipe.getPortIn() == portID || pipe.getPortOut() == portID )
                        return pipe;
                } else if (pipe.getID().equals( portID )) {
                    return pipe;
                }
        }
        return null;
    }
*/

    Node getPipeFromSrcOrDst(String PortID) { //not actually node ID: ###-###
    	for (Node pipe : this.pipes) {
    		String pipein = pipe.getPortIn();
    		String pipeout = pipe.getPortOut();
    		if (pipein != null || pipeout != null) {
    			if (pipein.equals(PortID) || pipeout.equals(PortID)) {
    				//System.out.println("Found node");
    				return pipe;
    			}
    		}
    	}
    	//System.out.println("Didnt find node");
    	return null;
    }
    

    
    Node getPipe(String nodeID) { //node ID is ###-###, comparing as ###-###=###-###
    	
    	//Node currentpipe = getPipeFromSrcOrDst(nodeID);
    	//String news = currentpipe.getID();
    	//nodeID = currentpipe.getID();
    	int length = nodeID.length();
    	if (length != 7 || nodeID.equals("PRODUCT")) {
    		for (Node pipe : this.pipes) {

    			if (pipe.getID().equals( nodeID )) {
    				//System.out.print("found node");
    				return pipe;
    			}
    		}
    	}
    	else {
    		return getPipeFromSrcOrDst(nodeID);
    	}
    	//System.out.print("didnt find node");
    	return null;
    }


    Edge getEdge(Node side1, Node side2) {
    	Node checkSide1 = new Node();
    	Node checkSide2 = new Node();
    	for (Edge connection : this.connections) {
    		checkSide1 = connection.getSide1();
    		checkSide2 = connection.getSide2();
    		if ((checkSide1 == side1) && (checkSide2 == side2)) {
    			return connection;
    		}
    	}
    	return null;
    	
    }

    // Returns results of a directed graph Port1->Port2
    ArrayList<Edge> findNeighborsForPipes(String nodeID) { //find neighbor pipes using ###-###==###-###
    	//test srcPort and dstPort

        ArrayList<Edge> edgeList = new ArrayList<>();


        for (Edge edge : connections) {
//          System.out.println(edge);
            if (nodeID.equals( edge.getSrcName() ) /*|| inPort.equals(edge.getDstName())*/)
                edgeList.add( edge );
        }
        return edgeList;
    }

    ArrayList<Edge> findNeighborsForTanks(String nodeID) {

        ArrayList<Edge> edgeList = new ArrayList<>();

        for (Edge edge : connections) {
//          System.out.println(edge);
        	String edgeSrcID = edge.getSrcName();
        	String edgeDstID = edge.getDstName();
        	
            if (nodeID.equals(edgeSrcID) || nodeID.equals(edgeDstID) ) /*|| inPort.equals(edge.getDstName())*/
                edgeList.add( edge );
        }
        return edgeList;
    }
    
    ArrayList<Edge> findNeighborsHelper(Graph g, Node currentNode) {
    	ArrayList<Edge> edges = new ArrayList();
    	if (currentNode.getPortIn() != null && currentNode.getPortOut() != null) {
    		edges = g.findNeighborsForPipes(currentNode.getID()); //was getID but is ###-###==###-###, need ###-###
    	
    	} else
    	{
    		edges = g.findNeighborsForTanks(currentNode.getID());
    	}
    	
    	return edges;
    }
    
    // Only print the path from the source to the destination
    void printPipeLine(String n) { //why string
        getPipe( n ).printLine();
    }

    // Prints all the paths from Source(Tanks)
    void printDistanceTree() {
        for (Node pipe : pipes) {
            pipe.printLine();
        }
    }

    // ----------------

   
    
   /* boolean dropConnection(Graph g, String eSrc, String eDst) {
        boolean res = false;
        for (Edge edge : findNeighborsHelper(g, eSrc )) {
//            System.out.println( edge );
            if (eDst.equals( edge.getDstName() )) {
                droppedConnection.add( edge );
                connections.remove( edge );
                res = true;
                break;
            }
        }
        return res;
    }*/
    
    void dropConnection(Edge edge) {
    	if (connections.contains(edge)) {
                droppedConnection.add( edge );
                connections.remove( edge );
    	}
    }

    boolean resetConnection(String eSrc, String eDst) {
        boolean res = false;
        for (Edge edge : droppedConnection) {
            if (eSrc.equals( edge.getSrcName() ) || eDst.equals( edge.getDstName() )) {
                connections.add( edge );
                res = true;
                break;
            }
        }
        return res;
    }

    void restoreDroppedConnections() {
        for (Edge e :
                droppedConnection) {
            connections.add( e );
        }
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
//            if (current.getID() == nodeName) {
//                return current;
//            }
//
//        }
//        return null;
//    }


}