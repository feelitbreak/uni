import java.io.*;
import java.util.*;

class Report {
    private final int[] a;
    private final int n;
    private int kRes = 0;
    private int iRes = 0;
    private final int[] seq1;
    private final int[] seq2;
    private final int[] ind1;
    private final int[] ind2;
    private final int[] k1;
    private final int[] k2;
    private int[] res1;
    private int[] res2;

    public Report() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("report.in")));
        st.nextToken();
        n = (int) st.nval;
        a = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            a[i] = (int) st.nval;
        }
        seq1 = new int[n];
        seq2 = new int[n];
        ind1 = new int[n];
        ind2 = new int[n];
        k1 = new int[n];
        k2 = new int[n];
    }

    public void formSol() {
        lis(a, ind1, seq1, k1);
        lds();
        for(int i = 0; i < n; i++) {
            if(Math.min(k1[i], k2[i]) > kRes) {
                kRes = Math.min(k1[i], k2[i]);
                iRes = i;
            }
        }
        kRes--;
        if(kRes != 0) {
            res1 = new int[kRes];
            for (int i = 0, j = ind1[iRes]; i < kRes; j = ind1[j], i++) {
                res1[i] = j;
            }

            res2 = new int[kRes];
            for (int i = 0, j = ind2[iRes]; i < kRes; j = ind2[j], i++) {
                res2[i] = j;
            }
        }
    }

    private void lis(int[] a, int[] ind, int[] seq, int[] k) {
        Arrays.fill(ind, -1);
        int len1 = 0;
        seq[0] = 0;
        k[0] = 1;
        len1++;
        for(int i = 1; i < n; i++) {
            if(a[seq[len1 - 1]] < a[i]) {
                seq[len1] = i;
                ind[i] = seq[len1 - 1];
                k[i] = k[seq[len1 - 1]] + 1;
                len1++;
            } else {
                int s = upperBound(a, seq, a[i], len1);
                if(s == 0 || a[seq[s - 1]] != a[i]) {
                    k[i] = k[seq[s]];
                    seq[s] = i;
                    if (s != 0) {
                        ind[i] = seq[s - 1];
                    }
                } else {
                    k[i] = k[seq[s - 1]];
                    ind[i] = ind[seq[s - 1]];
                }
            }
        }
    }

    private void lds() {
        int[] aRev = new int[n];
        for(int i = 0; i < n; i++) {
            aRev[i] = a[n - i - 1];
        }
        lis(aRev, ind2, seq2, k2);
        rev(k2);
        rev(ind2);
        for(int i = 0; i < n; i++) {
            if(ind2[i] != -1) {
                ind2[i] = n - ind2[i] - 1;
            }
        }
    }

    private void rev(int[] x) {
        int temp;
        for(int i = 0; i < x.length / 2; i++) {
            temp = x[i];
            x[i] = x[n - i - 1];
            x[n - i - 1] = temp;
        }
    }
    private int upperBound(int[] a, int[] seq, int x, int r) {
        int l = 0;
        int m;
        while(l < r) {
            m = (l + r) / 2;
            if (a[seq[m]] > x) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("report.out");
        pw.println(kRes);
        for(int i = kRes - 1; i >= 0; i--) {
            pw.print(res1[i] + 1);
            pw.print(' ');
        }
        pw.print(iRes + 1);
        for(int i = 0; i < kRes; i++) {
            pw.print(' ');
            pw.print(res2[i] + 1);
        }
        pw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Report myRep = new Report();
        myRep.formSol();
        myRep.out();
    }
}
