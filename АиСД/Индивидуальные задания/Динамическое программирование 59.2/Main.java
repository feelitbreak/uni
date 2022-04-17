import java.io.*;

class Report {
    private final int[] a;
    private final int n;
    private int kRes;
    private final int[][][] sol;

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
            if ((sol[i][j][m] > x && j == 1) || (sol[i][j][m] < x && j == 2)) {
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

    public void out() throws IOException {
        FileWriter fw = new FileWriter("report.out");
        fw.write(String.valueOf(kRes));
        fw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Report myRep = new Report();
        myRep.formSol();
        myRep.out();
    }
}
