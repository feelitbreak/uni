import java.io.*;
import java.util.*;

class Graph {
    private final static int n = 7;
    private final int N;
    private final boolean[][] matrix;
    private final boolean[] visited;
    private final int[] deg;
    private final StreamTokenizer st;
    private boolean answer = true;

    public Graph() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        N = (int) st.nval;
        matrix = new boolean[n][n];
        visited = new boolean[n];
        deg = new int[n];
    }

    public void formMatrix() throws IOException {
        int u;
        int v;
        for(int i = 0; i < N; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            matrix[u][v] = true;
            matrix[v][u] = true;
            deg[u]++;
            deg[v]++;
        }
    }

    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        int i = 0;
        for (; i < n; i++) {
            if (deg[i] != 0) {
                break;
            }
        }

        queue.add(i);
        int j;
        while (!queue.isEmpty()) {
            j = queue.poll();
            if (!visited[j]) {
                visited[j] = true;
                for (int k = 0; k < n; k++) {
                    if (matrix[j][k] && !visited[k]) {
                        queue.add(k);
                    }
                }
            }
        }
    }

    public void check() {
        for(int i = 0; i < n; i++) {
            if(!visited[i] && deg[i] != 0) {
                answer = false;
                break;
            }
        }

        if(answer) {
            for(int i = 0; i < n; i++) {
                if(deg[i] % 2 == 1) {
                    answer = false;
                    break;
                }
            }
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        if(answer) {
            pw.print("Yes");
        } else {
            pw.print("No");
        }
        pw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.formMatrix();
        myGraph.bfs();
        myGraph.check();
        myGraph.out();
    }
}