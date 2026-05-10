import java.util.*;

class Node {

    int[] board; // board[row] = column position
    int row;
    int g; // cost so far
    int h; // heuristic
    int f; // total cost

    Node(int[] board, int row, int g) {

        this.board = board.clone();
        this.row = row;
        this.g = g;

        this.h = calculateHeuristic(board, row);

        this.f = g + h;
    }

    // Heuristic Function
    // Counts attacking queen pairs
    static int calculateHeuristic(int[] board, int row) {

        int conflicts = 0;

        for (int i = 0; i < row; i++) {

            for (int j = i + 1; j < row; j++) {

                // Same column
                if (board[i] == board[j]) {
                    conflicts++;
                }

                // Same diagonal
                else if (Math.abs(i - j) ==
                        Math.abs(board[i] - board[j])) {

                    conflicts++;
                }
            }
        }

        return conflicts;
    }
}

public class NQueensAStar {

    static int N;

    // Print chessboard
    static void printBoard(int[] board) {

        System.out.println("\nSolution:\n");

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if (board[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }

            System.out.println();
        }
    }

    // A* Algorithm
    static void solve() {

        PriorityQueue<Node> pq =
                new PriorityQueue<>(
                        Comparator.comparingInt(n -> n.f));

        // Initialize board with -1
        int[] startBoard = new int[N];

        Arrays.fill(startBoard, -1);

        // Root node
        pq.add(new Node(startBoard, 0, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            // Goal state
            if (current.row == N &&
                    current.h == 0) {

                printBoard(current.board);
                return;
            }

            int row = current.row;

            // Try all columns
            for (int col = 0; col < N; col++) {

                int[] newBoard = current.board.clone();

                newBoard[row] = col;

                Node child =
                        new Node(newBoard,
                                row + 1,
                                current.g + 1);

                pq.add(child);
            }
        }

        System.out.println("No Solution Found.");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");

        N = sc.nextInt();

        solve();

        sc.close();
    }
}