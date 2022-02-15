/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 5. Индивидуальное задание 5
Условие:
Написать регулярное выражение определяющее является ли данная строчка номером паспорта и
местом выдачи. Проверять название на существование такового - не нужно. Городам соответствуют
буквы в номере: Минск – MP, Брест – АВ, и т.д.; областные города –МС. КВ, РР - паспорта,
выданные Министерством иностранных дел (в случае проживания за рубежом).
Дополнительное задание:
1. С помощью метода find (class Matcher) найти в строке с тегами HTML-документа все теги, строки между
тегами, атрибуты. Результаты вывести.
2. С помощью регулярного выражения заменить слова в тексте на новые.
*/
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out, true);
        try {
            List<String> lines = FileClass.input("in1.txt");
            out.println("Полученные строки из файла ввода:");
            lines.forEach(out::println);
            FileClass.findPatterned(lines);
            FileClass.output(lines, "out1.txt");
            out.println("Результат был успешно записан в файл.");
            FileClass.findInHtml("in2.txt", "out2.txt");
            FileClass.replaceText("in3.txt", "out3.txt");

        } catch (FileNotFoundException e) {
            out.println(e.getMessage());
        }
    }
}
