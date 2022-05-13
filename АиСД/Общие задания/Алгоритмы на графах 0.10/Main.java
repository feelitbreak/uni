import java.io.*;
import java.util.*;

class Weight {
    int u;
    int w;

    public Weight(int u, int w) {
        this.u = u;
        this.w = w;
    }

    public int getU() {
        return u;
    }

    public int getW() {
        return w;
    }
}

class Pair {
    int u;
    long dist;

    public Pair(int u, long dist) {
        this.u = u;
        this.dist = dist;
    }

    public int getU() {
        return u;
    }

    public long getDist() {
        return dist;
    }
}

class Walk {
    private final int n;
    private final List<List<Weight>> adj;
    private final long[] dist;
    private final static int START = 1;
    private final boolean[] processed;


    public Walk() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        int m;
        m = (int) st.nval;
        adj = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        dist = new long[n];
        for(int i = 1; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        processed = new boolean[n];

        int u;
        int v;
        int w;
        for(int i = 0; i < m; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            st.nextToken();
            w = (int) st.nval;
            adj.get(u - 1).add(new Weight(v, w));
            adj.get(v - 1).add(new Weight(u, w));
        }
    }

    public void dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(START, dist[START - 1]));
        Pair p;
        int u;
        long d;
        while(!pq.isEmpty()) {
            p = pq.poll();
            u = p.getU();
            d = p.getDist();
            if(!processed[u - 1]) {
                processed[u - 1] = true;
                dist[u - 1] = d;
                for(Weight w : adj.get(u - 1)) {
                    pq.add(new Pair(w.getU(), d + w.getW()));
                }
            }
        }
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(dist[n - 1]);
        pw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Walk myWalk = new Walk();
        myWalk.dijkstra();
        myWalk.out();
    }
}
