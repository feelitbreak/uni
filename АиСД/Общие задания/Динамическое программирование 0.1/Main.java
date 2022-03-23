import java.io.*;

class Matrix {
    private final int s;
    private final int[] nm;
    private final long[][] sol;

    public Matrix() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        s = (int) st.nval;
        nm = new int[s + 1];
        st.nextToken();
        nm[0] = (int) st.nval;
        for(int i = 1; i < s + 1; i++) {
            st.nextToken();
            nm[i] = (int) st.nval;
            st.nextToken();
        }
        sol = new long[s][s];
    }

    public void formSol() {
        for(int i = 0; i < s; i++) {
            sol[i][i] = 0;
        }

        for(int j = 1; j < s; j++) {
            for(int i = j - 1; i >= 0; i--) {
                if (j == i + 1) {
                    sol[i][j] = (long) nm[i] * nm[i + 1] * nm[i + 2];
                }
                else {
                    long temp;
                    sol[i][j] = Long.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        temp = sol[i][k] + sol[k + 1][j] + (long) nm[i] * nm[k + 1] * nm[j + 1];
                        if(temp < sol[i][j]) {
                            sol[i][j] = temp;
                        }
                    }
                }
            }
        }
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(String.valueOf(sol[0][s - 1]));
        fw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Matrix matrix = new Matrix();
        matrix.formSol();
        matrix.out();
    }
}
