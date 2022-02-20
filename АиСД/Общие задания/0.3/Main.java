import java.io.*;
import java.util.*;

//Пожалуйста, Джава, потрать меньше памяти :( Я знаю я написали прошлую попытку на C++, и я извиняюсь, ты заслуживаешь большего.
//Пожалуйста, дай мне еще один шанс.
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

    public void myLnr() {
        Stack<Vertex> st = new Stack<>();
        Vertex tmp = mass[0];
        Vertex save = null;
        while(tmp != null || !st.empty()) {
            while(tmp != null) {
                st.push(tmp);
                if(tmp.left != -1) {
                    tmp = mass[tmp.left];
                } else {
                    tmp = null;
                }
            }
            tmp = st.pop();

            if (save != null) {
                if (tmp.num < save.num) {
                    answer = false;
                    return;
                }
                if (tmp.num == save.num && tmp.left != -1) {
                    answer = false;
                    return;
                }
            }
            save = tmp;

            if(tmp.right != -1) {
                tmp = mass[tmp.right];
            } else {
                tmp = null;
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Tree myTree = new Tree();
        myTree.myLnr();
        PrintWriter pw = new PrintWriter("bst.out");
        if(!myTree.answer) {
            pw.print("NO");
            pw.close();
            return;
        }
        pw.print("YES");
        pw.close();
    }
}