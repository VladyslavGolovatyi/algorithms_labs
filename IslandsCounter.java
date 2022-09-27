import java.util.LinkedList;
import java.util.Queue;

public class IslandsCounter {
    static final int ROWS = 10;
    static final int COLUMNS = 10;

    static boolean isUnvisitedLand(int[][] matrix, int i, int j, boolean[][] visited) {
        return (i >= 0 && i < ROWS && j >= 0 && j < COLUMNS && matrix[i][j] == 1 && !visited[i][j]);
    }

    static void BFS(int[][] matrix, boolean[][] visited, int i, int j) {

        int[] rowNumbers = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNumbers = {-1, 0, 1, -1, 1, -1, 0, 1};

        Queue<pair> q = new LinkedList<>();
        q.add(new pair(i, j));
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int adjacentRow = q.peek().first;
            int adjacentColumn = q.peek().second;
            q.remove();

            for (int k = 0; k < 8; k++) {
                if (isUnvisitedLand(matrix, adjacentRow + rowNumbers[k], adjacentColumn + colNumbers[k], visited)) {
                    visited[adjacentRow + rowNumbers[k]][adjacentColumn + colNumbers[k]] = true;
                    q.add(new pair(adjacentRow + rowNumbers[k], adjacentColumn + colNumbers[k]));
                }
            }
        }
    }

    static int countIslands(int[][] matrix) {
        boolean[][] visited = new boolean[ROWS][COLUMNS];

        int numberOfIslands = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    BFS(matrix, visited, i, j);
                    numberOfIslands++;
                }
            }
        }
        return numberOfIslands;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 1, 1, 1}
        };

        System.out.print(countIslands(matrix));
    }

    static class pair {
        int first, second;

        public pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}
