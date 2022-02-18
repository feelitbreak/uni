import java.io.*;

class Vertex {
    private final int num;
    private Vertex left;
    private Vertex right;
    private int height;
    private int length;

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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return num + " " + height;
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
    private int lmax = 0;
    boolean multiple = false;
    Vertex vMain;
    Vertex[] vDel;

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        if (br.ready()) {
            root = new Vertex(Integer.parseInt(br.readLine()));
        }
        while (br.ready()) {
            Vertex v = new Vertex(Integer.parseInt(br.readLine()));
            addElem(v);
        }
        br.close();
    }

    public Vertex getRoot() {
        return root;
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

    private void delElem(int num) {
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

    public void lrn(Vertex v) {
        if (v != null) {
            lrn(v.getLeft());
            lrn(v.getRight());
            if (!v.hasRight() && v.hasLeft()) {
                int a = v.getLeft().getHeight() + 1;
                v.setHeight(a);
                checkV(v, a);
            } else if (v.hasRight() && !v.hasLeft()) {
                int a = v.getRight().getHeight() + 1;
                v.setHeight(a);
                checkV(v, a);
            } else if (v.hasLeft()) {
                v.setHeight(Math.max(v.getRight().getHeight(), v.getLeft().getHeight()) + 1);
                int l = v.getRight().getHeight() + v.getLeft().getHeight() + 2;
                checkV(v, l);
            }
        }
    }

    private void checkV(Vertex v, int l) {
        v.setLength(l);
        if(l > lmax) {
            lmax = l;
            vMain = v;
            multiple = false;
        } else if (l == lmax) {
            multiple = true;
            if(v.getHeight() < vMain.getHeight()) {
                vMain = v;
            }
        }
    }

    public void delete() {
        
    }

    public void nlr(Vertex v, PrintWriter pw) throws IOException {
        if (v != null) {
            pw.println(v);
            nlr(v.getLeft(), pw);
            nlr(v.getRight(), pw);
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
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }

    public void run() {
        try {
            Tree myTree = new Tree();
            myTree.init();
            myTree.lrn(myTree.getRoot());
            myTree.delete();
            PrintWriter pw = new PrintWriter("out.txt");
            myTree.nlr(myTree.getRoot(), pw);
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}