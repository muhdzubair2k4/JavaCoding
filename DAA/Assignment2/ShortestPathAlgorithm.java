package JavaCoding.DAA.Assignment2;

import java.util.*;

public class ShortestPathAlgorithm {

    static final int MAX = Integer.MAX_VALUE;
    static final int NODES = 9;

    public static void main(String[] args) {

        int[][] matrix = new int[NODES][NODES];

        int[][] data = {
                {0, 1, 4}, {0, 7, 8},
                {1, 2, 8}, {1, 7, 11},
                {2, 3, 7}, {2, 5, 4}, {2, 8, 2},
                {3, 4, 9}, {3, 5, 14},
                {4, 5, 10},
                {5, 6, 2},
                {6, 7, 1}, {6, 8, 6},
                {7, 8, 7}
        };

        for (int[] edge : data) {
            matrix[edge[0]][edge[1]] = edge[2];
            matrix[edge[1]][edge[0]] = edge[2];
        }

        findShortestPath(matrix, 0);
    }

    static void findShortestPath(int[][] matrix, int source) {

        int[] distance = new int[NODES];
        int[] previous = new int[NODES];
        boolean[] processed = new boolean[NODES];

        Arrays.fill(distance, MAX);
        Arrays.fill(previous, -1);

        distance[source] = 0;

        PriorityQueue<int[]> queue =
                new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        queue.offer(new int[]{0, source});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int currentNode = current[1];

            if (processed[currentNode]) {
                continue;
            }

            processed[currentNode] = true;

            for (int next = 0; next < NODES; next++) {

                if (matrix[currentNode][next] != 0 && !processed[next]) {

                    int totalCost = distance[currentNode] + matrix[currentNode][next];

                    if (distance[currentNode] != MAX && totalCost < distance[next]) {

                        distance[next] = totalCost;
                        previous[next] = currentNode;

                        queue.offer(new int[]{distance[next], next});
                    }
                }
            }
        }

        display(distance, previous, source);
    }

    static void display(int[] distance, int[] previous, int source) {

        System.out.println("Shortest Paths from Node " + source);
        System.out.println();

        System.out.printf("%-8s %-10s %s%n", "Vertex", "Cost", "Route");

        for (int i = 0; i < NODES; i++) {
            System.out.printf("%-8d %-10d %s%n",
                    i, distance[i], printRoute(previous, i));
        }

        System.out.println();
        System.out.println("Edges in Shortest Path Tree");

        for (int i = 0; i < NODES; i++) {
            if (previous[i] != -1) {
                System.out.println(previous[i] + " -> " + i);
            }
        }
    }

    static String printRoute(int[] previous, int vertex) {

        if (previous[vertex] == -1) {
            return String.valueOf(vertex);
        }

        return printRoute(previous, previous[vertex]) + " -> " + vertex;
    }
}