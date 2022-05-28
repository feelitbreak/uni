//Кендысь Алексей, 9 группа, вариант 2
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Text bd = new Text();
        bd.read("test.txt");
        bd.createList();
        bd.findBigInt("rezultnumb.txt");
        bd.createMap("rezultmap.txt");
        bd.sort("rezultsort.txt");
        bd.findMathProblems("rezultmath.txt");
        bd.outBin("rezultbin.txt");
        System.out.println("Вывод лексем-дат в форматированном виде:");
        bd.findDates();
    }
}
