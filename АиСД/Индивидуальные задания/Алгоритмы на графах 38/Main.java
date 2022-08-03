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

    public int getCapacity() {
        return capacity;
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
    protected final Map<Integer, List<Integer>> network;
    protected final int s;
    protected final int t;
    
    protected InitialGraph() throws IOException {
        this.flowEdges = new Edge[super.getNumOfFlowEdges()];

        final double defaultLoadFactor = 0.75;
        this.network = new HashMap<>((int)(super.getNumOfVertices() / defaultLoadFactor) + 1);
        for (int i = 1; i <= super.getNumOfVertices(); i++)
        {
            this.network.put(i, new ArrayList<>(super.getNumOfVertices()));
        }

        this.s = 1;
        this.t = super.getNumOfVertices();
        this.buildNetwork();
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
                i = this.addEdge(this.s, nPeople, 0, vertexNum, this.s, i);
                i = this.addEdge(vertexNum, 0, 0, this.s, vertexNum, i);
                vertexNum++;
            }
        }

        return i;
    }

    private int connectGroups(int i) {
        int vertexNumGroup1 = this.s + 1;

        for (int j = 0; j < super.group1.length; j++) {
            if (super.group1[j] != 0) {
                int vertexNumGroup2 = this.s + super.getNumOfVerticesInGroup1() + 1;

                for (int k = 0; k < super.group2.length; k++) {
                    if (super.group2[k] != 0) {
                        int weight;
                        if (k == SINGLE_ROOM_VERTEX) {
                            weight = j + MIN_AGE;
                        } else {
                            weight = 2 * Math.abs(j - k);
                        }

                        int cap = Math.min(super.group1[j], super.group2[k]);

                        i = this.addEdge(vertexNumGroup1, cap, weight, vertexNumGroup2, vertexNumGroup1, i);
                        i = this.addEdge(vertexNumGroup2, 0, - weight, vertexNumGroup1, vertexNumGroup2, i);
                        vertexNumGroup2++;
                    }
                }

                vertexNumGroup1++;
            }
        }

        return i;
    }

    private int connectGroup2ToT(int i) {
        int vertexNum = this.s + super.getNumOfVerticesInGroup1() + 1;
        for (int nPeople : super.group2) {
            if (nPeople != 0) {
                i = this.addEdge(vertexNum, nPeople, 0, this.t, vertexNum, i);
                i = this.addEdge(this.t, 0, 0, vertexNum, this.t, i);
                vertexNum++;
            }
        }

        return i;
    }

    private int addEdge(int source, int cap, int weight, int target, int networkInd, int i) {
        this.flowEdges[i] = new Edge(source, cap, 0, weight, target);
        this.network.get(networkInd).add(i);
        return i + 1;
    }
}

class Graph extends InitialGraph {
    private final int result = 0;
    private int[] dist;
    private int[] pred;

    public Graph() throws IOException {
        this.dist = new int[super.getNumOfVertices()];
        this.pred = new int[super.getNumOfVertices()];
    }

    public void findMaxFlowMinCost() {
        while (true)
        {
            this.findShortestPathBellmanFord();
            
        }
    }

    public void outResult() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.printf("%.1f", this.result / 2.0);
        pw.close();
    }

    private void findShortestPathBellmanFord() {
        dist[super.s - 1] = 0;
        Arrays.fill(this.dist, super.s, this.dist.length, Integer.MAX_VALUE);
        Arrays.fill(this.pred, 0);

        for (int i = 0; i < super.getNumOfVertices() - 1; i++)
        {
            for (Edge edge : super.flowEdges)
            {
                int u = edge.getSource();
                int v = edge.getTarget();
                int c = edge.getWeight();
                if (this.dist[u - 1] > this.dist[v - 1] + c)
                {
                    this.dist[u - 1] = this.dist[v - 1] + c;
                    this.pred[u - 1] = v;
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.findMaxFlowMinCost();
        myGraph.outResult();
    }
}
