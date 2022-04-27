import java.io.*;
import java.util.*;

class Graph {
    private final int n;
    private final int m;
    private final List<Integer>[] mass;
    private StreamTokenizer st;

    public Graph() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        m = (int) st.nval;
        mass = new List[n];
        for(int i = 0; i < n; i++) {
            mass[i] = new ArrayList<>();
        }
    }

    public void formMatrix() throws IOException {
        int u;
        int v;
        for(int i = 0; i < m; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            mass[u - 1].add(v);
            mass[v - 1].add(u);
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        for(int i = 0; i < n; i++) {
            pw.print(mass[i].size());
            for(Integer j : mass[i]) {
                pw.print(' ');
                pw.print(j);
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
