public class AdjacencyMatrix {

    public static void main(String[] args) {
        int vertice = 4;

        System.out.println("ADJACENCY MATRIX\n4 Vertex Graph (Each vertex connected to all others)");

        int[][] matrix = new int[vertice][vertice];


        for (int i = 0; i < vertice; i++) {
            for (int j = 0; j < vertice; j++) {
                if (i != j) {
                    matrix[i][j] = 1;
                }
            }
        }


        for (int i = 0; i < vertice; i++) {
            for (int j = 0; j < vertice; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
