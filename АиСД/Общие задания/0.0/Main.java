import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        Set<BigInteger> numb = new HashSet<>();
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            numb.add(sc.nextBigInteger());
        }
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger i : numb) {
            sum = sum.add(i);
        }
        sc.close();
        FileWriter fw = new FileWriter("output.txt");
        fw.write(sum.toString());
        fw.close();
    }
}
