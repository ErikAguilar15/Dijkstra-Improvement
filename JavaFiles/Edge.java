
public class Edge {

    private Node src;
    private Node dst;
    private Integer cost = Integer.MAX_VALUE;


    public Edge() {
        this.src = null;
        this.dst = null;
    }

    public Edge(Node src, Node dst, Integer c) {
        this.src = src;
        this.dst = dst;
        this.cost = c;
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
        return this.cost;
    }

    public void setCost(Integer len) {
        this.cost = len;
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
