import java.io.*;
import java.util.Arrays;

class Roads {
    private int n;
    private final int q;
    private final int[] forest;
    private final StreamTokenizer st;
    public Roads() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        q = (int) st.nval;
        forest = new int[n];
        Arrays.fill(forest, -1);
    }

    public void formOutput() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        int u;
        int uLeader;
        int v;
        int vLeader;
        for(int i = 0; i < q; i++) {
            st.nextToken();
            u = (int) st.nval;
            st.nextToken();
            v = (int) st.nval;
            
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
                n--;
            }
            pw.println(n);
        }
        pw.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Roads roads = new Roads();
        roads.formOutput();
    }
}
