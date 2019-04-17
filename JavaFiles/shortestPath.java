import java.util.ArrayList;
import java.util.Arrays;

public class shortestPath {

    public static void main(String[] args) {

        System.out.println("Creating Graph");

        Graph g = new Graph();

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

        int index = 0;
        for (String name :
                names) {
            index = name.indexOf("->");
            if (index > 0) {
                String src = name.substring(0, index);
                String dst = name.substring(index + 2, name.length());
                g.addPipe(new Node(src, dst, 100));
            } else {
                g.addPipe(new Node(name));
            }


        }


        g.addPossibleConnection(new Edge(g.getPipe(names.get(0)), g.getPipe(names.get(1)), 10)); // A-B
        g.addPossibleConnection(new Edge(g.getPipe(names.get(0)), g.getPipe(names.get(2)), 15)); // A-C

        g.addPossibleConnection(new Edge(g.getPipe(names.get(1)), g.getPipe(names.get(3)), 3)); // B-D
        g.addPossibleConnection(new Edge(g.getPipe(names.get(1)), g.getPipe(names.get(5)), 5)); // B-F

        g.addPossibleConnection(new Edge(g.getPipe(names.get(2)), g.getPipe(names.get(4)), 30)); // C-E

        g.addPossibleConnection(new Edge(g.getPipe(names.get(3)), g.getPipe(names.get(4)), 20)); // D-E
        g.addPossibleConnection(new Edge(g.getPipe(names.get(3)), g.getPipe(names.get(6)), 14)); // D-G

        g.addPossibleConnection(new Edge(g.getPipe(names.get(6)), g.getPipe(names.get(4)), 3)); // G-E

        g = Dijkstra.calcCheapestWineLine(g, g.getPipe(names.get(0)));

        g.printPipeLine(names.get(4));
    }


}
