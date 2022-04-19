import java.io.*;
import java.util.Arrays;

class HashTable {
    private final int m;
    private final int c;
    private final int n;
    private final int[] table;
    private final StreamTokenizer st;

    public HashTable() throws IOException {
        st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        m = (int) st.nval;
        st.nextToken();
        c = (int) st.nval;
        st.nextToken();
        n = (int) st.nval;
        table = new int[m];
        Arrays.fill(table, -1);
    }

    public void formTable() throws IOException {
        int x;
        int pos;
        for(int j = 0; j < n; j++) {
            st.nextToken();
            x = (int) st.nval;
            for(int i = 0; i < m; i++) {
                pos = h(x, i);
                if(table[pos] == x) {
                    break;
                } else if(table[pos] == -1) {
                    table[pos] = x;
                    break;
                }
            }
        }
    }

    public void out() throws IOException {
        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(table[0]);
        for(int i = 1; i < m; i++) {
            pw.print(' ');
            pw.print(table[i]);
        }
        pw.close();
    }

    private int h(int x, int i) {
        return ((x % m) + c * i) % m;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        HashTable ht = new HashTable();
        ht.formTable();
        ht.out();
    }
}
