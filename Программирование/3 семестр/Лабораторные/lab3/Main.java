/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 3. Индивидуальное задание 5
Условие:
В данной действительной квадратной матрице порядка n найти max по модулю
элемент. Получить квадратную матрицу порядка n-1 путем выбрасывания из исходной
какой-либо строки и столбца, на пересечении которых расположен элемент с найденным
значением. Отсортировать в последней строке элементы по убыванию, используя
Соmparator и реализовать бинарный поиск элемента в строке(стандартный метод).
*/
import java.io.*;
import java.util.Scanner;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        Execute();
    }
    public static void Execute() throws IOException {
        PrintWriter out =  new PrintWriter(System.out, true);
        Scanner sc = new Scanner(System.in);
        out.println("Введите n.");
        int n = sc.nextInt();
        Double[][] a = new Double[n][n];
        Matrix.generate(a);
        System.out.println("Сгенерированная матрица:");
        Matrix.output(a);
        Matrix.findMax(a);
        System.out.println("Полученная матрица:");
        Matrix.output2(a);
        System.out.println("Изначальная матрица с отсортированной последней строкой:");
        Arrays.sort(a[a.length - 1], new cmp());
        Matrix.output(a);
        out.println("Введите число, которое хотите найти в последней строке изначальной матрицы.");
        Arrays.sort(a[a.length - 1]);
        double c = sc.nextDouble();
        int j = Arrays.binarySearch(a[a.length - 1], c);
        if (j < 0) {
            out.println("Не удалось найти элемент.");
        } else {
            out.println("Позиция элемента = " + (a.length - j));
        }
        sc.close();
    }
}
