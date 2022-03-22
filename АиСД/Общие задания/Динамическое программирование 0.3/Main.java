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

    private long fact(long num) {
        long res = 1;
        for (int i = 2; i <= num; i++) {
            res *= i % MOD;
            res %= MOD;
        }
        return res;
    }

    private long pow(long num) {
        num %= MOD;
        long pow = MOD - 2;
        long res = 1;
        while(pow > 0) {
            if (pow % 2 == 1) {
                res *= num;
                res %= MOD;
            }
            num *= num;
            num %= MOD;
            pow /= 2;
        }
        return res;
    }

    public void solve() {
        long res;
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