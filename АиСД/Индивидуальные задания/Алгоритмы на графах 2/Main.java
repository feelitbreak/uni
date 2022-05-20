import java.io.*;

class Graph {
    private final int n;
    private final boolean[][] matrix;

    public Graph() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        matrix = new boolean[n][n];

        int x;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                st.nextToken();
                x = (int) st.nval;
                matrix[i][j] = x != 0;
            }
        }
    }

    public void findSpanningTree() {
        
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.findSpanningTree();
    }
}
