import java.io.*;

class Frog {
    private final int[] mosquitoes;
    private final int[] sol;

    public Frog() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();
        int n;
        n = (int) st.nval;
        mosquitoes = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            mosquitoes[i] = (int) st.nval;
        }
        sol = new int[n];
    }

    public void formSol() {
        sol[0] = mosquitoes[0];
        if(sol.length > 1) {
            sol[1] = -1;
        }
        if(sol.length > 2) {
            sol[2] = sol[0] + mosquitoes[2];
        }
        for(int i = 3; i < sol.length; i++) {
            if (sol[i - 3] > sol[i - 2]) {
                sol[i] = sol[i - 3] + mosquitoes[i];
            } else {
                sol[i] = sol[i - 2] + mosquitoes[i];
            }
        }
        if(sol.length > 1) {
            sol[sol.length - 2] = -1;
        }
    }

    public void out() {
        System.out.println(sol[sol.length - 1]);
        if(sol[0] != -1) {
            int k = 1;
            mosquitoes[0] = sol.length;
            for(int i = sol.length - 1; true; ) {
                if(i > 2) {
                    if (sol[i - 3] > sol[i - 2]) {
                        i -= 3;
                    } else {
                        i -= 2;
                    }
                    mosquitoes[k] = i + 1;
                    k++;
                } else if (i == 2) {
                    mosquitoes[k] = 1;
                    k++;
                    break;
                } else {
                    break;
                }
            }
            System.out.print(mosquitoes[k - 1]);
            for(int i = k - 2; i >= 0; i--) {
                System.out.print(" ");
                System.out.print(mosquitoes[i]);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Frog frog = new Frog();
        frog.formSol();
        frog.out();
    }
}
