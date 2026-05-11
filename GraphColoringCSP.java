import java.util.Scanner;

public class GraphColoringCSP {

    int V, E;
    int[][] graph;
    int[] color;

    // Constructor
    public GraphColoringCSP(int v, int e) {
        V = v;
        E = e;
        graph = new int[V][V];
        color = new int[V];
    }

    // Check whether color can be assigned
    boolean isSafe(int v, int c) {

        for (int i = 0; i < V; i++) {

            // Adjacent vertex has same color
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function
    boolean solve(int v, int m) {

        // All vertices are colored
        if (v == V) {
            return true;
        }

        // Try all colors
        for (int c = 1; c <= m; c++) {

            if (isSafe(v, c)) {

                color[v] = c;

                // Recur for next vertex
                if (solve(v + 1, m)) {
                    return true;
                }

                // Backtrack
                color[v] = 0;
            }
        }

        return false;
    }

    // Print result
    void printSolution() {

        System.out.println("\nVertex Coloring Result:");

        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " ---> Color " + color[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        // Input edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        GraphColoringCSP g = new GraphColoringCSP(V, E);

        // Input colors
        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();

        System.out.println("Enter edges (source destination):");

        // Input edges
        for (int i = 0; i < E; i++) {

            int src = sc.nextInt();
            int dest = sc.nextInt();

            // Undirected graph
            g.graph[src][dest] = 1;
            g.graph[dest][src] = 1;
        }

        // Solve graph coloring problem
        if (g.solve(0, m)) {
            g.printSolution();
        } else {
            System.out.println("No solution exists with " + m + " colors.");
        }

        sc.close();
    }
}
