import java.io.*;
import java.util.*;

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
}

class Tree {
    private final Vertex root;

    public Tree(Vertex root) {
        this.root = root;
    }

    public void addElem(Vertex v) {
        Vertex temp = root;
        while(true) {
            if (v.getNum() == temp.getNum()) {
                break;
            }
            if (v.getNum() < temp.getNum()) {
                if (temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else {
                    temp.setLeft(v);
                    break;
                }
            } else {
                if (temp.getRight() != null) {
                    temp = temp.getRight();
                } else {
                    temp.setRight(v);
                    break;
                }
            }
        }
    }

    public void nlr() {

    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        Tree myTree = new Tree(new Vertex(sc.nextInt()));
        while(sc.hasNext()) {
            Vertex v = new Vertex(sc.nextInt());
            myTree.addElem(v);
        }
        sc.close();

    }
}