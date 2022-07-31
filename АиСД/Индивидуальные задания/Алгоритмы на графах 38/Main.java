import java.io.*;

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
    private int[] group1;
    private int[] group2;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 60;
    private static final int SINGLE_ROOM_VERTEX = MAX_AGE - MIN_AGE + 1;
    private int nVertices1 = 0;
    private int nVertices2 = 0;
    private int n = 0;
    private int m = 0;

    protected AgeGroups() throws IOException {
        this.group1 = new int[MAX_AGE - MIN_AGE + 1];
        this.group2 = new int[MAX_AGE - MIN_AGE + 2];
        inputGroups();

        if (this.m > this.n)
        {
            switchGroups();
            switchVertices();
        }

        if (this.n != this.m) {
            this.group2[SINGLE_ROOM_VERTEX] = Math.abs(this.n - this.m);
            this.nVertices2++;
        }
    }

    protected int getPeopleInGroup1(int age) {
        return this.group1[age - MIN_AGE];
    }

    protected int getPeopleInGroup2(int age) {
        return this.group2[age - MIN_AGE];
    }

    protected int getSingleRoomPeople() {
        return this.group2[SINGLE_ROOM_VERTEX];
    }
    
    protected int getNumberOfFlowEdges() {
        return (this.nVertices1 * this.nVertices2 + this.nVertices1 + this.nVertices2) * 2;
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

class Graph extends AgeGroups {
    private Edge[] flowEdges;
    private int dis = 0;
    
    public Graph() throws IOException {
        this.flowEdges = new Edge[this.getNumberOfFlowEdges()];

    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.printf("%.1f", this.dis / 2.0);
        pw.close();
    }

    private void buildNetwork()
    {

    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Graph myGraph = new Graph();
        myGraph.out();
    }
}
