import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Vertex {
    private final int num;

    Vertex(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}

class Huffman {
    private int answer = 0;

    public Huffman() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("huffman.in")));
        st.nextToken();
        int n = (int) st.nval;
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(Vertex::getNum));
        for(int i = 0; i < n; i++) {
            st.nextToken();
            Vertex v = new Vertex((int) st.nval);
            pq.add(v);
        }
        while(pq.size() > 1) {
            Vertex v1 = pq.poll();
            Vertex v2 = pq.poll();
            Vertex v = new Vertex(v1.getNum() + v2.getNum());
            answer += v.getNum();
            pq.add(v);
        }
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("huffman.out");
        fw.write(String.valueOf(answer));
        fw.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Huffman h = new Huffman();
        h.out();
    }
}
