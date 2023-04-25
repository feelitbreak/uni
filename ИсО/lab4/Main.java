import java.io.*;

class Knapsack {
    private final int n;
    private final int W;
    private final int[] v;
    private final int[] w;
    private final int[][] p;
    private final int[][] f;

    public Knapsack() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));

        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        W = (int) st.nval;

        v = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            v[i] = (int) st.nval;
        }

        w = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            w[i] = (int) st.nval;
        }

        p = new int[n + 1][W + 1];
        f = new int[n + 1][W + 1];
    }

    public void solve() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < w[i - 1]; j++) {
                f[i][j] = f[i - 1][j];
                p[i][j] = 0;
            }

            for (int j = w[i - 1]; j <= W; j++) {
                f[i][j] = Math.max(f[i - 1][j], v[i - 1] + f[i][j - w[i - 1]]);

                if (f[i][j] == f[i - 1][j]) {
                    p[i][j] = 0;
                }
                else {
                    p[i][j] = 1;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Knapsack k = new Knapsack();
        k.solve();
    }
}
