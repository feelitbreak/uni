import java.io.*;
import java.util.*;

class Edge {
    private final int source;
    private final int capacity;
    private final int flow;
    private final int weight;
    private final int target;

    public Edge(int source, int capacity, int flow, int weight, int target) {
        this.source = source;
        this.capacity = capacity;
        this.flow = flow;
        this.weight = weight;
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public int getWeight() {
        return weight;
    }

    public int getTarget() {
        return target;
    }
}

class AgeGroups {
    private int nVertices1 = 0;
    private int nVertices2 = 0;
    private int n = 0;
    private int m = 0;

    protected int[] group1;
    protected int[] group2;

    protected static final int MIN_AGE = 18;
    protected static final int MAX_AGE = 60;
    protected static final int SINGLE_ROOM_VERTEX = MAX_AGE - MIN_AGE + 1;

    protected AgeGroups() throws IOException {
        this.group1 = new int[MAX_AGE - MIN_AGE + 1];
        this.group2 = new int[MAX_AGE - MIN_AGE + 2];
        this.inputGroups();

        if (this.m > this.n)
        {
            this.switchGroups();
            this.switchVertices();
        }

        if (this.n != this.m) {
            this.group2[SINGLE_ROOM_VERTEX] = Math.abs(this.n - this.m);
            this.nVertices2++;
        }
    }
    
    protected int getNumOfFlowEdges() {
        return (this.nVertices1 * this.nVertices2 + this.nVertices1 + this.nVertices2) * 2;
    }

    protected int getNumOfVertices() {
        return this.nVertices1 + this.nVertices2 + 2;
    }

    protected int getNumOfVerticesInGroup1() {
        return this.nVertices1;
    }

    private void inputGroups()  throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.eolIsSignificant(true);
        st.nextToken();
        while (st.ttype != StreamTokenizer.TT_EOL) {
            if (this.group1[((int) st.nval) - MIN_AGE] == 0) {
                this.nVertices1++;
            }

            this.n++;
            this.group1[((int) st.nval) - MIN_AGE]++;
            st.nextToken();
        }

        st.nextToken();
        while (st.ttype != StreamTokenizer.TT_EOF) {
            if (this.group2[((int) st.nval) - MIN_AGE] == 0) {
                this.nVertices2++;
            }

            this.m++;
            this.group2[((int) st.nval) - MIN_AGE]++;
            st.nextToken();
        }
    }
    
    private void switchGroups() {
        int[] temp = this.group1;
        this.group1 = this.group2;
        this.group2 = temp;
    }

    private void switchVertices() {
        int temp = this.nVertices1;
        this.nVertices1 = this.nVertices2;
        this.nVertices2 = temp;
    }
}

class InitialGraph extends AgeGroups {
    protected final Edge[] flowEdges;
    protected final Map<Integer, List<Integer>> network;
    protected final int s;
    protected final int t;
    
    protected InitialGraph() throws IOException {
        this.flowEdges = new Edge[this.getNumOfFlowEdges()];

        final double defaultLoadFactor = 0.75;
        this.network = new HashMap<>((int)(this.getNumOfVertices() / defaultLoadFactor) + 1);

        this.s = 1;
        this.t = this.getNumOfVertices();
        this.buildNetwork();
    }

    private void buildNetwork() {
        int i = 0;
        i = this.connectSToGroup1(i);
        i = this.connectGroups(i);
        i = this.connectGroup2ToT(i);

        assert(i == this.getNumOfFlowEdges());
    }

    private int connectSToGroup1(int i) {
        network.put(this.s, new ArrayList<>());

        int vertexNum = this.s + 1;
        for (int nPeople : this.group1) {
            if (nPeople != 0) {
                network.put(vertexNum, new ArrayList<>());

                flowEdges[i] = new Edge(this.s, nPeople, 0, 0, vertexNum);
                network.get(this.s).add(i);
                i++;

                flowEdges[i] = new Edge(vertexNum, 0, 0, 0, this.s);
                network.get(vertexNum).add(i);
                i++;

                vertexNum++;
            }
        }

        return i;
    }

    private int connectGroups(int i) {
        int vertexNumGroup1 = this.s + 1;
        int vertexNumGroup2 = this.s + this.getNumOfVerticesInGroup1() + 1;
        for (int j = 0; j < this.group1.length; j++) {
            if (group1[j] != 0) {
                for (int k = 0; k < this.group2.length; k++) {
                    if (group2[k] != 0) {
                        network.put(vertexNumGroup2, new ArrayList<>());

                        int weight;
                        if (k == SINGLE_ROOM_VERTEX) {
                            weight = j + MIN_AGE;
                        } else {
                            weight = 2 * Math.abs(j - k);
                        }

                        flowEdges[i] = new Edge(
                                vertexNumGroup1,
                                Math.min(group1[j], group2[k]),
                                0,
                                weight,
                                vertexNumGroup2);
                        network.get(vertexNumGroup1).add(i);
                        i++;

                        flowEdges[i] = new Edge(
                                vertexNumGroup2,
                                0,
                                0,
                                - weight,
                                vertexNumGroup1);
                        network.get(vertexNumGroup2).add(i);
                        i++;

                        vertexNumGroup2++;
                    }
                }

                vertexNumGroup1++;
            }
        }
        return i;
    }

    private int connectGroup2ToT(int i) {
        network.put(this.t, new ArrayList<>());

        int vertexNum = this.s + this.getNumOfVerticesInGroup1() + 1;
        for (int nPeople : group2) {
            if (nPeople != 0) {
                flowEdges[i] = new Edge(vertexNum, nPeople, 0, 0, this.t);
                network.get(vertexNum).add(i);
                i++;

                flowEdges[i] = new Edge(this.t, 0, 0, 0, vertexNum);
                network.get(this.t).add(i);
                i++;

                vertexNum++;
            }
        }
        return i;
    }
}

class Graph extends InitialGraph {
    private final int result = 0;

    public Graph() throws IOException {
        
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.printf("%.1f", this.result / 2.0);
        pw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.out();
    }
}
