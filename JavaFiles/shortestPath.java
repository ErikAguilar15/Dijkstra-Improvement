public class shortestPath {

    public static void main(String[] args) {

        System.out.println("Creating Graph");

        // Creating Tank Nodes
        Node aTank = new Node("ATank");
        Node bTank = new Node("BTank");


        // Creating Pipe Nodes
        Node pipeE = new Node("E567", "890", 10);
        Node pipeC = new Node("C345", "678", 10);
        Node pipeD = new Node("D456", "789", 10);
        Node pipeF = new Node("F678", "901", 10);
        Node pipeG = new Node("G345", "045", 10);

        // Adding neighbors to Nodes
        aTank.addAdjacentNode(pipeE, 10);
        aTank.addAdjacentNode(pipeC, 15);

        pipeC.addAdjacentNode(bTank, 100);

        pipeE.addAdjacentNode(pipeD, 12);
        pipeE.addAdjacentNode(pipeF, 15);

        pipeD.addAdjacentNode(bTank, 2);
        pipeD.addAdjacentNode(pipeF, 1);

        pipeF.addAdjacentNode(pipeG, 1);

        pipeG.addAdjacentNode(bTank, 1);

//        bTank.addAdjacentNode(bTank, 5);


        Graph g = new Graph();

        g.addNode(aTank);
        g.addNode(pipeE);
        g.addNode(pipeC);
        g.addNode(pipeD);
        g.addNode(pipeF);
        g.addNode(pipeG);
        g.addNode(bTank);

        g = Dijkstra.calcCheapestWineLine(g, aTank);
        g.printPath(bTank.getName());
//        bTank.printPath();


        // Create graph from DB Node
        //  Find  all the Non-Active Ports and Sorts them by number of reservations
		/*
		 * String sql = "SELECT *
				FROM MNFLD.dbo.wmgma01_cnct_pt AS cp
				WHERE cp.ACTV_X =0 AND cp.RESV_IN_USE_N < 2
				ORDER BY cp.RESV_IN_USE_N ASC, cp.CNCT_I
	*/

        // Set up Nodes and Edges
        // 	-- [] Find Neighbors based from all Pannels
        // 	-- Calculate size of clips possible
        // 	-- Calculate angle theta of connection


    }


}
