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
    private final int[] res1;
    private final int[] res2;

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
        res1 = new int[n];
        res2 = new int[n];
    }

    public void formSol() {
        lis(a, ind1, seq1, k1);
        lds();

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
                len1++;
                k[i] = k[i - 1] + 1;
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
        rev(seq2);
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

    /*public void formSequence(int iRoot) {
        Arrays.fill(ind1, -1);
        Arrays.fill(ind2, -1);
        int len1 = 0;
        int len2 = 0;

        int i = 0;
        for(; i < iRoot; i++) {
            if(a[i] < a[iRoot]) {
                seq1[0] = i;
                len1++;
                break;
            }
        }
        i += 1;
        for(; i < iRoot; i++) {
            if(a[seq1[len1 - 1]] < a[i] && a[i] < a[iRoot]) {
                seq1[len1] = i;
                ind1[i] = seq1[len1 - 1];
                len1++;
            } else if(a[i] < a[iRoot]) {
                int s = upperBound(a[i], len1,  1);
                if(s != len1 && (s == 0 || a[seq1[s - 1]] != a[i])) {
                    seq1[s] = i;
                    if (s != 0) {
                        ind1[i] = seq1[s - 1];
                    }
                }
            }
        }


        i = iRoot + 1;
        for(; i < n; i++) {
            if(a[i] < a[iRoot]) {
                seq2[0] = i;
                len2++;
                break;
            }
        }
        i += 1;
        for(; i < n; i++) {
            if(a[seq2[len2 - 1]] > a[i] && a[i] < a[iRoot]) {
                seq2[len2] = i;
                ind2[i] = seq2[len2 - 1];
                len2++;
            } else if(a[i] < a[iRoot]) {
                int s = upperBound(a[i], len2, 2);
                if(s != len2 && (s == 0 || a[seq2[s - 1]] != a[i])) {
                    seq2[s] = i;
                    if (s != 0) {
                        ind2[i] = seq2[s - 1];
                    }
                }
            }
        }

        if(kRes < Math.min(len1, len2)) {
            iRes = iRoot;
            kRes = Math.min(len1, len2);

            i = 0;
            int j = seq1[len1 - 1];
            for (int k = 0; k < len1 - kRes; k++) {
                j = ind1[j];
            }
            for (; j >= 0; j = ind1[j]) {
                res1[i] = j;
                i++;
            }

            i = 0;
            j = seq2[len2 - 1];
            for (int k = 0; k < len2 - kRes; k++) {
                j = ind2[j];
            }
            for (; j >= 0; j = ind2[j]) {
                res2[i] = j;
                i++;
            }
        }
    }*/

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("report.out");
        pw.println(kRes);
        for(int i = kRes - 1; i >= 0; i--) {
            pw.print(res1[i] + 1);
            pw.print(' ');
        }
        pw.print(iRes + 1);
        for(int i = kRes - 1; i >= 0; i--) {
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
