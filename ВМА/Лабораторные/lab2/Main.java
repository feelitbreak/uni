import java.io.PrintWriter;
public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        Matrix myMatrix = new Matrix(10);
        out.println("Вектор -a:");
        myMatrix.outA();
        out.println("Вектор -b:");
        myMatrix.outB();
        out.println("Вектор c:");
        myMatrix.outC();
        out.println("Точное решение y:");
        myMatrix.outY();
        out.println("Вектор f:");
        myMatrix.outF();
        myMatrix.algorithm();
        out.println("Полученное решение y0:");
        myMatrix.outY0();
        System.out.print("Относительная погрешность = ");
        myMatrix.outError();
    }
}
