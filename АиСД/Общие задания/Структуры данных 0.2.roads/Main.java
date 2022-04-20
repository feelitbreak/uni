import java.io.*;
import java.util.Arrays;

class Roads {
    private int n;
    private final int m;
    private final int q;
    private final int[] forest;
    private final int[][] roads;
    private final boolean[] inQuery;
    private final int[] queries;
    private int answer;

    public Roads() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        m = (int) st.nval;
        st.nextToken();
        q = (int) st.nval;
        roads = new int[m][2];
        for(int i = 0; i < m; i++) {
            st.nextToken();
            roads[i][0] = (int) st.nval;
            st.nextToken();
            roads[i][1] = (int) st.nval;
        }
        inQuery = new boolean[m];
        Arrays.fill(inQuery, false);
        queries = new int[q];
        for(int i = 0; i < q; i++) {
            st.nextToken();
            queries[i] = (int) st.nval;
            inQuery[queries[i] - 1] = true;
        }
        forest = new int[n];
        Arrays.fill(forest, -1);
    }

    public void formForest() {
        int u;
        int v;
        for(int i = 0; i < m; i++) {
            if(!inQuery[i]) {
                u = roads[i][0];
                v = roads[i][1];
                buildRoad(u, v, false);
            }
        }
        for(int i = q - 1; i >= 0; i--) {
            u = roads[queries[i] - 1][0];
            v = roads[queries[i] - 1][1];
            buildRoad(u, v, true);
            if(n == 1) {
                answer = i;
                break;
            }
        }
    }

    private void buildRoad(int u, int v, boolean changeN) {
        int uLeader;
        int vLeader;
        uLeader = u;
        while(forest[uLeader - 1] > 0) {
            uLeader = forest[uLeader - 1];
        }
        vLeader = v;
        while(forest[vLeader - 1] > 0) {
            vLeader = forest[vLeader - 1];
        }

        if (uLeader != vLeader) {
            if (forest[uLeader - 1] < forest[vLeader - 1]) {
                forest[vLeader - 1] = uLeader;
                forest[uLeader - 1]--;
            } else {
                forest[uLeader - 1] = vLeader;
                forest[vLeader - 1]--;
            }
            if(changeN) {
                n--;
            }
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        int i = 0;
        for(; i < answer; i++) {
            pw.print('1');
        }
        for(; i < q; i++) {
            pw.print('0');
        }
        pw.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Roads roads = new Roads();
        roads.formForest();
        roads.out();
    }
}
