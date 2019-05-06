
public class Edge {

    private String conn; // Connection Name
    private Node side1; // Pipe  inFlow
    private Node side2; // Pipe outFlow
    private Float cost = Float.MAX_VALUE; // Edge Cost
    private Boolean inUse;

    // Creates an Edge with null pointers
    public Edge() {
        this.side1 = null;
        this.side2 = null;
    }

    // Creates an Edge with side1, side2 and cost of edge
    public Edge(Node side1, Node side2, Float cost) {
    	String node1, node2;
    	node1 = side1.getID();
    	node2 = side2.getID();
        this.conn = node1 + "__" + node2;
        this.side1 = side1;
        this.side2 = side2;
        this.cost = cost;
    }
    
    public String getEdgeID() {
    	return this.conn;
    }

    // Returns the Side1 of the edge
    public Node getSide1() {
    	if (this.side1 != null)
    		return this.side1;
    	return null;
    }

    // Sets the Side1 of the edge
    public void setSide1(Node side1) {
        this.side1 = side1;
    }

    // Returns the Side2 of the edge
    public Node getSide2() {
    	if (this.side2 != null)
    		return this.side2;
    	return null;
    }

    // Sets the Side2 of the edge
    public void setSide2(Node side2) {
        this.side2 = side2;
    }

    // Returns the Edge cost as cost of edge + weight of destination node
    Float getCost() {
        return this.cost; //gets lenght of pipe
    }

    // Sets teh cost of the edge, does not effect node weight
    public void setCost(Float cost) {
        this.cost = cost;
    }

    // Returns the neighbor of given node
    Node getNeighbor(Node self) {
        return (this.side1 == self) ? this.side2 : this.side1;
    }

    // Returns the name of the source as string
    String getSrcName() {
    	//String bane = this.side2.getID();
        String name = this.side1.getID();
        if (name != null) {
            return name;
        }
        //System.out.println("Tried to get source when it was null");
        return null;
    }

    // Returns the name of the destination as string
    String getDstName() {
    	String name = this.side2.getID();
        if (name != null) {
        	return name;
        }
        return null;
    }

    @Override
    public String toString() {
        return ("(" + this.side1 + "," + this.side2 + ")->" + (this.cost + this.side2.getWeight()));
    }

}