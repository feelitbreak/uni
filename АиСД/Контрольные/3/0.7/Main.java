import java.io.*;

class Graph {
    private final int n;
    private final int[] mass;
    private final StreamTokenizer st;

    public Graph() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        mass = new int[n];
    }

    public void formMass() throws IOException {
        int hasEdge;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                st.nextToken();
                hasEdge = (int) st.nval;
                if(hasEdge == 1) {
                    mass[j] = i + 1;
                }
            }
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        for(int i = 0; i < n; i++) {
            pw.print(mass[i]);
            pw.print(' ');
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