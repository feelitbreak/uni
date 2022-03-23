import java.io.*;

class Palindrome {
    private final String s;
    private final int[][] sol;

    public Palindrome() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        s = br.readLine();
        br.close();
        sol = new int[s.length()][s.length()];
    }

    public void formSol() {
        int n = s.length();
        for(int i = 0; i < n - 1; i++) {
            sol[i][i] = 1;
            if(s.charAt(i) == s.charAt(i + 1)) {
                sol[i][i + 1] = 2;
            } else {
                sol[i][i + 1] = 1;
            }
        }
        sol[n - 1][n - 1] = 1;

        for(int j = 2; j < n; j++) {
            for(int i = j - 2; i >= 0; i--) {
                if(s.charAt(i) == s.charAt(j)) {
                    sol[i][j] = sol[i + 1][j - 1] + 2;
                } else {
                    sol[i][j] = Math.max(sol[i + 1][j], sol[i][j - 1]);
                }
            }
        }
    }

    public void out() throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(String.valueOf(sol[0][s.length() - 1]));
        fw.close();
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Palindrome p = new Palindrome();
        p.formSol();
        p.out();
    }
}
