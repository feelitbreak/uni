import java.io.*;
import java.util.*;

class Edge {
    private final int vGroup1;
    private final int vGroup2;
    private final int weight;

    public Edge(int vGroup1, int vGroup2, int weight) {
        this.vGroup1 = vGroup1;
        this.vGroup2 = vGroup2;
        this.weight = weight;
    }

    public int getVGroup1() {
        return vGroup1;
    }

    public int getVGroup2() {
        return vGroup2;
    }

    public int getWeight() {
        return weight;
    }
}

class Graph {
    private final int[] agesDept1;
    private final int[] agesDept2;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 60;
    private int n = 0;
    private int m = 0;
    private final Edge[] edges;
    private int dis = 0;

    public Graph() throws IOException {
        agesDept1 = new int[MAX_AGE - MIN_AGE + 1];
        agesDept2 = new int[MAX_AGE - MIN_AGE + 1];
        int ages1 = 0;
        int ages2 = 0;

        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.eolIsSignificant(true);
        st.nextToken();
        while(st.ttype != StreamTokenizer.TT_EOL) {
            if(agesDept1[((int) st.nval) - MIN_AGE] == 0) {
                ages1++;
            }
            agesDept1[((int) st.nval) - MIN_AGE]++;
            n++;
            st.nextToken();
        }
        st.nextToken();
        while(st.ttype != StreamTokenizer.TT_EOF) {
            if(agesDept2[((int) st.nval) - MIN_AGE] == 0) {
                ages2++;
            }
            agesDept2[((int) st.nval) - MIN_AGE]++;
            m++;
            st.nextToken();
        }

        edges = new Edge[ages1 * ages2];
        int k = 0;
        for(int i = 0; i < agesDept1.length; i++) {
            for(int j = 0; j < agesDept2.length; j++) {
                if(agesDept1[i] > 0 && agesDept2[j] > 0) {
                    edges[k] = new Edge(i + MIN_AGE, j + MIN_AGE, 2 * Math.abs(i - j));
                    k++;
                }
            }
        }
    }

    public void findDissatisfaction() {
        Arrays.sort(edges, Comparator.comparingInt(Edge::getWeight));

        int amount;
        for (Edge edge : edges) {
            amount = Math.min(agesDept1[edge.getVGroup1() - MIN_AGE], agesDept2[edge.getVGroup2() - MIN_AGE]);
            if (amount != 0) {
                agesDept1[edge.getVGroup1() - MIN_AGE] -= amount;
                agesDept2[edge.getVGroup2() - MIN_AGE] -= amount;
                dis += amount * edge.getWeight();
            }
        }

        for(int i = 0; i < agesDept1.length; i++) {
            if(agesDept1[i] > 0) {
                dis += agesDept1[i] * (i + MIN_AGE);
                agesDept1[i] = 0;
            }
        }

        for(int i = 0; i < agesDept2.length; i++) {
            if(agesDept2[i] > 0) {
                dis += agesDept2[i] * (i + MIN_AGE);
                agesDept2[i] = 0;
            }
        }
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.printf("%.1f", dis / 2.0);
        pw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.findDissatisfaction();
        myGraph.out();
    }
}
