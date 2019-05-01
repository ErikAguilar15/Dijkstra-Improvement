import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Dijkstra {

    // Finds shortest path from Node src on given Graph g
//    public static Graph runBasicDijkstra(Graph g, Node src) {
//
//        src.setDistCost(0);
//
//        //TreeSet data structures for nodes we need to visit and add them once visited
//        TreeSet<Node> visitedNodes = new TreeSet<>();
//        TreeSet<Node> unVisitedNodes = new TreeSet<>();
//
//        //Add root node to visited and everything else to unvisited
//        visitedNodes.add(src);
//        unVisitedNodes.addAll(g.getNodes());
//
//        //While we still have unvisited nodes
//        while (!unVisitedNodes.isEmpty()) {
//            Node openNode = unVisitedNodes.first();
//
//            unVisitedNodes.remove(openNode);
//
//            //Begin building neighborlist and get path cost
//            for (Map.Entry<Node, Integer> n :
//                    openNode.getNeighborsList().entrySet()) {
//                Node adjNode = n.getKey();
//                Integer edgeCost = n.getValue();
//
//                //Compare path costs to get shortest path
//                if (!visitedNodes.contains(adjNode)) {
//                    findShortcut(openNode, adjNode, edgeCost);
//                    unVisitedNodes.add(adjNode);
//                }
//            }
//            visitedNodes.add(openNode);
//        }
//        return g;
//    }

    static void findMinPaths(Graph g, Node startPort) {

        startPort.setDistCost( (float) 0.0 );

        //TreeSet data structures for nodes we need to visit and add them once visited
        TreeSet<Node> expanded = new TreeSet<>();
        TreeSet<Node> frontier = new TreeSet<>();

        // Path Start nodes
        expanded.add( startPort );
        frontier.add(startPort);

        //While we still have unvisited nodes
        while (!frontier.isEmpty()) {


            Node currentNode = frontier.first();
            frontier.remove(currentNode);

            //Get node keys and lengths of wine lines
            for (Edge edge : g.findNeighbors( currentNode.getID() )) {

                //Calculate path costs based off line lengths
                Node adjPipe = edge.getNeighbor(currentNode);
                Float lineLength = edge.getCost();

                if (!expanded.contains(adjPipe)) {
                    findShortcut(currentNode, adjPipe, lineLength);

                }
                if (!expanded.contains( adjPipe ) && currentNode != adjPipe)
                    frontier.add( adjPipe );
                }
            expanded.add(currentNode);
            }
//        return g;
    }

    private static void findShortcut(Node currentNode, Node adjPipe, Float lineLength) {
        if (currentNode.getDistCost() + lineLength < adjPipe.getDistCost()) {
            adjPipe.setDistCost( currentNode.getDistCost() + lineLength );
            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getPath());
            shortestPath.add(currentNode);
            adjPipe.setShortestPath(shortestPath);
        }
    }

    static void resetCosts(Graph g) {
        for (Node n :
                g.getPipes()) {
            n.setDistCost( Float.MAX_VALUE );
            n.clearRoute();
        }
    }

    static void findPaths(Graph g, int n, String srcTank, String destTank) {
        int cons;
        Node p1;
        while (n > 0) {
            findMinPaths( g, g.getPipe( srcTank ) );
//            g.printDistanceTree();
            cons = g.getPipe( destTank ).pipesInRoute();
            if (cons < 1)
                break;
            System.out.print( "\nShortest Path: " );
            g.printPipeLine( destTank );

            p1 = g.getPipe( destTank ).getRoute( cons - 1 );
            g.dropConnection( p1.getID(), destTank );
            resetCosts( g );
            n--;
        }
    }

    static void mergePaths(Graph g, String srcTank, List<Node> path, Node dest) {
        List<Node> oldPath = new LinkedList<>();
        List<Node> newPath = new LinkedList<>();
        boolean seen = false;
        Node current;
        Node closest = null;
        Float min = Float.MAX_VALUE;
        // Storing copy of old path
        for (Node n : path) {
            oldPath.add( new Node( n ) );
        }
        oldPath.add( new Node( dest ) );

        resetCosts( g );
        findMinPaths( g, g.getPipe( srcTank ) );

//        g.printDistanceTree();

        for (int i = 0; i < oldPath.size(); i++) {
            current = g.getPipe( oldPath.get( i ).getID() );
            if (min > current.getDistCost()) {
                closest = current;
                min = current.getDistCost();
            }

        }
        newPath = closest.getPath();
        newPath.add( closest );

        for (Node n : oldPath) {
            if (!seen) {
                if (n.getID().equals( closest.getID() ))
                    seen = true;
//                oldPath.remove( n );
            } else {
                newPath.add( g.getPipe( n.getID() ) );
//                System.out.println( n );
            }

        }
//        System.out.println( "Old Path: \t\t\t\t" + oldPath);
        System.out.println( "Connecting to old Path \t" + newPath );
    }

}
