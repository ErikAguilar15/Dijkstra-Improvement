import java.util.ArrayList;
import java.util.Arrays;

public class shortestPath {

    public static void main(String[] args) {

        System.out.println("Creating Graph");
        Graph mnfld = new Graph();
        int index;

        ArrayList<String> names = new ArrayList<>(


                /// pipe_ID portIN _.portOut


                Arrays.asList(
                        "ATank",     //0
                        "B345->678", //1
                        "C456->789", //2
                        "D567->890", //3
                        "ETank",     //4
                        "F678->901", //5
                        "G345->045", //6
                        "HTank"      //7
                )
        );

        for (String name : names) {
            index = name.indexOf("->");
            if (index > 0) {
                String src = name.substring(0, index);
                String dst = name.substring(index + 2);
                mnfld.addPipe(new Node(src, dst, (float) 0.0));
            } else {
                mnfld.addPipe(new Node(name, (float) 0.0));
            }
        }

        // Inserting Edges to the graph
        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(0)), mnfld.getPipe(names.get(1)), 10)); // A-B
        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(0)), mnfld.getPipe(names.get(2)), 15)); // A-C

        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(1)), mnfld.getPipe(names.get(3)), 3)); // B-D
        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(1)), mnfld.getPipe(names.get(5)), 5)); // B-F

        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(2)), mnfld.getPipe(names.get(4)), 30)); // C-E

        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(3)), mnfld.getPipe(names.get(4)), 20)); // D-E
        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(3)), mnfld.getPipe(names.get(6)), 14)); // D-G

        mnfld.insertConnection(new Edge(mnfld.getPipe(names.get(6)), mnfld.getPipe(names.get(4)), 3)); // G-E

        // -- Running Dijkstra & Finding shortest Paths -- //

        int cons;
        String destName = names.get(4);
        Node p1;

        Dijkstra.findMinPaths(mnfld, mnfld.getPipe(names.get(0)));
        mnfld.printDistanceTree();
        System.out.print("\nShortest Path: ");
        mnfld.printPipeLine(names.get(4));
        // TODO: Implement method to remove edge and calculate new shortest cost with out the edge, used to find new routes for the destination

        cons = mnfld.getPipe(destName).pipesInRoute();
        System.out.println(cons);

        p1 = mnfld.getPipe(destName).getRoute(cons - 1);

        mnfld.dropConnection(p1.getName(), destName);

        Dijkstra.resetCosts(mnfld);

        Dijkstra.findMinPaths(mnfld, mnfld.getPipe(names.get(0)));
        mnfld.printDistanceTree();
        System.out.print("\n\nShortest Path: ");
        mnfld.printPipeLine(names.get(4));
    }


}
