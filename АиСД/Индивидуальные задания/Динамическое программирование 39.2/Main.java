import java.io.*;
import java.util.*;

class Word {
    private final String word;
    private final String num;

    public Word(String word, String num) {
        this.word = word;
        this.num = num;
    }

    public String getWord() {
        return word;
    }

    public String getNum() {
        return num;
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
    private final int[] sol;
    private final int[] k;
    private int[] res;

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
    }

    public void formSolution() {
        int[] ind;
        ind = new int[num.length()];
        Arrays.fill(ind, -1);

        String wNum;
        for(int i = 0; i < num.length(); i++) {
            for(int j = 0; j < n; j++) {
                wNum = words[j].getNum();
                if(wNum.length() <= i + 1 && num.startsWith(wNum, i + 1 - wNum.length())) {
                    if(i + 1 - wNum.length() == 0) {
                        k[i] = 1;
                        sol[i] = j;
                    } else if(k[i - wNum.length()] != 0 && (k[i] == 0 || k[i] > k[i - wNum.length()] + 1)) {
                        k[i] = k[i - wNum.length()] + 1;
                        sol[i] = j;
                        ind[i] = i - wNum.length();
                    }
                }
            }
        }

        if(k[num.length() - 1] != 0) {
            res = new int[k[num.length() - 1]];
            for(int i = k[num.length() - 1] - 1, j = num.length() - 1; j != -1; j = ind[j], i--) {
                res[i] = sol[j];
            }
        }
    }

    public void out() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("output.txt");
        if(k[num.length() - 1] == 0) {
            pw.print("No solution");
        } else {
            pw.println(k[num.length() - 1]);
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
        tn.formSolution();
        tn.out();
    }
}
