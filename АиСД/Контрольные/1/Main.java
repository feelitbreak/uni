import java.io.*;
import java.util.*;

class Solution {
    private final int[] a;
    private final int n;
    private int k;
    private final int[] sol;

    public Solution() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        a = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            a[i] = Math.abs((int) st.nval);
        }
        Arrays.sort(a);
        sol = new int[n];
    }

    public void formSol() {
        boolean zero = false;
        if (n == 0) {
            k = -1;
            return;
        }
        int n1 = 0;
        if(a[0] == 0) {
            zero = true;
            n1++;
            while(n1 < n && a[n1] == 0) {
                n1++;
            }
        }
        if(n1 < n) {
            sol[n1] = 0;
            for(int i = n1 + 1; i < n; i++) {
                int max = -1;
                for(int j = n1; j < i; j++) {
                    if (a[i] % a[j] == 0 && sol[j] > max) {
                        max = sol[j];
                    }
                }
                sol[i] = max + 1;
                if(sol[i] > k) {
                    k = sol[i];
                }
            }
        } else {
            k = -1;
        }
        if(zero) {
            k++;
        }
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(String.valueOf(k + 1));
        fw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution mySol = new Solution();
        mySol.formSol();
        mySol.out();
    }
}
