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

    public boolean hasLeft() {
        return left != null;
    }
    public boolean hasRight() {
        return right != null;
    }
}

class Tree {
    private Vertex root;
    private int n;
    private Vertex[] A;

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bst.in"));
        n = Integer.parseInt(br.readLine());
        root = new Vertex(Integer.parseInt(br.readLine()));
        A = new Vertex[n];
        A[0] = root;
        for (int i = 1; br.ready(); i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            Vertex v = new Vertex(Integer.parseInt(st.nextToken()));
            int id = Integer.parseInt(st.nextToken()) - 1;
            if(st.nextToken().equals("R")) {
                A[id].setRight(v);
            } else {
                A[id].setLeft(v);
            }
            A[i] = v;
        }
        br.close();
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

    public void delElem(int num) {
        Vertex parent = root;
        Vertex vDel = null;
        boolean isLeft = false;
        boolean isRight = false;
        while(true) {
            if(num > parent.getNum() && parent.hasRight() && num == parent.getRight().getNum()) {
                vDel = parent.getRight();
                isRight = true;
                break;
            } else if(num > parent.getNum() && parent.hasRight()) {
                parent = parent.getRight();
            } else if(num > parent.getNum()) {
                break;
            } else if (num < parent.getNum() && parent.hasLeft() && num == parent.getLeft().getNum()) {
                    vDel = parent.getLeft();
                    isLeft = true;
                    break;
            } else if (num < parent.getNum() && parent.hasLeft()) {
                parent = parent.getLeft();
            } else if(num < parent.getNum()) {
                break;
            } else {
                vDel = parent;
                break;
            }
        }

        if(vDel != null) {
            if(!vDel.hasLeft() && !vDel.hasRight() && isLeft) {
                parent.setLeft(null);
            } else if(!vDel.hasLeft() && !vDel.hasRight() && isRight) {
                parent.setRight(null);
            } else if(!vDel.hasLeft() && !vDel.hasRight()){
                root = null;
            } else if(vDel.hasLeft() && !vDel.hasRight() && isLeft) {
                parent.setLeft(vDel.getLeft());
            } else if(vDel.hasLeft() && !vDel.hasRight() && isRight) {
                parent.setRight(vDel.getLeft());
            } else if(vDel.hasLeft() && !vDel.hasRight()) {
                root = root.getLeft();
            } else if(!vDel.hasLeft() && vDel.hasRight() && isLeft) {
                parent.setLeft(vDel.getRight());
            } else if(!vDel.hasLeft() && vDel.hasRight() && isRight) {
                parent.setRight(vDel.getRight());
            } else if(!vDel.hasLeft() && vDel.hasRight()) {
                root = root.getRight();
            } else if(isRight) {
                if (vDel.getRight().hasLeft()) {
                    Vertex newV = findMin(vDel.getRight());
                    newV.setLeft(vDel.getLeft());
                    newV.setRight(vDel.getRight());
                    parent.setRight(newV);
                } else {
                    vDel.getRight().setLeft(vDel.getLeft());
                    parent.setRight(vDel.getRight());
                }
            } else if(isLeft) {
                if (vDel.getRight().hasLeft()) {
                    Vertex newV = findMin(vDel.getRight());
                    newV.setLeft(vDel.getLeft());
                    newV.setRight(vDel.getRight());
                    parent.setLeft(newV);
                } else {
                    vDel.getRight().setLeft(vDel.getLeft());
                    parent.setLeft(vDel.getRight());
                }
            } else {
                if (vDel.getRight().hasLeft()) {
                    Vertex newV = findMin(vDel.getRight());
                    newV.setLeft(vDel.getLeft());
                    newV.setRight(vDel.getRight());
                    root = newV;
                } else {
                    vDel.getRight().setLeft(vDel.getLeft());
                    root = vDel.getRight();
                }
            }
        }
    }

    public void nlr() throws IOException {
        FileWriter fw = new FileWriter("bst.out");
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

    private Vertex findMin(Vertex r) {
        Vertex temp;
        temp = r.getLeft();
        while(temp.hasLeft()) {
            r = temp;
            temp = temp.getLeft();
        }
        if(temp.hasRight()) {
            r.setLeft(temp.getRight());
        } else {
            r.setLeft(null);
        }
        return temp;
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
            myTree.nlr();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}