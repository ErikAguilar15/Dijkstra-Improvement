public class main {

    public static void main(String[] args) {

        System.out.println("Creating Graph");

        // Creating Nodes to be inserted
        Node node1 = new Node("A123-475");
        Node node2 = new Node("B234-567");
        Node node3 = new Node("C345-678");
        Node node4 = new Node("D456-789");
        Node node5 = new Node("E567-890");
        Node node6 = new Node("F678-901");

        // Adding neighbors to Nodes
        node1.addAdjacentNode(node2, 10);
        node1.addAdjacentNode(node3, 15);

        node2.addAdjacentNode(node4, 12);
        node2.addAdjacentNode(node6, 15);

        node3.addAdjacentNode(node5, 10);


        node4.addAdjacentNode(node5, 2);
        node4.addAdjacentNode(node6, 1);

        node5.addAdjacentNode(node5, 5);


        Graph g = new Graph();

        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        g.addNode(node5);
        g.addNode(node6);

        g = Dijkstra.calcShortestPath(g, node1);
        node5.printPath();
//		for (Node n:
//				g.getNodes()) {
//			for (Node pN:
//					n.getPath()) {
//				pN.print();
//			}
//			System.out.print("\n");
//		}

//		node1.print();
//		g.print();


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
//}
