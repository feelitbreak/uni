import java.util.*;
import java.io.*;
public class SumClass {
    private Float sum = (float) 0;
    public SumClass(String filePath) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        sc.useDelimiter("[;, \t]+");
        String leks;
        while(sc.hasNext()) {
            leks = sc.next();
            try {
                sum += Float.parseFloat(leks);
            } catch (NumberFormatException ignored) {}
        }
        sc.close();
    }
    public void output(String filePath) throws IOException {
        FileWriter fr = new FileWriter(filePath);
        if (sum == 0) {
            fr.write("0");
        }
        else {
            fr.write(sum.toString());
        }
        fr.close();
    }
}
