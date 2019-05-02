public class Edge {

    private String conn; // Connection Name
    private Node side1; // Pipe  inFlow
    private Node side2; // Pipe outFlow
    private Float cost = Float.MAX_VALUE; // Edge Cost
    private Boolean inUse = false;

    // Creates an Edge with null pointers
    public Edge() {
        this.side1 = null;
        this.side2 = null;
    }

    // Creates an Edge with side1, side2 and cost of edge
    public Edge(Node side1, Node side2, Float cost) {
        this.conn = side1 + "__" + side2;
        this.side1 = side1;
        this.side2 = side2;
        this.cost = cost;
    }

    public Edge(Node side1, Node side2, Float cost, Boolean inUse) {
        this.conn = side1 + "__" + side2;
        this.side1 = side1;
        this.side2 = side2;
        this.cost = cost;
        this.inUse = inUse;
    }

    // Returns the Side1 of the edge
    public Node getSide1() {
        return this.side1;
    }

    // Sets the Side1 of the edge
    public void setSide1(Node side1) {
        this.side1 = side1;
    }

    // Returns the Side2 of the edge
    public Node getSide2() {
        return this.side2;
    }

    // Sets the Side2 of the edge
    public void setSide2(Node side2) {
        this.side2 = side2;
    }

    // Returns the Edge cost as cost of edge + weight of destination node
    Float getCost() {
        return this.cost + this.side2.getWeight(); //gets lenght of pipe
    }

    // Sets teh cost of the edge, does not effect node weight
    public void setCost(Float cost) {
        this.cost = cost;
    }

    // Returns the neighbor of given node
    Node getNeighbor(Node self) {
        return (side1 == self) ? side2 : side1;
    }

    // Returns the name of the source as string
    String getSrcName() {
        String name = this.side1.getID();
        if (name != null) {
            return name;
        }
        System.out.println("Tried to get source when it was null");
        return null;
    }

    // Returns the name of the destination as string
    String getDstName() {
        return this.side2.getID();
    }

    Boolean checkInUse() {
        return this.inUse;
    }

    @Override
    public String toString() {
        return ("(" + this.side1 + "," + this.side2 + ")->" + (this.cost + this.side2.getWeight()));
    }

}