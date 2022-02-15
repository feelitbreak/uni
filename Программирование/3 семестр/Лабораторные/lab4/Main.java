/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 4. Индивидуальное задание 5
Условие:
Разбить первую строку на лексемы (используя разделители из второй строки), определить в ней
целые числа 2-й с\с. Числа записать в новый отдельный массив. Среди лексем являющихся числами,
найти лексемы не являющиеся палиндромами. Найти число Р(если есть, то должно совпадать с
лексемой), вывести позицию в изначальной строке. Продублировать в строке самое большое число,
добавить его в начало строки. Первую лексему с латинскими буквами и цифрами - удалить из
строки. Все результаты сформировать в строки с помощью String.format и вывести.
/1001,231100k/0001,asdc4,100/sdfdsf,2000/111111111001
*/
import java.io.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        PrintWriter out =  new PrintWriter(System.out, true);
        Scanner sc = new Scanner(System.in);
        out.println("Введите первую строку.");
        String str;
        str = sc.nextLine();
        out.println("Введите вторую строку.");
        String delim;
        delim = sc.nextLine();
        out.println("Введите число P.");
        int p;
        p = sc.nextInt();
        String[] leks = StrClass.divide(str, delim);
        out.println("Полученный массив лексем:");
        StrClass.output(leks);
        out.println("Полученный массив чисел:");
        String[] numbers = StrClass.getNumbers(leks);
        StrClass.output(numbers);
        out.println("Лексемы-числа, не являющиеся палиндромами:");
        StrClass.palindromes(numbers);
        out.println("Результат поиска числа P:");
        StrClass.searchP(str, delim, p);
        out.println("Исходная строка с соответствующими преобразованиями:");
        out.println(StrClass.changeStr(str, delim, leks));
    }
}
