import java.io.*;

class Report {
    private final int[] a;
    private final int n;
    private int k;
    private final int[][] sol;
    private final static int MAX_VALUE = 100001;

    public Report() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("report.in")));
        st.nextToken();
        n = (int) st.nval;
        a = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            a[i] = (int) st.nval;
        }
        sol = new int[((n - 1) / 2) + 1][2];
    }

    public void formSol() {
        sol[0][0] = findMax(MAX_VALUE);
        sol[0][1] = sol[0][0];
        for(k = 1; k <= (n - 1) / 2; k++) {
            int x = findMax(a[sol[k - 1][1]]);
            if(x == -1) {
                break;
            }
            sol[k][0] = x;

            x = findMax(a[x]);
            if(x == -1) {
                break;
            }
            sol[k][1] = x;
        }
        k--;
    }

    private int findMax(int lim) {
        int max = -1;
        for(int i = 0; i < n; i++) {
            if(a[i] < lim && (max == -1 || a[i] > a[max])) {
                max = i;
            }
        }
        return max;
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("report.out");
        fw.write(String.valueOf(k));
        fw.write('\n');
        fw.write(String.valueOf(a[sol[k][0]]));
        for(int i = k - 1; i >= 0; i--) {
            fw.write(' ');
            fw.write(String.valueOf(a[sol[i][0]]));
        }
        for(int i = 1; i <= k; i++) {
            fw.write(' ');
            fw.write(String.valueOf(a[sol[i][1]]));
        }
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
