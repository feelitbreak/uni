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

    public static long power(long base) {
        long res = 1;
        long temp = base;
        int exponent = MOD - 2;
        while (exponent > 0) {
            if (exponent % 2 != 0) {
                res = (res * temp) % MOD;
            }
            temp = (temp * temp) % MOD;
            exponent /= 2;

        }
        return res;
    }

    public void solve() {
        long res;
        res = fact(n);
        res *= power(fact(k)) % MOD;
        res %= MOD;
        res *= power(fact(n - k));
        res %= MOD;

        System.out.print(res);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Ones ones = new Ones();
        ones.solve();
    }
}/*
import java.util.Scanner;

class Ones {
    public static int mod = 1000000007;

    public static long power(long base) {
        long res = 1;
        long temp = base;
        int exponent = mod - 2;
        while (exponent > 0) {
            if (exponent % 2 != 0) {
                res = (res * temp) % mod;
            }
            temp = (temp * temp) % mod;
            base *= base;
            exponent /= 2;

        }
        return res;
    }
}
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        long up = k + 1;
        for (int i = k + 2; i <= n; i++) {
            up = ((up % Ones.mod) * (i % Ones.mod)) % Ones.mod;
        }

        //long down = fact(n - k);
        //down = power(down, mod - 2) % mod;

        for (int i = 1; i <= n - k; i++) {
            up = (up * Ones.power(i)) % Ones.mod;
        }

        System.out.println(up);

        //System.out.println((up * down) % mod);
    }
}
*/