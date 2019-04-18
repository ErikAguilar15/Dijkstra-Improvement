import java.util.ArrayList;
import java.util.Arrays;

public class shortestPath {

    public static void main(String[] args) {

        System.out.println("Creating Graph");
        Graph mnfld = new Graph();
        int index;

        ArrayList<String> names = new ArrayList<>(
                Arrays.asList(
                        "ATank",     //0
                        "B345->678", //1
                        "C456->789", //2
                        "D567->890", //3
                        "ETank",     //4
                        "F678->901", //5
                        "G345->045"  //6
                )
        );

        for (String name : names) {
            index = name.indexOf("->");
            if (index > 0) {
                String src = name.substring(0, index);
                String dst = name.substring(index + 2);
                mnfld.addPipe(new Node(src, dst, 0));
            } else {
                mnfld.addPipe(new Node(name, 0));
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

        Dijkstra.findMinPaths(mnfld, mnfld.getPipe(names.get(0)));

        mnfld.printDistanceTree();

        System.out.print("\n\nShortest Path: ");
        mnfld.printPipeLine(names.get(4));
    }


}
