import java.io.*;
import java.util.Arrays;

class Sequence {
    private final int[] a;
    private final int[] sol;
    private int k;
    private final static int MAX_VALUE = 1000000001;

    public Sequence() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        int n = (int) st.nval;
        a = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            a[i] = (int) st.nval;
        }
        sol = new int[n];
        Arrays.fill(sol, MAX_VALUE);
    }

    public void formSol() {
        k = 0;
        sol[k] = a[0];
        for(int num : a) {
            if(sol[k] < num) {
                k++;
                sol[k] = num;
            } else {
                int i = upperBound(num);
                if(i != k + 1) {
                    sol[i] = num;
                }
            }
        }
    }

    private int upperBound(int x) {
        int l = 0;
        int r = k + 1;
        int m;
        while(l < r) {
            m = (l + r) / 2;
            if (sol[m] > x) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(String.valueOf(k + 1));
        fw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Sequence seq = new Sequence();
        seq.formSol();
        seq.out();
    }
}
