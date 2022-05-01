import java.io.*;
import java.util.*;

class Schedule {
    private final int n;
    private final long[] p;
    private final long[] d;
    private final int[] outArcsN;
    private final List<List<Integer>> inArcs;
    private final int[] y;
    private long maxFine = 0;
    private int iMaxFine;

    public Schedule() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        p = new long[n];
        d = new long[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            p[i] = (long) st.nval;
            st.nextToken();
            d[i] = (long) st.nval;
        }

        int m;
        st.nextToken();
        m = (int) st.nval;

        outArcsN = new int[n];
        inArcs = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            inArcs.add(new ArrayList<>());
        }
        int u, v;
        for(int i = 0; i < m; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            outArcsN[u - 1]++;
            inArcs.get(v - 1).add(u);
        }

        y = new int[n];
    }

    public void formSequence() {
        Queue<Integer> pq = new PriorityQueue<>((x, y) -> {
            if(d[y - 1] - d[x - 1] > 0) {
                return 1;
            } else if(d[y - 1] - d[x - 1] < 0) {
                return -1;
            } else {
                return 0;
            }
        }) ;
        for(int i = 0; i < n; i++) {
            if(outArcsN[i] == 0) {
                pq.add(i + 1);
            }
        }
        int k = n - 1;
        int u;
        while(!pq.isEmpty()) {
            u = pq.poll();
            y[k] = u;
            k--;

            for(int v : inArcs.get(u - 1)) {
                outArcsN[v - 1]--;
                if(outArcsN[v - 1] == 0) {
                    pq.add(v);
                }
            }
        }
    }

    public void calculateMaxFine() {
        long time = 0;
        iMaxFine = y[0];
        for(int i = 0; i < n; i++) {
            time += p[y[i] - 1];
            if(time - d[y[i] - 1] > maxFine) {
                maxFine = time - d[y[i] - 1];
                iMaxFine = y[i];
            }
        }
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(iMaxFine);
        pw.print(' ');
        pw.print(maxFine);
        for(int i = 0; i < n; i++) {
            pw.print('\n');
            pw.print(y[i]);
        }
        pw.close();
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        Schedule mySchedule = new Schedule();
        mySchedule.formSequence();
        mySchedule.calculateMaxFine();
        mySchedule.out();
    }
}
