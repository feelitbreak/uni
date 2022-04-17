import java.io.*;
import java.util.*;

class Report {
    private final int[] a;
    private final int n;
    private int kRes;
    private int iRes;
    private final int[][][] sol;
    private int[] seq1;
    private int[] seq2;
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
        sol = new int[n][][];
        sol[0] = new int[1][1];
        sol[0][0][0] = 0;
        if(n != 1) {
            sol[n - 1] = new int[1][1];
            sol[n - 1][0][0] = 0;
        }
        kRes = 0;
        iRes = 0;
        for(int i = 1; i < n - 1; i++) {
            sol[i] = new int[3][];
            sol[i][0] = new int[1];
            sol[i][1] = new int[i];
            sol[i][2] = new int[n - i - 1];
        }
    }

    public void formSol() {
        for(int i = 1; i < n - 1; i++) {
            sol[i][0][0] = Math.min(lis(i), lds(i));
            if(sol[i][0][0] > kRes) {
                kRes = sol[i][0][0];
                iRes = i;
            }
        }
    }

    private int lis(int i) {
        int k = 0;
        sol[i][1][k] = a[0];
        for(int j = 1; j < i; j++) {
            if(sol[i][1][k] < a[j] && a[j] < a[i]) {
                k++;
                sol[i][1][k] = a[j];
            } else if(a[j] < a[i]) {
                int s = upperBound(a[j], k + 1, i, 1);
                if(s == 0 || sol[i][1][s - 1] != a[j]) {
                    sol[i][1][s] = a[j];
                }
            }
        }
        if(sol[i][1][0] >= a[i]) {
            return k;
        }
        return k + 1;
    }

    private int upperBound(int x, int r, int i, int j) {
        int l = 0;
        int m;
        while(l < r) {
            m = (l + r) / 2;
            if ((j == 1 && sol[i][j][m] > x) || (j == 2 && sol[i][j][m] < x) || (j == 3 && a[seq1[m]] > x ) || (j == 4 && a[seq2[m]] < x )) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }

    private int lds(int i) {
        int k = 0;
        sol[i][2][k] = a[i + 1];
        for(int j = i + 2; j < n; j++) {
            if(sol[i][2][k] > a[j] && a[j] < a[i]) {
                k++;
                sol[i][2][k] = a[j];
            } else if (a[j] < a[i]) {
                int s = upperBound(a[j], k + 1, i, 2);
                if(s == 0 || sol[i][2][s - 1] != a[j]) {
                    sol[i][2][s] = a[j];
                }
            }
        }
        if(sol[i][2][0] >= a[i]) {
            return k;
        }
        return k + 1;
    }

    public void formSequence() {
        int[] ind1 = new int[iRes];
        Arrays.fill(ind1, -1);
        int[] ind2 = new int[n];
        Arrays.fill(ind2, -1);
        seq1 = new int[kRes];
        seq2 = new int[kRes];
        int len1 = 0;
        int len2 = 0;

        int i = 0;
        for(; i < iRes; i++) {
            if(a[i] < a[iRes]) {
                seq1[0] = i;
                len1++;
                break;
            }
        }
        i += 1;
        for(; len1 < kRes; i++) {
            if(a[seq1[len1 - 1]] < a[i] && a[i] < a[iRes]) {
                seq1[len1] = i;
                ind1[i] = seq1[len1 - 1];
                len1++;
            } else if(a[i] < a[iRes]) {
                int s = upperBound(a[i], len1, 0, 3);
                if(s != len1) {
                    seq1[s] = i;
                    if (s != 0) {
                        ind1[i] = seq1[s - 1];
                    }
                }
            }
        }
        if(len1 != 0) {
            res1 = new int[len1];
            i = 0;
            for(int j = seq1[len1 - 1]; j >= 0; j = ind1[j]) {
                res1[i] = j;
                i++;
            }
        }

        i = iRes + 1;
        for(; i < n; i++) {
            if(a[i] < a[iRes]) {
                seq2[0] = i;
                len2++;
                break;
            }
        }
        i += 1;
        for(; len2 < kRes; i++) {
            if(a[seq2[len2 - 1]] > a[i] && a[i] < a[iRes]) {
                seq2[len2] = i;
                ind2[i] = seq2[len2 - 1];
                len2++;
            } else if(a[i] < a[iRes]) {
                int s = upperBound(a[i], len2, 0, 4);
                if(s != len2) {
                    seq2[s] = i;
                    if (s != 0) {
                        ind2[i] = seq2[s - 1];
                    }
                }
            }
        }
        if(len2 != 0) {
            res2 = new int[len2];
            i = 0;
            for(int j = seq2[len2 - 1]; j >= 0; j = ind2[j]) {
                res2[i] = j;
                i++;
            }
        }
    }

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
        myRep.formSequence();
        myRep.out();
    }
}
