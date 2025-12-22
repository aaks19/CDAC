
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public int noOfVertices;
    public List<List<Integer>> adjacencyList = new ArrayList<>();

    public BFS(int noOfVertices){
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
        for(int i=0 ; i < noOfVertices ; i++){
            System.out.print(i + " -> ");
            for(int neighbor : adjacencyList.get(i)){
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int startVertex){
        System.out.println("BFS Traversal");
        boolean[] visited = new boolean[noOfVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;

        queue.offer(startVertex);
        while(!queue.isEmpty()){
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for(int neighbor : adjacencyList.get(vertex)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }


    public static void main(String[] args) {
        BFS graph = new BFS(5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,4);
        graph.addEdge(2,3);
        graph.addEdge(1,3);
        graph.addEdge(3,4);

        graph.displayAdjacencyList();

        System.out.println("----------------------------------");
        graph.bfs(0);
    }
}
