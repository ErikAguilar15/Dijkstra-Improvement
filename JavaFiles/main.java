public class main {

    public static void main(String[] args) {

        System.out.println("Creating Graph");

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
