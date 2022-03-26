import java.util.*;
public class Main {
    private static final int N = 10;
    public static void main(String[] args) {
        Formatter fmt;
        System.out.println("Задание 1).");
        Matrix myMatrix = new Matrix(N);
        System.out.println("Матрица A:");
        myMatrix.outA();
        System.out.println("Вектор точного решения x:");
        myMatrix.outX();
        System.out.println("Вектор правой части f:");
        myMatrix.outF();
        System.out.println("Вектор начального приближения x0:");
        myMatrix.outX0();
        int q;
        q = myMatrix.gradDescent();
        System.out.println("Номер итерации q:");
        if (q != 0) {
            System.out.println(q);
        } else {
            System.out.println("Достигнуто максимальное количество итераций.");
        }
        System.out.println("Полученное приближённое решение xK:");
        myMatrix.outXK();
        fmt = new Formatter();
        fmt.format("Норма невязки:\n%.12f\n", myMatrix.getResidual());
        fmt.format("Абсолютная погрешность:\n%.12f", myMatrix.getError());
        System.out.println(fmt);
        fmt.close();
        System.out.println("\nЗадание 2).");
        double[] w = new double[] {0.2, 0.5, 0.8, 1.0, 1.3, 1.5, 1.8};
        fmt = new Formatter();
        fmt.format("Параметр w    Номер итерации q    ||Ax(q) - f||    ||x - x(q)||\n");
        for (double v : w) {
            q = myMatrix.sor(v);
            fmt.format("%6.1f %15d %24.11f %16.11f\n", v, q, myMatrix.getResidual(), myMatrix.getError());
        }
        System.out.print(fmt);
    }
}
