import java.io.*;

class Ones {
    private final int k;
    private final int n;
    private final static int MOD = 1000000007;

    public Ones() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        k = (int) st.nval;
    }

    private int fact(int num) {
        int res = 1;
        for (int i = 2; i <= num; i++) {
            res *= i % MOD;
            res %= MOD;
        }
        return res;
    }

    private int pow(int num) {
        num %= MOD;
        int res = num;
        for (int i = 1; i < MOD - 1; i++) {
            res *= num;
            res %= MOD;
        }
        return res;
    }

    public void solve() {
        int res;
        res = fact(n);
        res *= pow(fact(k));
        res %= MOD;
        res *= pow(fact(n - k));
        res %= MOD;

        System.out.print(res);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Ones ones = new Ones();
        ones.solve();
    }
}