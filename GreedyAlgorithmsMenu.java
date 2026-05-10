import java.util.*;

// Edge class for Kruskal
class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

public class GreedyAlgorithmsMenu {

    static Scanner sc = new Scanner(System.in);

    // =====================================================
    // 1. SELECTION SORT
    // =====================================================
    static void selectionSort() {

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Selection Sort Logic
        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        System.out.println("\nSorted Array:");

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    // =====================================================
    // 2. KRUSKAL MST
    // =====================================================

    static int[] parent;

    // Find parent
    static int find(int i) {

        if (parent[i] == i)
            return i;

        return find(parent[i]);
    }

    // Union
    static void union(int x, int y) {

        int xset = find(x);
        int yset = find(y);

        parent[xset] = yset;
    }

    static void kruskalMST() {

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();

        System.out.println("Enter source destination weight:");

        for (int i = 0; i < E; i++) {

            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();

            edges.add(new Edge(s, d, w));
        }

        // Sort edges
        edges.sort(Comparator.comparingInt(e -> e.weight));

        parent = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        System.out.println("\nEdges in MST:");

        for (Edge e : edges) {

            int x = find(e.src);
            int y = find(e.dest);

            // Avoid cycle
            if (x != y) {

                System.out.println(
                        e.src + " - " +
                                e.dest + " : " +
                                e.weight);

                totalCost += e.weight;

                union(x, y);
            }
        }

        System.out.println("Total Cost = " + totalCost);
    }

    // =====================================================
    // 3. DIJKSTRA SHORTEST PATH
    // =====================================================

    static final int INF = 9999;

    static int minDistance(int[] dist,
            boolean[] visited) {

        int min = INF;
        int minIndex = -1;

        for (int i = 0; i < dist.length; i++) {

            if (!visited[i] &&
                    dist[i] <= min) {

                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    static void dijkstra() {

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < V; i++) {

            for (int j = 0; j < V; j++) {

                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, INF);

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);

            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != INF
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nShortest Distances:");

        for (int i = 0; i < V; i++) {

            System.out.println(
                    src + " -> " +
                            i + " = " +
                            dist[i]);
        }
    }

    // =====================================================
    // MAIN MENU
    // =====================================================

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("   GREEDY ALGORITHMS MENU");
            System.out.println("=================================");

            System.out.println("1. Selection Sort");
            System.out.println("2. Kruskal Minimum Spanning Tree");
            System.out.println("3. Dijkstra Shortest Path");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    selectionSort();
                    break;

                case 2:
                    kruskalMST();
                    break;

                case 3:
                    dijkstra();
                    break;

                case 4:
                    System.out.println("Program Exited.");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}