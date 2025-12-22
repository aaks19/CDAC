
import java.util.ArrayList;
import java.util.List;

public class GraphPractice {

    public int noOfVertices;
    public List<List<Integer>> adjacencyList = new ArrayList<>();

    public GraphPractice(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        for (int i = 0; i < noOfVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void displayAdjacencyList() {
        for (int i = 0; i < noOfVertices; i++) {
            System.out.print(i+" -> ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphPractice graph = new GraphPractice(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        graph.displayAdjacencyList();

    }

}
