import java.io.*;
import java.util.*;

class Edge {
    private final int u;
    private final int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (u == edge.u && v == edge.v) || (v == edge.u && u == edge.v);
    }
}

class Graph {
    private final int n;
    private final Set<Edge> edges;
    private final int[] dsu;
    private int m = 0;
    private final boolean[] processed;
    private boolean hasTree = false;

    public Graph() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        edges = new HashSet<>((int) Math.pow(n, 2));
        dsu = new int[n];
        processed = new boolean[n];

        int x;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                st.nextToken();
                x = (int) st.nval;
                if(x == 1) {
                    edges.add(new Edge(i + 1, j + 1));
                }
            }
        }

        Arrays.fill(dsu, -1);
    }

    public void findSpanningTree() {
        int u;
        int v;
        for(Edge edge : edges) {
            u = edge.getU();
            v = edge.getV();

            if(buildRoad(u, v)) {
                m++;
                processed[u - 1] = true;
                processed[v - 1] = true;
            } else {
                edges.remove(edge);
            }
        }

        for(int i = 0; i < n; i++) {
            if(!processed[i]) {
                hasTree = false;
                break;
            }
        }
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        if(!hasTree) {
            pw.print(-1);
        } else {
            pw.println(m);
            for(Edge edge : edges) {
                pw.print(edge.getU());
                pw.print(' ');
                pw.println(edge.getV());
            }
        }
        pw.close();
    }

    private boolean buildRoad(int u, int v) {
        int uLeader;
        int vLeader;
        uLeader = findSet(u);
        vLeader = findSet(v);

        if (uLeader != vLeader) {
            if (dsu[uLeader - 1] < dsu[vLeader - 1]) {
                dsu[vLeader - 1] = uLeader;
                dsu[uLeader - 1]--;
            } else {
                dsu[uLeader - 1] = vLeader;
                dsu[vLeader - 1]--;
            }
            return true;
        }
        return false;
    }

    private int findSet(int u) {
        if(dsu[u - 1] > 0) {
            u = findSet(dsu[u - 1]);
        }
        return u;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.findSpanningTree();
        myGraph.out();
    }
}
