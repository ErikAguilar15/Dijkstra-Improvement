public class shortestPath {
	/*
     * Inserts Example Tanks, Pipes, and Connections and tests program
	 */
    public static void runTest() {
        Graph mnfld = new Graph();
        float destTank = 7f;

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
        mnfld.addPipe( new Node( 10f, 1010f, 150f ) );
        mnfld.addPipe( new Node( 20f, 2020f, 150f ) );

        // Inserting Edges to the graph
        mnfld.insertConnection( new Edge( mnfld.getPipe( 0f ), mnfld.getPipe( 2f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 0f ), mnfld.getPipe( 3f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 1f ), mnfld.getPipe( 2f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 22f ), mnfld.getPipe( 4f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 33f ), mnfld.getPipe( 5f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 44f ), mnfld.getPipe( 5f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 44f ), mnfld.getPipe( 7f ), 500f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 55f ), mnfld.getPipe( 6f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 66f ), mnfld.getPipe( 7f ), 100f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 66f ), mnfld.getPipe( 8f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 88f ), mnfld.getPipe( 7f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 33f ), mnfld.getPipe( 9f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 99f ), mnfld.getPipe( 5f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 33f ), mnfld.getPipe( 1010f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 1010f ), mnfld.getPipe( 7f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 1f ), mnfld.getPipe( 20f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 2020f ), mnfld.getPipe( 3f ), 1f ) );

        // -- Running Dijkstra & Finding shortest Paths -- //
//        Dijkstra.findPaths( mnfld, 10, "0.0", destTank );
//
//        mnfld.restoreDroppedConnections();
//
//        System.out.println( "\n\n" );
        Dijkstra.findMinPaths( mnfld, mnfld.getPipe( 0f ) );
        Dijkstra.mergePaths( mnfld, 1f, mnfld.getPipe( destTank ).getPath(), mnfld.getPipe( destTank ) );

    }


}