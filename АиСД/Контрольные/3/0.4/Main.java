import java.io.*;
class Graph {
    private final int n;
    private final int m;
    private boolean[][] matrix;
    private StreamTokenizer st;

    public Graph() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        m = (int) st.nval;
        matrix = new boolean[n][n];
    }

    public void formMatrix() throws IOException {
        int u;
        int v;
        for(int i = 0; i < m; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            matrix[u - 1][v - 1] = true;
            matrix[v - 1][u - 1] = true;
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        for(int i = 0; i < n; i++) {
            if(matrix[i][0]) {
                pw.print('1');
            } else {
                pw.print('0');
            }
            for(int j = 1; j < n; j++) {
                pw.print(' ');
                if(matrix[i][j]) {
                    pw.print('1');
                } else {
                    pw.print('0');
                }
            }
            pw.print('\n');
        }
        pw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.formMatrix();
        myGraph.out();
    }
}
