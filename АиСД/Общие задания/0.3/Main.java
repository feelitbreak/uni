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

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex v) {
            return v.getNum() == num && v.getRight() == right && v.getLeft() == left;
        } else {
            return false;
        }
    }
}

class Tree {
    private Vertex root;
    private Vertex[] a;
    boolean answer = true;
    private int k = 0;

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bst.in"));
        int n = Integer.parseInt(br.readLine());
        root = new Vertex(Integer.parseInt(br.readLine()));
        a = new Vertex[n];
        a[0] = root;
        for (int i = 1; br.ready(); i++) {
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

    public void lnr(Vertex v) {
        if(v != null) {
            lnr(v.getLeft());
            a[k] = v;
            k++;
            lnr(v.getRight());
        }
    }

    public void checkA() throws IOException {
        for (int i = 1; i < a.length; i++) {
            if (a[i].getNum() < a[i - 1].getNum()) {
                answer = false;
                break;
            }
            if (a[i].getNum() == a[i-1].getNum() && !a[i-1].getRight().equals(a[i])) {
                answer = false;
                break;
            }
        }
        PrintWriter pw = new PrintWriter("bst.out");
        if(answer) {
            pw.print("YES");
        } else {
            pw.print("NO");
        }
        pw.close();
    }
}

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Tree myTree = new Tree();
            myTree.init();
            myTree.lnr(myTree.getRoot());
            myTree.checkA();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}