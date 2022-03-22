import java.io.*;

class Ones {
    private final int k;
    private final int n;
    private final int[][] sol;

    public Ones() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        k = (int) st.nval;
        sol = new int[n + 1][];
        for(int i = 0; i <= n; i++) {
            sol[i] = new int[i + 1];
        }
    }

    public void formSol() {
        for(int i = 0; i <= n; i++) {
            sol[i][0] = 1;
            sol[i][i] = 1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i - 1; j++) {
                sol[i][j] = sol[i - 1][j - 1] + sol[i - 1][j];
                while (sol[i][j] >= 1000000007) {
                    sol[i][j] -= 1000000007;
                }
            }
        }
    }

    public void out() {
        System.out.print(sol[n][k]);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Ones ones = new Ones();
        ones.formSol();
        ones.out();
    }
}