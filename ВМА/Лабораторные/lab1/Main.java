/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 1
*/
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        PrintWriter out =  new PrintWriter(System.out, true);
        Matrix myMatrix = new Matrix(10);
        System.out.println("Задание 1).");
        System.out.println("Сгенерированная матрица A(n=10):");
        myMatrix.outA();
        System.out.println("Вектор f:");
        myMatrix.outF();
        System.out.println("Точное решение x:");
        myMatrix.outX();
        myMatrix.gauss(true);
        System.out.println("Полученное приближённое решение x0:");
        myMatrix.outX0();
        System.out.print("Относительная погрешность = ");
        myMatrix.outError();
        System.out.println("Задание 2).");
        System.out.println("Обратная матрица A1:");
        myMatrix.outA1();
        System.out.println("Результат умножения A1*A:");
        myMatrix.outE();
        System.out.println("Задание 3).");
        myMatrix.outTable();
    }
}
