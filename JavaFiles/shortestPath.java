import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class shortestPath {
	/*
	 * NEED TO IMPLEMENT WITH INPUT nodes
	 */
    public static void createShortestPath(List<Node> nodes) {

    	System.out.println( "Creating Graph" );
        Graph mnfld = new Graph();
        int index;

        // Adding Tanks
        mnfld.addPipe( new Node( 0f, 5.0f ) );
        mnfld.addPipe( new Node( 1f, 5.0f ) );
        mnfld.addPipe( new Node( 7f, 5.0f ) );

        // Adding Pipes
        mnfld.addPipe( new Node( 2f, 22f, 100f ) );
        mnfld.addPipe( new Node( 3f, 33f, 150f ) );
        mnfld.addPipe( new Node( 4f, 44f, 200f ) );
        mnfld.addPipe( new Node( 5f, 55f, 250f ) );
        mnfld.addPipe( new Node( 6f, 66f, 300f ) );
        mnfld.addPipe( new Node( 8f, 88f, 200f ) );
        mnfld.addPipe( new Node( 9f, 99f, 150f ) );

        // Inserting Edges to the graph
        mnfld.insertConnection( new Edge( mnfld.getPipe( "0.0" ), mnfld.getPipe( "2.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "0.0" ), mnfld.getPipe( "3.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "1.0" ), mnfld.getPipe( "2.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "1.0" ), mnfld.getPipe( "3.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "22.0" ), mnfld.getPipe( "4.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "33.0" ), mnfld.getPipe( "5.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "44.0" ), mnfld.getPipe( "5.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "44.0" ), mnfld.getPipe( "7.0" ), 500f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "55.0" ), mnfld.getPipe( "6.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "66.0" ), mnfld.getPipe( "7.0" ), 100f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "66.0" ), mnfld.getPipe( "8.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "88.0" ), mnfld.getPipe( "7.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "33.0" ), mnfld.getPipe( "9.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "99.0" ), mnfld.getPipe( "5.0" ), 1f ) );

        // -- Running Dijkstra & Finding shortest Paths -- //

        int cons;
        String destTank = "7.0";
        Node p1;


//        for (int i = 0; i < 3; i++)
//            System.out.println();
        Dijkstra.findPaths( mnfld, 3, "0.0", destTank );

//        Dijkstra.findMinPaths( mnfld, mnfld.getPipe( "0.0" ) );
//        mnfld.printDistanceTree();
//        System.out.print( "\nShortest Path: " );
//        mnfld.printPipeLine( destTank );
        // TODO: Implement method to remove edge and calculate new shortest cost with out the edge, used to find new routes for the destination


//
//        cons = mnfld.getPipe( destTank ).pipesInRoute();
//
//        p1 = mnfld.getPipe( destTank ).getRoute( cons - 1 );
//
//        mnfld.dropConnection( p1.getID(), destTank );
//
//        Dijkstra.resetCosts( mnfld );
//
//        Dijkstra.findMinPaths(mnfld, mnfld.getPipe("0.0"));
////        mnfld.printDistanceTree();
//        System.out.print( "\n\nShortest Path: " );
//        mnfld.printPipeLine(destTank);
//
//
//        cons = mnfld.getPipe( destTank ).pipesInRoute();
//
//        p1 = mnfld.getPipe( destTank ).getRoute( cons - 1 );
//
//        mnfld.dropConnection( p1.getID(), destTank );
//
//        Dijkstra.resetCosts( mnfld );
//
//        Dijkstra.findMinPaths(mnfld, mnfld.getPipe("0.0"));
////        mnfld.printDistanceTree();
//        System.out.print( "\n\nShortest Path: " );
//        mnfld.printPipeLine(destTank);
    }


}