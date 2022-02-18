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
    private Vertex[] mass;
    private int k = 0;

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bst.in"));
        int n = Integer.parseInt(br.readLine());
        root = new Vertex(Integer.parseInt(br.readLine()));
        mass = new Vertex[n];
        mass[0] = root;
        for (int i = 1; i <= n - 1; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            Vertex v = new Vertex(Integer.parseInt(st.nextToken()));
            int id = Integer.parseInt(st.nextToken()) - 1;
            if(str.endsWith("R")) {
                mass[id].setRight(v);
            } else {
                mass[id].setLeft(v);
            }
            mass[i] = v;
        }
        br.close();
    }

    public Vertex getRoot() {
        return root;
    }

    public void lnr(Vertex v)  {
        if(v != null) {
            lnr(v.getLeft());
            mass[k] = v;
            k++;
            lnr(v.getRight());
        }
    }

    public void checkMass() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("bst.out");
        String s1 = "NO";

        for (int i = 1; i < mass.length; i++) {
                if (mass[i].getNum() < mass[i - 1].getNum()) {
                    pw.print(s1);
                    pw.close();
                    return;
                }
                if (mass[i].getNum() == mass[i - 1].getNum() && mass[i].hasLeft()) {
                    pw.print(s1);
                    pw.close();
                    return;
                }
        }
        pw.print("YES");
        pw.close();
    }
}

public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 32 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Tree myTree = new Tree();
            myTree.init();
            myTree.lnr(myTree.getRoot());
            myTree.checkMass();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}