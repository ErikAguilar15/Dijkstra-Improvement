public class shortestPath {
	/*
     * Inserts Example Tanks, Pipes, and Connections and tests program
	 */
    public static void runTest() {
        Graph mnfld = new Graph();
        String destTank = "7.0";

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
        mnfld.insertConnection( new Edge( mnfld.getPipe( "0.0" ), mnfld.getPipe( "2.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "0.0" ), mnfld.getPipe( "3.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "1.0" ), mnfld.getPipe( "2.0" ), 1f ) );
//        mnfld.insertConnection( new Edge( mnfld.getPipe( "1.0" ), mnfld.getPipe( "3.0" ), 1f ) );
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
        mnfld.insertConnection( new Edge( mnfld.getPipe( "33.0" ), mnfld.getPipe( "1010.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "1010.0" ), mnfld.getPipe( "7.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "1.0" ), mnfld.getPipe( "20.0" ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( "2020.0" ), mnfld.getPipe( "3.0" ), 1f ) );

        // -- Running Dijkstra & Finding shortest Paths -- //
//        Dijkstra.findPaths( mnfld, 2, "0.0", destTank );
//        mnfld.restoreDroppedConnections();
        Dijkstra.findMinPaths( mnfld, mnfld.getPipe( "0.0" ) );
        Dijkstra.mergePaths( mnfld, "1.0", mnfld.getPipe( destTank ).getPath(), mnfld.getPipe( destTank ) );

    }


}