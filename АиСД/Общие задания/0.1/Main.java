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

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    public boolean hasLeft() {
        return left != null;
    }
    public boolean hasRight() {
        return right != null;
    }
}

class Tree {
    private Vertex root;

    public void setRoot(Vertex root) {
        this.root = root;
    }

    public void addElem(Vertex v) {
        Vertex temp = root;
        while(true) {
            if (v.getNum() == temp.getNum()) {
                break;
            }
            if (v.getNum() < temp.getNum()) {
                if (temp.hasLeft()) {
                    temp = temp.getLeft();
                } else {
                    temp.setLeft(v);
                    break;
                }
            } else {
                if (temp.hasRight()) {
                    temp = temp.getRight();
                } else {
                    temp.setRight(v);
                    break;
                }
            }
        }
    }

    public void nlr() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        if(root != null) {
            fw.write(root.toString());
            nlrRecursion(root.getLeft(), fw);
            nlrRecursion(root.getRight(), fw);
        }
        fw.close();
    }
    private void nlrRecursion(Vertex v, FileWriter fw) throws IOException {
        if (v != null) {
            fw.write("\n");
            fw.write(v.toString());
            nlrRecursion(v.getLeft(), fw);
            nlrRecursion(v.getRight(), fw);
        }
    }
}
public class Main implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            Tree myTree = new Tree();
            if (sc.hasNext()) {
                myTree.setRoot(new Vertex(sc.nextInt()));
            }
            while (sc.hasNext()) {
                Vertex v = new Vertex(sc.nextInt());
                myTree.addElem(v);
            }
            sc.close();
            myTree.nlr();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}