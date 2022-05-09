import java.io.*;

class Graph {
    private final int n;
    private final int[][] matrix;
    private final int[] marks;
    private int ind = 0;

    public Graph() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        matrix = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                st.nextToken();
                matrix[i][j] = (int) st.nval;
            }
        }
        marks = new int[n];
    }

    public void dfs() {
        for(int i = 0; i < n; i++) {
            if(marks[i] == 0) {
                dfsRec(i);
            }
        }
    }

    private void dfsRec(int v) {
        ind++;
        marks[v] = ind;
        for(int i = 0; i < n; i++) {
            if(matrix[v][i] == 1 && marks[i] == 0) {
                dfsRec(i);
            }
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(marks[0]);
        for(int i = 1; i < n; i++) {
            pw.print(' ');
            pw.print(marks[i]);
        }
        pw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.dfs();
        myGraph.out();
    }
}