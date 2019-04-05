import java.util.*;

public class Node {

	// Values 
	public int srcPortID;
	public int destPortID;
	// a,b,c to be assigned,  b > c since we perfer more clips (makes Hoses of Higher cost)
	// c(x) = PipeLen*a + Clips*b + Hoses*c
	public int cost;
	public boolean visited;
	
	// Post Computed for Printing
	public int pannel; 
	public int port;

	// This is where we'll store open connections 
	public List<Node> neighbors = new ArrayList<Node>(); 

	// Default Constructor --  Sets to MAX VALUE, FLAGS FOR UNSET
	public Node(){
		srcPortID = Integer.MAX_VALUE;
		destPortID = Integer.MAX_VALUE;
		cost = Integer.MAX_VALUE;
		visited = true;
	}

	// Constructor 
	public Node(int src, int dest, int cst, boolean vis) {
        	srcPortID = src;
        	destPortID = dest;
        	cost = cst;
			visited = vis;
    	}

	// Update Node cost
    	public void updateCost(int c) {
		cost = c;
	}

	// Get cost of Node
	public int getCost() {
		return cost;
	}

	// Inserting to neighbors list
	public int addNeighbor(Node n) {
		neighbors.add(n);
	}
	
	public int compare(Node n1, Node n2) {

		if (n1.cost < n2.cost)
			return -1;
		if (n1.cost > n2.cost)
			return 1;
		return 0;
	}

}
