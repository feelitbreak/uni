/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 6. Индивидуальное задание 5
Условие:
Использовать Классы Date, Calendar, DateFormat, Formatter, SimpleDateFormat
Общее задание:
1. Ввод, вывод(пребразование из строки в Date, Calendar и наоборот).
2. Проверить данные с неправильными : месяцами(>12), номерами дней месяца (>31),
часами, минутами, секундами (стандартный метод форматирующего класса )
3. Для Calendar изменять данные , использовать методы: settime, add,roll.
Для Calendar вывести и изменять: час, минуты , секунды.

Для SimpleDateFormat вывести:
1. Эра (в английской локализации - AD и BC)
2. Номер месяца без лидирующих нулей
3. День недели в месяце без лидирующих нулей
4. Час в 24-часовом формате
5. Минуты с лидирующим нулем

Для Formatter вывести:
1. Название месяца
2. Стандартное представление в виде: день, месяц чч:мм:сс часовой пояс, год
3. Миллисекунды (от 000 до 999)
*/
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        DateClass myDates = new DateClass();
        myDates.getValidDates("in.txt");
        System.out.println("Полученные даты:");
        myDates.outputDates();
        myDates.setTime();
        System.out.println("Изменённые даты:");
        myDates.outputDates();
        System.out.println("Вывод через SDF:");
        myDates.outputSDF();
        System.out.println("Вывод через Formatter:");
        myDates.outputFormatter();
    }
}
