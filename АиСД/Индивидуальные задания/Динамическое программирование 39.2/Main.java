import java.io.*;
import java.util.*;

class Vertex {
    public int[] descendants;
    private int k = 0;
    private int h;
    private int i;
    private int j;
    private boolean endWord = true;

    public Vertex(int h, int i, int j) {
        descendants = new int[TelephoneNumber.MAX_DESCENDANTS];
        this.h = h;
        this.i = i;
        this.j = j;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getH() {
        return h;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isEndWord() {
        return endWord;
    }

    public void setEndWord(boolean endWord) {
        this.endWord = endWord;
    }

    public void addToDescendants(int v) {
        descendants[k] = v;
        k++;
    }
}

class Word {
    private final String word;
    private final String num;
    private final int length;

    public Word(String word, String num) {
        this.word = word;
        this.num = num;
        length = num.length();
    }

    public String getWord() {
        return word;
    }

    public String getNum() {
        return num;
    }

    public int getLength() {
        return length;
    }
}

class TelephoneNumber {
    private final String num;
    private final int n;
    private final Word[] words;
    private final String[] table = {
            "2", "2", "2", "3", "3", "3", "4", "4", "1", "1", "5", "5", "6",
            "6", "0", "7", "0", "7", "7", "8", "8", "8", "9", "9", "9", "0"
    };
    private final Vertex[] radixTree;
    private int nRadixTree = 0;
    private final int[] sol;
    private final int[] k;
    private int[] res;

    public static final int MAX_DESCENDANTS = 10;

    public TelephoneNumber() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        num = br.readLine();
        n = Integer.parseInt(br.readLine());
        words = new Word[n];
        String word;
        for(int i = 0; i < n; i++) {
            word = br.readLine();
            words[i] = new Word(word, decipher(word));
        }
        sol = new int[num.length()];
        k = new int[num.length()];

        radixTree = new Vertex[2 * n + 1];
    }

    public void buildRadixTree() {
        radixTree[0] = new Vertex(-1, -1, -1);
        radixTree[0].setEndWord(false);
        nRadixTree++;
        for (int i = 0; i < n; i++) {
            if(words[i].getLength() <= num.length()) {
                addToTree(i);
            }
        }
    }

    private void addToTree(int iWord) {
        Word word = words[iWord];
        Vertex v = radixTree[0];

        boolean noElement = false;
        int i = 0;
        while(!noElement && v.getK() != 0) {
            for(int s = 0; s < v.getK(); s++) {
                Vertex uV = radixTree[v.descendants[s]];

                int j = prefix(word.getNum(), i, uV);

                if (uV.getI() + j == uV.getJ()) {
                    if (i + j == word.getLength() && uV.isEndWord()) {
                        return;
                    } else if(i + j == word.getLength()) {
                        uV.setEndWord(true);
                        uV.setH(iWord);
                        uV.setI(i);
                        uV.setJ(i + j);
                        return;
                    } else {
                            v = uV;
                            i += j;
                            break;
                        }
                } else if(i + j == word.getLength()) {
                    Vertex a = new Vertex(uV.getH(), uV.getI() + j, uV.getJ());
                    radixTree[nRadixTree] = a;
                    nRadixTree++;

                    a.setK(uV.getK());
                    System.arraycopy(uV.descendants, 0, a.descendants, 0, a.getK());

                    if(!uV.isEndWord()) {
                        a.setEndWord(false);
                        uV.setEndWord(true);
                    }

                    uV.setH(iWord);
                    uV.setI(i);
                    uV.setJ(word.getLength());
                    uV.setK(0);
                    uV.addToDescendants(nRadixTree - 1);

                    return;
                } else if (j != 0) {
                    Vertex a = new Vertex(iWord, i + j, word.getLength());
                    Vertex b = new Vertex(uV.getH(), uV.getI() + j, uV.getJ());

                    b.setK(uV.getK());
                    System.arraycopy(uV.descendants, 0, b.descendants, 0, b.getK());
                    if(!uV.isEndWord()) {
                        b.setEndWord(false);
                    }

                    uV.setK(0);
                    radixTree[nRadixTree] = b;
                    uV.addToDescendants(nRadixTree);
                    nRadixTree++;
                    uV.setEndWord(false);
                    uV.setJ(uV.getI() + j);

                    radixTree[nRadixTree] = a;
                    uV.addToDescendants(nRadixTree);
                    nRadixTree++;

                    return;
                } else if (s == v.getK() - 1) {
                    noElement = true;
                }
            }
        }

        Vertex a = new Vertex(iWord, i, word.getLength());
        radixTree[nRadixTree] = a;
        v.addToDescendants(nRadixTree);
        nRadixTree++;
    }

    private int prefix(String num, int i, Vertex u) {
        int j;
        String numToCompare = words[u.getH()].getNum();
        for(j = 0; i + j < num.length() && u.getI() + j < u.getJ(); j++) {
            if(num.charAt(i + j) != numToCompare.charAt(u.getI() + j)) {
                break;
            }
        }
        return j;
    }

    public void formSolution() {
        int[] ind;
        ind = new int[num.length()];
        Arrays.fill(ind, -1);

        for(int i = num.length() - 1; i >= 0; i--) {
            Vertex v = radixTree[0];
            int j = i;
            boolean noElement = false;
            while(j <= num.length() - 1 && !noElement && v.getK() != 0) {
                for(int s = 0; s < v.getK(); s++) {
                    Vertex uV = radixTree[v.descendants[s]];
                    String wNum = words[uV.getH()].getNum();
                    if(num.startsWith(wNum.substring(uV.getI(), uV.getJ()), j)) {
                        if(uV.isEndWord() && j + uV.getJ() - uV.getI() == num.length()) {
                            k[i] = 1;
                            sol[i] = uV.getH();
                            ind[i] = -1;
                            v = uV;
                            j += uV.getJ() - uV.getI();
                            break;
                        } else if(uV.isEndWord() && k[j + uV.getJ() - uV.getI()] != 0 &&
                                (k[i] == 0 || k[i] > k[j + uV.getJ() - uV.getI()] + 1)) {
                            k[i] = k[j + uV.getJ() - uV.getI()] + 1;
                            sol[i] = uV.getH();
                            ind[i] = j + uV.getJ() - uV.getI();
                            v = uV;
                            j += uV.getJ() - uV.getI();
                            break;
                        } else {
                            v = uV;
                            j += uV.getJ() - uV.getI();
                            break;
                        }
                    }
                    if(s == v.getK() - 1) {
                        noElement = true;
                    }
                }
            }
        }

        if(k[0] != 0) {
            res = new int[k[0]];
            for(int i = 0, j = 0; j != -1; j = ind[j], i++) {
                res[i] = sol[j];
            }
        }
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        if(k[0] == 0) {
            pw.print("No solution");
        } else {
            pw.println(k[0]);
            pw.print(words[res[0]].getWord());
            for(int i = 1; i < res.length; i++) {
                pw.print(' ');
                pw.print(words[res[i]].getWord());
            }
        }
        pw.close();
    }

    private String decipher(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for(int i = 0; i < word.length(); i++) {
            if(Character.isDigit(word.charAt(i))) {
                sb.append(word.charAt(i));
            } else {
                sb.append(table[word.charAt(i) - 'A']);
            }
        }
        return sb.toString();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        TelephoneNumber tn = new TelephoneNumber();
        tn.buildRadixTree();
        tn.formSolution();
        tn.out();
    }
}
