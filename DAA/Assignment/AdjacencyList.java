
import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {
        public static void main(String[] args) {
            int vertics = 4;

            System.out.println("ADJACENCY LIST\n4 Vertex Graph (Each vertex connected to all others)");

            List<Integer>[] adjList = new ArrayList[vertics];

            for (int i = 0; i < vertics; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < vertics; i++) {
                for (int j = 0; j < vertics; j++) {
                    if (i != j) {
                        adjList[i].add(j);
                    }
                }
            }

            for (int i = 0; i < vertics; i++) {
                System.out.print(i + "   ->   ");
                for (int neighbor : adjList[i]) {
                    System.out.print(neighbor + "   ");
                }
                System.out.println();
            }
        }
    }
