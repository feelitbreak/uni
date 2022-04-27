import java.io.*;
class Graph {
    private final int n;
    private int[] mass;
    private StreamTokenizer st;

    public Graph() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        mass = new int[n];
    }

    public void formMass() throws IOException {
        int u;
        int v;
        for(int i = 0; i < n - 1; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            mass[v - 1] = u;
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(mass[0]);
        for(int i = 1; i < n; i++) {
            pw.print(' ');
            pw.print(mass[i]);
        }
        pw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.formMass();
        myGraph.out();
    }
}
