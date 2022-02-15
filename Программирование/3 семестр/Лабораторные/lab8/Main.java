/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 8. Индивидуальное задание 5
Условие:
Автопарк
Управление автопарком должно иметь сведения:
о водителях: табельный номер, фамилия водителя, категория, стаж, адрес, дата рождения.
BM-3001
V-Menon
B
0
Levingstock 33a
07.11.1996
*/
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.security.*;
import javax.xml.transform.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, TransformerException, ParserConfigurationException {
        try {
            Passwords passwords = new Passwords();
            passwords.input("inPasswords.txt");
            System.out.println("Введите 1 - Войти, 2 - Зарегистрироваться.");
            int a;
            Scanner sc = new Scanner(System.in);
            boolean verified;
            a = Integer.parseInt(sc.nextLine());
            if (a != 1 && a != 2) {
                System.out.println("Ошибка.");
                sc.close();
                return;
            }
            System.out.println("Введите логин.");
            String login;
            login = sc.nextLine();
            System.out.println("Введите пароль.");
            String pass;
            pass = sc.nextLine();

            if (a == 1) {
                verified = passwords.verify(login, pass);
            }
            else {
                passwords.register(login, pass);
                System.out.println("Спасибо. Перезапустите программу и войдите.");
                sc.close();
                return;
            }
            if (verified) {
                System.out.println("Вы успешно вошли в программу.");
                CarPark myPark = new CarPark();
                System.out.println("Как вы хотите ввести данные?");
                System.out.println("1 - Из текстового файла в обычном формате.");
                System.out.println("2 - В бинарном формате.");
                System.out.println("3 - В формате XML.");
                a = sc.nextInt();
                if (a == 1) {
                    myPark.inputText("inText.txt");
                } else if (a == 2) {
                    myPark.inputBin("inBin.txt");
                } else if (a == 3) {
                    myPark.inputXML("inXML.txt");
                } else {
                    System.out.println("Ошибка.");
                    sc.close();
                    return;
                }
                myPark.show();
                System.out.println("Что вы хотите сделать с данными?");
                System.out.println("1 - Сериализация в бинарный формат.");
                System.out.println("2 - Сериализация в XML формат с помощью XDecoder.");
                System.out.println("3 - Сериализация в XML формат с помощью DOM.");
                System.out.println("4 - Заменить элемент в текстовом файле.");
                a = sc.nextInt();
                if (a == 1) {
                    myPark.outBin("outBin.txt");
                    System.out.println("Данные были успешно записаны в файл.");
                } else if (a == 2) {
                    myPark.outXML("outXML.txt");
                    System.out.println("Данные были успешно записаны в файл.");
                } else if (a == 3) {
                    myPark.outDOM("outDOM.txt");
                    System.out.println("Данные были успешно записаны в файл.");
                } else if (a == 4) {
                    String[] info = new String[6];
                    System.out.println("Введите табельный номер.");
                    sc.nextLine();
                    info[0] = sc.nextLine();
                    System.out.println("Введите фамилию водительницы_ля.");
                    info[1] = sc.nextLine();
                    System.out.println("Введите категорию.");
                    info[2] = sc.nextLine();
                    System.out.println("Введите стаж.");
                    info[3] = sc.nextLine();
                    System.out.println("Введите адрес.");
                    info[4] = sc.nextLine();
                    System.out.println("Введите дату рождения.");
                    info[5] = sc.nextLine();
                    myPark.addElement(info);
                    System.out.println("Текстовый файл был успешно изменён.");
                } else {
                    System.out.println("Ошибка.");
                }
            }
            else {
                System.out.println("Ошибка. Проверьте правильность введённого пароля.");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
