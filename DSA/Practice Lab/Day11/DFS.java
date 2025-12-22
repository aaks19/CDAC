
import java.util.ArrayList;
import java.util.List;

public class DFS {
    
    public int noOfVertices;
    public List<List<Integer>> adjacencyList = new ArrayList<>();

    public DFS(int noOfVertices){
        this.noOfVertices = noOfVertices;
        for(int i=0 ; i<noOfVertices ; i++){
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination){
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void displayAdjacencyList(){
        for(int i=0 ; i<noOfVertices ; i++){
            System.out.print(i+" -> ");
            for(int neighbor : adjacencyList.get(i)){
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int startVertex){
        System.out.println("DFS Traversal");
        boolean[] visited = new boolean[noOfVertices];
        dfsHelper(startVertex, visited);
    }

    public void dfsHelper(int startVertex, boolean[] visited){
        visited[startVertex] = true;
        System.out.print(startVertex + " ");
        for(int neighbor : adjacencyList.get(startVertex)){
            if(!visited[neighbor]){
                dfsHelper(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        DFS graph = new DFS(5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,4);
        graph.addEdge(2,3);
        graph.addEdge(1,3);
        graph.addEdge(3,4);

        graph.displayAdjacencyList();

        System.out.println("-------------------------------------------");
        graph.dfs(0);

    }
}
