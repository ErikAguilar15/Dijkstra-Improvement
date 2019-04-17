import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class Dijkstra {

    // Finds shortest path from Node src on given Graph g
    public static Graph calcShortestPath(Graph g, Node src) {

        src.setDistanceFromSrc(0);

        TreeSet<Node> visitedNodes = new TreeSet<>();
        TreeSet<Node> unVisitedNodes = new TreeSet<>();

        visitedNodes.add(src);
        unVisitedNodes.addAll(g.getNodes());

        while (!unVisitedNodes.isEmpty()) {
            Node openNode = unVisitedNodes.first();

            unVisitedNodes.remove(openNode);

            for (Map.Entry<Node, Integer> n :
                    openNode.getNeighborsList().entrySet()) {
                Node adjNode = n.getKey();
                Integer edgeCost = n.getValue();

                if (!visitedNodes.contains(adjNode)) {
                    if (openNode.getDistanceFromSrc() + edgeCost < adjNode.getDistanceFromSrc()) {
                        adjNode.setDistanceFromSrc(openNode.getDistanceFromSrc() + edgeCost);
                        LinkedList<Node> shortestPath = new LinkedList<>(openNode.getPath());
                        shortestPath.add(openNode);
                        adjNode.setShortestPath(shortestPath);
                    }
                    unVisitedNodes.add(adjNode);
                }
            }
            visitedNodes.add(openNode);
        }
        return g;
    }

    public static Graph calcCheapestWineLine(Graph g, Node src) {

        src.setDistanceFromSrc(0);

        TreeSet<Node> visitedNodes = new TreeSet<>();
        TreeSet<Node> unVisitedNodes = new TreeSet<>();

        visitedNodes.add(src);
        unVisitedNodes.add(src);

        while (!unVisitedNodes.isEmpty()) {

            Node openNode = unVisitedNodes.first();
            unVisitedNodes.remove(openNode);

            for (Edge e :
                    g.getPossibleConnections(openNode.getName())) {
                Node adjPipe;
                if (e.dstNode != null)
                    adjPipe = e.dstNode;
                else
                    adjPipe = e.srcNode;

                Integer lineLength = e.getLength();

                if (!visitedNodes.contains(adjPipe)) {
                    if (openNode.getDistanceFromSrc() + lineLength < adjPipe.getDistanceFromSrc()) {
                        adjPipe.setDistanceFromSrc(openNode.getDistanceFromSrc() + lineLength);
                        LinkedList<Node> shortestPath = new LinkedList<>(openNode.getPath());
                        shortestPath.add(openNode);
                        adjPipe.setShortestPath(shortestPath);
                    }
                }
                unVisitedNodes.add(adjPipe);
                }
            visitedNodes.add(openNode);
            }
        return g;
    }


}