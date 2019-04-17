
public class Edge {

    Node srcNode;
    Node dstNode;
    Integer length = Integer.MAX_VALUE;


    public Edge() {
        this.srcNode = null;
        this.dstNode = null;
    }

    public Edge(Node src, Node dst, Integer len) {
        this.srcNode = src;
        this.dstNode = dst;
        this.length = len;
    }

    public Node getSrcNode() {
        return this.srcNode;
    }

    public void setSrcNode(Node src) {
        this.srcNode = src;
    }

    public Node getDstNode() {
        return this.dstNode;
    }

    public void setDstNode(Node dst) {
        this.dstNode = dst;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer len) {
        this.length = len;
    }

    public Node getNeighbor(Node self) {
        return (srcNode == self) ? dstNode : srcNode;
    }

    public String getSrcName() {
        String name = this.srcNode.getName();
        if (name != null) {
            return name;
        }
        System.out.println("Tried to get source when it was null");
        return null;
    }

    public String getDstName() {
        return this.dstNode.getName();
    }

    @Override
    public String toString() {
        return ("(" + this.srcNode + "," + this.dstNode + ")->" + this.length);
    }

}
