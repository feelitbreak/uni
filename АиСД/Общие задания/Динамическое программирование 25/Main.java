import java.io.*;

class Transform {
    private final int x;
    private final int y;
    private final int z;
    private final String a;
    private final String b;
    private final int[][] sol;

    public Transform() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("in.txt")));
        st.nextToken();
        x = (int) st.nval;
        st.nextToken();
        y = (int) st.nval;
        st.nextToken();
        z = (int) st.nval;
        st.nextToken();
        a = st.sval;
        st.nextToken();
        b = st.sval;
        sol = new int[a.length() + 1][b.length() + 1];
    }

    public void formSol() {
        for(int i = 0; i <= a.length(); i++) {
            sol[i][0] = i * x;
        }
        for(int i = 0; i <= b.length(); i++) {
            sol[0][i] = i * y;
        }
        for(int i = 1; i <= a.length(); i++) {
            for(int j = 1; j <= b.length(); j++) {
                sol[i][j] = sol[i - 1][j - 1];
                if(a.charAt(i) != b.charAt(j)) {
                    sol[i][j] += z;
                }
                int c;
                c = sol[i - 1][j] + x;
                if(c < sol[i][j]) {
                    sol[i][j] = c;
                }
                c = sol[i][j - 1] + y;
                if(c < sol[i][j]) {
                    sol[i][j] = c;
                }
            }
        }
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("out.txt");
        fw.write(String.valueOf(sol[a.length()][b.length()]));
        fw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Transform tf = new Transform();
        tf.formSol();
        tf.out();
    }
}
