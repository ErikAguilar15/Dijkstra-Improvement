
public class Edge {

    private Node src;
    private Node dst;
    private Integer cost = Integer.MAX_VALUE;

    // Empty null pointer
    public Edge() {
        this.src = null;
        this.dst = null;
    }

    public Edge(Node src, Node dst, Integer cost) {
        this.src = src;
        this.dst = dst;
        this.cost = cost;
    }

    public Node getSrc() {
        return this.src;
    }

    public void setSrc(Node src) {
        this.src = src;
    }

    public Node getDst() {
        return this.dst;
    }

    public void setDst(Node dst) {
        this.dst = dst;
    }

    // When considering using cost for edges we can sum the Node pipeLength
    public Integer getCost() {
        return this.cost + this.dst.getPipeLength();
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Node getNeighbor(Node self) {
        return (src == self) ? dst : src;
    }

    public String getSrcName() {
        String name = this.src.getName();
        if (name != null) {
            return name;
        }
        System.out.println("Tried to get source when it was null");
        return null;
    }

    public String getDstName() {
        return this.dst.getName();
    }

    @Override
    public String toString() {
        return ("(" + this.src + "," + this.dst + ")->" + this.cost);
    }

}
