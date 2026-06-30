package JavaCoding.DAA.Assignment2;

import java.util.*;

public class EdmondsKarpAlgorithm {

    static final int V = 7;
    static final String[] NAMES = {"A", "B", "C", "D", "E", "F", "G"};

    static int[][] cap = new int[V][V];
    static int[][] origCap = new int[V][V];
    static int[] parent = new int[V];


    static boolean bfs(int source, int sink) {
        Arrays.fill(parent, -2);
        parent[source] = -1;

        Queue<Integer> q = new LinkedList<>();
        q.add(source);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < V; v++) {
                if (parent[v] == -2 && cap[u][v] > 0) {
                    parent[v] = u;
                    if (v == sink) return true;
                    q.add(v);
                }
            }
        }
        return false;
    }

    static int edmondsKarp(int source, int sink) {
        int maxFlow = 0;

        while (bfs(source, sink)) {


            int bottleneck = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v])
                bottleneck = Math.min(bottleneck, cap[parent[v]][v]);

            List<Integer> path = new ArrayList<>();
            for (int v = sink; v != source; v = parent[v]) path.add(v);
            path.add(source);
            Collections.reverse(path);

            System.out.print("Path: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(NAMES[path.get(i)]);
                if (i != path.size() - 1) System.out.print(" -> ");
            }
            System.out.println("   (bottleneck = " + bottleneck + ")");

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                cap[u][v] -= bottleneck;
                cap[v][u] += bottleneck;
            }
            maxFlow += bottleneck;
        }
        return maxFlow;
    }


    static void printMinCut(int source) {
        boolean[] reachable = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        reachable[source] = true;
        q.add(source);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < V; v++) {
                if (!reachable[v] && cap[u][v] > 0) {
                    reachable[v] = true;
                    q.add(v);
                }
            }
        }

        System.out.println();
        System.out.println("Min cut edges:");
        int cutValue = 0;
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (reachable[u] && !reachable[v] && origCap[u][v] > 0) {
                    System.out.println("  " + NAMES[u] + " -> " + NAMES[v] + "  (capacity " + origCap[u][v] + ")");
                    cutValue += origCap[u][v];
                }
            }
        }
        System.out.println("Total cut capacity: " + cutValue);
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 3, 3}, // A -> D
                {2, 0, 3}, // C -> A
                {0, 1, 3}, // A -> B
                {2, 3, 1}, // C -> D
                {2, 4, 2}, // C -> E
                {1, 2, 4}, // B -> C
                {3, 5, 6}, // D -> F
                {3, 4, 2}, // D -> E
                {4, 1, 1}, // E -> B
                {4, 6, 1}, // E -> G
                {5, 6, 9}, // F -> G
        };

        for (int[] e : edges) {
            cap[e[0]][e[1]] = e[2];
            origCap[e[0]][e[1]] = e[2];
        }

        System.out.println("Edmonds-Karp Max Flow (source A, sink G)");
        System.out.println();

        int maxFlow = edmondsKarp(0, 6);

        System.out.println();
        System.out.println("Maximum flow = " + maxFlow);

        printMinCut(0);
    }
}