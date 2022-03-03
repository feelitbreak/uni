import java.io.*;

class Frog {
    private int[] mosquitoes;
    private int[] sol;

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
                sol[i] = sol[i - 3] + mosquitoes[3];
            } else {
                sol[i] = sol[i - 2] + mosquitoes[3];
                sol[i - 3] = -1;
            }
        }
        if(sol.length > 1) {
            sol[sol.length - 2] = -1;
        }
    }
    
    public void out() {

    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Frog frog = new Frog();
        frog.formSol();
    }
}
