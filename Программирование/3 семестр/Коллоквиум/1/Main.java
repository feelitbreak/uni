import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        SumClass mySum = new SumClass("input.txt");
        mySum.output("output.txt");
    }
}
