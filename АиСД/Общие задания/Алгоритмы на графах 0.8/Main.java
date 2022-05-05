import java.io.*;
import java.util.*;

class Graph {
    private final int n;
    private final int[][] matrix;
    private final int[] marks;

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

    public void bfs() {
        int ind = 0;
        for(int i = 0; i < n; i++) {
            if(marks[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                int j;
                while(!queue.isEmpty()) {
                    j = queue.poll();
                    if(marks[j] == 0) {
                        for (int k = 0; k < n; k++) {
                            if (matrix[j][k] == 1) {
                                queue.add(k);
                            }
                        }
                        ind++;
                        marks[j] = ind;
                    }
                }

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
        myGraph.bfs();
        myGraph.out();
    }
}
