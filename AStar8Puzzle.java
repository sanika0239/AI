import java.util.*;

class Node {

    int[][] state;
    int x, y;
    int cost;
    int level;
    Node parent;

    Node(int[][] state, int x, int y, int level, Node parent) {

        this.state = new int[3][3];

        for (int i = 0; i < 3; i++)
            this.state[i] = state[i].clone();

        this.x = x;
        this.y = y;
        this.level = level;
        this.parent = parent;
    }
}

public class AStar8Puzzle {

    static int[][] goal = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
    };

    // Calculate heuristic (Misplaced Tiles)
    static int calculateCost(int[][] state) {

        int count = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (state[i][j] != 0 &&
                        state[i][j] != goal[i][j]) {

                    count++;
                }
            }
        }

        return count;
    }

    // Print matrix
    static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {

            for (int val : row) {
                System.out.print(val + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    // Check valid position
    static boolean isSafe(int x, int y) {

        return (x >= 0 && x < 3 &&
                y >= 0 && y < 3);
    }

    // Create new node
    static Node newNode(Node parent,
            int newX, int newY,
            int x, int y) {

        int[][] newState = new int[3][3];

        for (int i = 0; i < 3; i++)
            newState[i] = parent.state[i].clone();

        // Swap blank tile
        int temp = newState[newX][newY];
        newState[newX][newY] = newState[x][y];
        newState[x][y] = temp;

        Node node = new Node(newState,
                newX, newY,
                parent.level + 1,
                parent);

        node.cost = calculateCost(newState);

        return node;
    }

    // Print path
    static void printPath(Node root) {

        if (root == null)
            return;

        printPath(root.parent);
        printMatrix(root.state);
    }

    // A* Algorithm
    static void solve(int[][] initial, int x, int y) {

        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> (a.cost + a.level)));

        Node root = new Node(initial, x, y, 0, null);

        root.cost = calculateCost(initial);

        pq.add(root);

        int[] row = { 1, 0, -1, 0 };
        int[] col = { 0, -1, 0, 1 };

        while (!pq.isEmpty()) {

            Node min = pq.poll();

            if (min.cost == 0) {

                System.out.println("Solution Found:\n");
                printPath(min);
                return;
            }

            for (int i = 0; i < 4; i++) {

                int newX = min.x + row[i];
                int newY = min.y + col[i];

                if (isSafe(newX, newY)) {

                    Node child = newNode(min,
                            newX, newY,
                            min.x, min.y);

                    pq.add(child);
                }
            }
        }
    }

    public static void main(String[] args) {

        // for hard coded
        // int[][] initial = {
        // { 1, 2, 3 },
        // { 5, 6, 0 },
        // { 7, 8, 4 }
        // };
        // int x = 1, y = 2;

        //dynamic
        Scanner sc=new Scanner(System.in);
        int N = 3;
        int[][] initial = new int[N][N];

        int x = -1, y = -1;

        System.out.println("Enter elements of 3x3 matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                initial[i][j] = sc.nextInt();

                // Find position of 0 (blank tile)
                if (initial[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }

        
        }
        solve(initial, x, y);
    }
}