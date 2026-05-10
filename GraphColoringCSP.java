import java.util.Scanner;

public class GraphColoringCSP {

    int V;
    int[][] graph;
    int[] color;

    public GraphColoringCSP(int v) {
        V = v;
        graph = new int[V][V];
        color = new int[V];
    }

    // Check if color assignment is safe (Branch & Bound)
    boolean isSafe(int v, int c) {

        for (int i = 0; i < V; i++) {

            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function
    boolean solve(int v, int m) {

        // All vertices colored
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

                // Backtracking
                color[v] = 0;
            }
        }

        return false;
    }

    // Print solution
    void printSolution() {

        System.out.println("\nVertex Coloring Result:");

        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " → Color " + color[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        GraphColoringCSP g = new GraphColoringCSP(V);

        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                g.graph[i][j] = sc.nextInt();
            }
        }

        if (g.solve(0, m)) {
            g.printSolution();
        } else {
            System.out.println("No solution exists with " + m + " colors.");
        }

        sc.close();
    }
} 