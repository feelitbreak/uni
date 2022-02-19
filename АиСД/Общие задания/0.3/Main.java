import java.io.*;
import java.util.StringTokenizer;

class Vertex {
    public int num;
    public Vertex left;
    public Vertex right;

    Vertex(int num) {
        this.num = num;
    }
}

class Tree {
    public Vertex[] mass;
    private Vertex saved;
    private boolean answer = true;

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bst.in"));
        int n = Integer.parseInt(br.readLine());
        Vertex root = new Vertex(Integer.parseInt(br.readLine()));
        mass = new Vertex[n];
        mass[0] = root;
        for (int i = 1; i <= n - 1; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            Vertex v = new Vertex(Integer.parseInt(st.nextToken()));
            int id = Integer.parseInt(st.nextToken()) - 1;
            if(str.endsWith("R")) {
                if(v.num < mass[id].num) {
                    answer = false;
                    mass[0] = null;
                    return;
                }
                mass[id].right = v;
            } else {
                if(v.num >= mass[id].num) {
                    answer = false;
                    mass[0] = null;
                    return;
                }
                mass[id].left = v;
            }
            mass[i] = v;
        }
        br.close();
    }

    public void lnr(Vertex v)  {
        if(v != null) {
            lnr(v.left);
            if(saved != null) {
                if (v.num < saved.num) {
                    answer = false;
                    return;
                }
                if (v.num == saved.num && v.left != null) {
                    answer = false;
                    return;
                }
            }
            saved = v;
            lnr(v.right);
        }
    }

    public void checkMass() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("bst.out");
        String s1 = "NO";
        if(!answer) {
            pw.print(s1);
            pw.close();
            return;
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
            myTree.lnr(myTree.mass[0]);
            myTree.checkMass();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}