import java.util.Scanner;

public class Dijkstra {
    
    /*
     * Implement Dijkstra's algorithm with weighted graphs.
     * Your code should read a text file and then ask for input from the console
     * for start and destination nodes and use Dijsktra's algorithm to find the
     * "length" of the shortest path between the two nodes. It should also display
     * the sequence of nodes that make up the shortest path. You can assume that
     * the associated graph is connected and that the input file is consistent.
     */

    static int[][] graph = {
        {0, 54, 11, 13, 0, 0, 0, 0, 0, 0},
        {54, 0, 37, 0, 3, 0, 102, 0, 0, 0},
        {11, 37, 0, 10, 36, 19, 0, 0, 0, 0},
        {13, 0, 10, 0, 0, 18, 0, 0, 7, 0},
        {0, 3, 36, 0, 0, 15, 124, 123, 0, 0},
        {0, 0, 19, 18, 15, 0, 0, 138, 8, 0},
        {0, 102, 0, 0, 124, 0, 0, 9, 0, 72},
        {0, 0, 0, 0, 123, 138, 9, 0, 146, 67},
        {0, 0, 0, 7, 0, 8, 0, 146, 0, 213},
        {0, 0, 0, 0, 0, 0, 72, 67, 213, 0}
    };

    static int[][] readGraph(String fileName) throws Exception {
        
        Scanner file = new Scanner(new java.io.File(fileName));
        int n = file.nextInt();

        int[][] weightedGraph = new int[n][n];

        for (int i = 0; i < n; i++) {
            int node = file.nextInt();
            int edges = file.nextInt();
            for (int j = 0; j < edges; j++) {
                int neighbor = file.nextInt();
                int weight = file.nextInt();
                weightedGraph[node][neighbor] = weight;
            }
        }


        return weightedGraph;
    }

    static void dijkstra(int start, int destination) {
        int[] distance = new int[graph.length];
        int[] previous = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            previous[i] = -1;
            visited[i] = false;
        }

        distance[start] = 0;

        for (int i = 0; i < graph.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && distance[j] < min) {
                    min = distance[j];
                    minIndex = j;
                }
            }
            visited[minIndex] = true;
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && graph[minIndex][j] != 0 && distance[minIndex] + graph[minIndex][j] < distance[j]) {
                    distance[j] = distance[minIndex] + graph[minIndex][j];
                    previous[j] = minIndex;
                }
            }
        }

        System.out.println("Shortest path from " + start + " to " + destination + ": " + distance[destination]);
        System.out.print("Path: ");
        int current = destination;
        while (current != start) {
            System.out.print(current + " ");
            current = previous[current];
        }
        System.out.println(start);
    }


    public static void main(String[] args) {
    
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the start node: ");
            int start = sc.nextInt();
            System.out.println("Enter the destination node: ");
            int destination = sc.nextInt();
            sc.close();

            // TODO: implement Dijkstra's algorithm here
            dijkstra(start, destination);
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a positive integer.");
        }
    
    }


}
