import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        ClassBD bd = new ClassBD();
        bd.inputList("test1.txt");
        bd.inputMap("test1.txt");
        bd.checkMark();
        bd.outList("rezuncorrect.txt");
        bd.checkSurname();
        bd.outMap("rezdel.txt");
        bd.search("rezreg.txt");
        bd.sort();
        bd.outList("rezsort.txt");
        bd.outXML("rezxml.txt");
    }
}
