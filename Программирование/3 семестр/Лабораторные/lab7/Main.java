/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 6. Индивидуальное задание 5
Условие:
На прямой гоночной трассе стоит N автомобилей, для каждого из которых известны начальное
положение и скорость. Вывести время и названия машин первых K обгонов. Использовать класс
HashTable. Отсортировать по времени обгонов для конкретного авто, т.е. переопределить hash
код для объекта ключа в HashTable.
*/
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        try {
            HashClass hc = new HashClass();
            hc.inputCars("in.txt");
            System.out.println("Автомобили из файла:");
            hc.showCars();
            hc.findOvertakes();
            System.out.println("Полученный Hashtable с временем:");
            hc.showHashtable();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
