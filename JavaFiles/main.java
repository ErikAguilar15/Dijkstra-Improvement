import java.util.*;
public class main {

	public static void main(String[] args) {


		 ArrayList<Node> newnodes = new ArrayList<Node>();
		
		 Graph mnfld = new Graph();
		 
		 
		 //might need to limit query results, too large maybe?
		 //newnodes = JDBC.graphInformation(); //returns arraylist of nodes
		 //mnfld.setPipes(newnodes); //set nodes to the manifold
		 //JDBC.insertConnections(mnfld);
		 
		
		 
		 
		 
		 
		 
		 /*
			for (int i = 0; i < newnodes.length(); i++) { //length might be wrong
				//need to look for way to add edges by looking for neighbor nodes i think
				//loop through nodes and create Edges
				//might need new query
				newedges.addEdge(node1,node2,cost);
			}

			mnfld.setConnections(newedges);

			up to this point, graph should be global to Dijkstra and unused.
			loop until user leaves page
			get input from user for tanks to transfer
			find shortest path between both tanks
			end loop
			this will change depending how html works so not spending any time now on it
*/
         //shortestPath.runTest();
		 shortestPath.runTest2();
	}

}