import java.io.*;

class Matrix {
    private final int s;
    private final int[] nm;
    private final int[][] sol;

    public Matrix() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        s = (int) st.nval;
        nm = new int[s + 1];
        st.nextToken();
        nm[0] = (int) st.nval;
        for(int i = 0; i < s; i++) {
            st.nextToken();
            nm[i] = (int) st.nval;
            st.nextToken();
        }
        sol = new int[s + 1][s + 1];
    }

    public void formSol() {

    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Matrix matrix = new Matrix();
        matrix.formSol();
    }
}
