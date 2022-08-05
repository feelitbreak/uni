import java.io.*;
import java.util.*;

class Edge {
    private final int source;
    private final int capacity;
    private int flow;
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

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getWeight() {
        return weight;
    }

    public int getTarget() {
        return target;
    }

    public int available() {
        return this.capacity - this.flow;
    }

    public boolean IsResidual() {
        return this.capacity == 0;
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
            this.addToGroup1((int) st.nval);
            st.nextToken();
        }

        st.nextToken();
        while (st.ttype != StreamTokenizer.TT_EOF) {
            this.addToGroup2((int) st.nval);
            st.nextToken();
        }
    }

    private void addToGroup1(int age)
    {
        if (this.group1[age - MIN_AGE] == 0) {
            this.nVertices1++;
        }

        this.n++;
        this.group1[age - MIN_AGE]++;
    }

    private void addToGroup2(int age)
    {
        if (this.group2[age - MIN_AGE] == 0) {
            this.nVertices2++;
        }

        this.m++;
        this.group2[age - MIN_AGE]++;
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
    protected final int s;
    protected final int t;
    
    protected InitialGraph() throws IOException {
        this.flowEdges = new Edge[super.getNumOfFlowEdges()];
        this.s = 1;
        this.t = super.getNumOfVertices();
        this.buildNetwork();
    }

    protected int getSourceOfEdge(int edgeInd) {
        return this.flowEdges[edgeInd].getSource();
    }

    protected int available(int edgeInd) {
        return this.flowEdges[edgeInd].available();
    }

    private void buildNetwork() {
        int i = 0;
        i = this.connectSToGroup1(i);
        i = this.connectGroups(i);
        i = this.connectGroup2ToT(i);

        assert(i == super.getNumOfFlowEdges());
    }

    private int connectSToGroup1(int i) {
        int vertexNum = this.s + 1;
        for (int nPeople : super.group1) {
            if (nPeople != 0) {
                i = this.addEdge(this.s, nPeople, 0, vertexNum, i);
                i = this.addEdge(vertexNum, 0, 0, this.s, i);
                vertexNum++;
            }
        }

        return i;
    }

    private int connectGroups(int i) {
        int vertexNumGroup1 = this.s + 1;
        for (int j = 0; j < super.group1.length; j++) {
            if (super.group1[j] != 0) {
                i = this.connectToGroup2(i, j, vertexNumGroup1);
                vertexNumGroup1++;
            }
        }

        return i;
    }

    private int connectToGroup2(int i, int j, int vertexNumGroup1) {
        int vertexNumGroup2 = this.s + super.getNumOfVerticesInGroup1() + 1;
        for (int k = 0; k < super.group2.length; k++) {
            if (super.group2[k] != 0) {
                int weight = this.countWeight(j, k);
                int cap = this.countCapacity(j, k);

                i = this.addEdge(vertexNumGroup1, cap, weight, vertexNumGroup2, i);
                i = this.addEdge(vertexNumGroup2, 0, - weight, vertexNumGroup1, i);
                vertexNumGroup2++;
            }
        }

        return i;
    }

    private int countWeight(int j, int k) {
        if (k == SINGLE_ROOM_VERTEX) {
            return j + MIN_AGE;
        } else {
            return 2 * Math.abs(j - k);
        }
    }

    private int countCapacity(int j, int k) {
        return Math.min(super.group1[j], super.group2[k]);
    }

    private int connectGroup2ToT(int i) {
        int vertexNum = this.s + super.getNumOfVerticesInGroup1() + 1;
        for (int nPeople : super.group2) {
            if (nPeople != 0) {
                i = this.addEdge(vertexNum, nPeople, 0, this.t, i);
                i = this.addEdge(this.t, 0, 0, vertexNum, i);
                vertexNum++;
            }
        }

        return i;
    }

    private int addEdge(int source, int cap, int weight, int target, int i) {
        this.flowEdges[i] = new Edge(source, cap, 0, weight, target);
        return i + 1;
    }
}

class Graph extends InitialGraph {
    private long result = 0;
    private final double[] dist;
    private final int[] pred;

    public Graph() throws IOException {
        this.dist = new double[super.getNumOfVertices()];
        this.pred = new int[super.getNumOfVertices()];
    }

    public void findMaxFlowMinCost() {
        while (this.findShortestPathBellmanFord()) {
            this.processFlowFordFulkerson();
        }
    }

    public void countDiscontent() {
        for (int i = 0; i < super.flowEdges.length; i += 2) {
            Edge edge = super.flowEdges[i];
            if (!edge.IsResidual()) {
                result += (long) edge.getFlow() * (long) edge.getWeight();
            }
        }
    }

    public void outResult() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.printf("%.1f", this.result / 2.0);
        pw.close();
    }

    private boolean findShortestPathBellmanFord() {
        this.dist[super.s - 1] = 0;
        Arrays.fill(this.dist, super.s, this.dist.length, Double.POSITIVE_INFINITY);
        Arrays.fill(this.pred, -1);

        for (int i = 0; i < super.getNumOfVertices() - 1; i++) {
            if (!this.performRelaxation()) {
                break;
            }
        }

        return this.dist[super.t - 1] != Double.POSITIVE_INFINITY;
    }

    private boolean performRelaxation() {
        boolean changeHappened = false;
        for (int j = 0; j < super.flowEdges.length; j++) {
            Edge edge = super.flowEdges[j];
            if (edge.available() > 0) {
                int v = edge.getSource();
                int u = edge.getTarget();
                int c = edge.getWeight();

                if (this.dist[u - 1] > this.dist[v - 1] + c) {
                    this.dist[u - 1] = this.dist[v - 1] + c;
                    this.pred[u - 1] = j;
                    changeHappened = true;
                }
            }
        }

        return changeHappened;
    }

    private void processFlowFordFulkerson() {
        int cMin = this.findMinC();
        for (int i = super.t; i != this.s;) {
            int edgeInd = this.pred[i - 1];
            this.pushEdge(edgeInd, cMin);
            i = super.getSourceOfEdge(edgeInd);
        }
    }

    private int findMinC() {
        int edgeInd = this.pred[super.t - 1];
        int cMin = super.available(edgeInd);

        for (int i = super.getSourceOfEdge(edgeInd); i != this.s;) {
            edgeInd = this.pred[i - 1];
            if (super.available(edgeInd) < cMin) {
                cMin = super.available(edgeInd);
            }

            i = super.getSourceOfEdge(edgeInd);
        }

        return cMin;
    }

    private void pushEdge(int edgeInd, int flow) {
        Edge edge = super.flowEdges[edgeInd];
        edge.setFlow(edge.getFlow() + flow);

        Edge reverseEdge = super.flowEdges[edgeInd ^ 1];
        reverseEdge.setFlow(reverseEdge.getFlow() - flow);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.findMaxFlowMinCost();
        myGraph.countDiscontent();
        myGraph.outResult();
    }
}
