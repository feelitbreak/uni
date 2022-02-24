import java.io.*;

class BLR {
    boolean b;
    int l;
    int r;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (b) {
            sb.append(1);
        } else {
            sb.append(0);
        }
        sb.append(" ").append(l);
        sb.append(" ").append(r);
        return sb.toString();
    }
}
class BinarySearch {
    private final int n;
    private final int[] mass;
    private final int[] enq;

    public BinarySearch() throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();
        n = (int) st.nval;
        mass = new int[n];
        for(int i = 0; i < n; i++) {
            st.nextToken();
            mass[i] = (int) st.nval;
        }
        st.nextToken();
        int k = (int) st.nval;
        enq = new int[k];
        for(int i = 0; i < k; i++) {
            st.nextToken();
            enq[i] = (int) st.nval;
        }
    }

    private BLR search(int a) {
        BLR blr = new BLR();
        int l1 = 0;
        int r1 = n;
        int l2 = 0;
        int r2 = n;
        int m1;
        int m2;
        while(l1 < r1 || l2 < r2) {
            if(l1 < r1) {
                m1 = (l1 + r1) / 2;
                if (mass[m1] < a) {
                    l1 = m1 + 1;
                } else {
                    r1 = m1;
                }
            }
            if(l2 < r2) {
                m2 = (l2 + r2) / 2;
                if (mass[m2] > a) {
                    r2 = m2;
                } else {
                    l2 = m2 + 1;
                }
            }
        }
        blr.b = l1 != r2;
        blr.l = l1;
        blr.r = r2;
        return blr;
    }

    public void out() {
        for (int a : enq) {
            System.out.println(search(a));
        }
    }

}
public class Main {

    public static void main(String[] args) throws IOException {
        BinarySearch bs = new BinarySearch();
        bs.out();
    }
}
