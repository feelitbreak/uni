import java.io.*;

class Heap {
    private final int[] mass;
    private final int n;
    private boolean answer = true;

    public Heap() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        mass = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            mass[i] = (int) st.nval;
        }
    }

    public void check() {
        for(int i = 1; i < n; i++) {
            if(mass[i] > mass[(i - 1) / 2]) {
                answer = false;
                break;
            }
        }
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        if(answer) {
            fw.write("Yes");
        } else {
            fw.write("No");
        }
        fw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Heap heap = new Heap();
        heap.check();
        heap.out();
    }
}
