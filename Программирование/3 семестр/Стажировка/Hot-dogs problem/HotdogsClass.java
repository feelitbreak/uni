import java.io.*;
import java.util.*;
public class HotdogsClass {
    private int x;
    private int y;
    HotdogsClass(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter("[\n\t\r,]+");
        try {
            x = sc.nextInt();
            y = sc.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }
    void processNumbers(String fileName) throws IOException {
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter("[\n\t\r ]+");
        FileWriter fw = new FileWriter("output.txt");
        int a;
        while(sc.hasNext()) {
            a = sc.nextInt();
            if (a % x == 0 && a % y == 0) {
                fw.write("HotDog");
            }
            else if (a % x == 0) {
                fw.write("Hot");
            }
            else if (a % y == 0) {
                fw.write("Dog");
            }
            else {
                fw.write(String.valueOf(a));
            }
            if (sc.hasNext()) {
                fw.write(" ");
            }
        }
        fw.close();
        sc.close();
    }
}
