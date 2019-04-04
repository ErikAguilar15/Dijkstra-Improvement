import java.util.*;

public class Node {

	public int pannel;
	public int port;

	public int srcPortID;
	public int destPortID;
	public int value;

	public List<Node> neighbors = new ArrayList<Node>(); 

	// Constructor
	public Node(int s, int d, int v) {
        	srcPortID = s;
        	destPortID = d;
        	value = v;
    	}

	// Update Node cost
    	public void updateCost(int v) {
		value = v;
	}

	// Get cost of Node
	public int getCost() {
		return value;
	}

	// Inserting to neighbors list
	public int addNeighbor(Node n) {
		neighbors.add(n);
	}


}
