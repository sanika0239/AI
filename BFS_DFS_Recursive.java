import java.util.*;

public class BFS_DFS_Recursive {

    private int V;
    private LinkedList<Integer>[] adj;

    // Constructor
    BFS_DFS_Recursive(int vertices) {

        V = vertices;
        adj = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Add Edge (Undirected Graph)
    void addEdge(int u, int v) {

        adj[u].add(v);
        adj[v].add(u);
    }

    // ---------------- DFS RECURSIVE ----------------
    void DFS(int start) {

        boolean[] visited = new boolean[V];

        System.out.print("DFS Traversal: ");
        DFSRecursive(start, visited);

        System.out.println();
    }

    // Recursive DFS Function
    void DFSRecursive(int node, boolean[] visited) {

        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adj[node]) {

            if (!visited[neighbor]) {
                DFSRecursive(neighbor, visited);
            }
        }
    }

    // ---------------- BFS ----------------
    void BFS(int start) {

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int node = queue.poll();

            System.out.print(node + " ");

            for (int neighbor : adj[node]) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        BFS_DFS_Recursive g = new BFS_DFS_Recursive(vertices);

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        System.out.println("Enter edges (u v):");

        for (int i = 0; i < edges; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            g.addEdge(u, v);
        }

        int choice;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. DFS Traversal (Recursive)");
            System.out.println("2. BFS Traversal");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter starting vertex for DFS: ");
                    int dfsStart = sc.nextInt();

                    g.DFS(dfsStart);
                    break;

                case 2:

                    System.out.print("Enter starting vertex for BFS: ");
                    int bfsStart = sc.nextInt();

                    g.BFS(bfsStart);
                    break;

                case 3:

                    System.out.println("Program Exited.");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);

        sc.close();
    }
}