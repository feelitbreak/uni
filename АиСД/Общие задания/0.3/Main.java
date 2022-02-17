import java.io.*;
import java.util.StringTokenizer;

class Vertex {
    private final int num;
    private Vertex left;
    private Vertex right;

    Vertex(int num) {
        this.num = num;
    }

    public Vertex getLeft() {
        return left;
    }

    public void setLeft(Vertex left) {
        this.left = left;
    }

    public Vertex getRight() {
        return right;
    }

    public void setRight(Vertex right) {
        this.right = right;
    }

    public int getNum() {
        return num;
    }

    public boolean hasLeft() {
        return left != null;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}

class Tree {
    private Vertex root;
    boolean answer = true;
    private Vertex saved;

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bst.in"));
        int n = Integer.parseInt(br.readLine());
        root = new Vertex(Integer.parseInt(br.readLine()));
        Vertex[] a = new Vertex[n];
        a[0] = root;
        for (int i = 1; i <= n - 1; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            Vertex v = new Vertex(Integer.parseInt(st.nextToken()));
            int id = Integer.parseInt(st.nextToken()) - 1;
            if(st.nextToken().equals("R")) {
                a[id].setRight(v);
            } else {
                a[id].setLeft(v);
            }
            a[i] = v;
        }
        br.close();
    }

    public Vertex getRoot() {
        return root;
    }

    public void lnr(Vertex v)  {
        if(v != null) {
            lnr(v.getLeft());
            if(saved != null) {
                if (v.getNum() < saved.getNum()) {
                    answer = false;
                    return;
                }
                if (v.getNum() == saved.getNum() && (saved.getRight() == null || v.getNum() != findMin(saved.getRight()).getNum())) {
                    answer = false;
                    return;
                }
            }
            saved = v;
            lnr(v.getRight());
        }
    }

    public void check() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("bst.out");
        if(answer) {
            pw.print("YES");
        } else {
            pw.print("NO");
        }
        pw.close();
    }

    private Vertex findMin(Vertex r) {
        while(r.hasLeft()) {
            r = r.getLeft();
        }
        return r;
    }
}

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Tree myTree = new Tree();
            myTree.init();
            myTree.lnr(myTree.getRoot());
            myTree.check();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}