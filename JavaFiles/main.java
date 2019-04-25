import java.util.*;
public class main {

	public static void main(String[] args) {
		System.out.println("STARTING");
        List<Node> newnodes = new ArrayList<Node>();

        System.out.println("QUERIES:");
    	
    	newnodes = JDBC.graphInformation();
    	
    	shortestPath.createShortestPath(newnodes);

	}

}
