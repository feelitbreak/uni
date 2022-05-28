import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileProcessor fp = new FileProcessor("input1.txt", "input2.txt");
        fp.processText();
        fp.outText("output1.txt");
        fp.outFragments("output2.txt");
        fp.outNumbers("output3.txt");
    }
}
