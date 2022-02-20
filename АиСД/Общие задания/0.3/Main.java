import java.io.*;

class Vertex {
    public int num;
    public int left = -1;
    public int right = -1;

    Vertex(int num) {
        this.num = num;
    }
}

class Tree {
    public Vertex[] mass;
    private int saved = -1;
    public boolean answer = true;

    public Tree() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("bst.in")));
        st.ordinaryChar('R');
        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        Vertex root = new Vertex(((int)st.nval));
        mass = new Vertex[n];
        mass[0] = root;

        for (int i = 1; i <= n - 1; i++) {
            st.nextToken();
            Vertex v = new Vertex((int)st.nval);
            st.nextToken();
            int id = (int) st.nval - 1;
            st.nextToken();
            if(st.ttype == 'R') {
                if(v.num < mass[id].num) {
                    answer = false;
                    return;
                }
                mass[id].right = i;
            } else {
                if(v.num >= mass[id].num) {
                    answer = false;
                    return;
                }
                mass[id].left = i;
            }
            mass[i] = v;
        }
    }

    public void lnr(int v) {
        if (answer) {
            if(saved == -1) {
                saved = 0;
                return;
            }
            if (mass[v].left != -1) {
                lnr(mass[v].left);
            }
            if (saved != -1) {
                if (mass[v].num < mass[saved].num) {
                    answer = false;
                    return;
                }
                if (mass[v].num == mass[saved].num && mass[v].left != -1) {
                    answer = false;
                    return;
                }
            }
            saved = v;
            if (mass[v].right != -1) {
                lnr(mass[v].right);
            }
        }
    }
}

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 32 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Tree myTree = new Tree();
            myTree.lnr(0);
            PrintWriter pw = new PrintWriter("bst.out");
            if(!myTree.answer) {
                pw.print("NO");
                pw.close();
                return;
            }
            pw.print("YES");
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}