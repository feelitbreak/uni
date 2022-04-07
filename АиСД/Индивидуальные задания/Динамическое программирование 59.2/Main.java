import java.io.*;

class Report {
    private final int[] a;
    private final int n;

    public Report() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("report.in")));
        st.nextToken();
        n = (int) st.nval;
        a = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            a[i] = (int) st.nval;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Report myRep = new Report();
    }
}
