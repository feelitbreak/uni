/* Кендысь Алексей. Группа 9. 2 курс, 3 семестр. Лаб 2. Индивидуальное задание 5
Условие:
1. Выводить целые значения в восьмеричном и шестнадцатеричном виде
2. Выводить значения с плавающей точкой
3. Использовать спецификатор минимальной ширины поля
4. Использовать спецификатор точности
5. Использовать флаги (flags) форматирования. (5 вариантов)
6. Использование порядкового номера аргумента
*/
import java.io.*;
import java.math.*;
import java.util.*;
public class Lab {
	private final static int BIG_PRECISION = 16;
	public static void main(String[] args) throws IOException {
		Execute();
	}
	public static void Execute() throws IOException {
    PrintWriter out =  new PrintWriter(System.out, true);
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	Formatter fmt = new Formatter();
    out.println("Введите x.");
   	String s;
    s = in.readLine();
    double x = Double.parseDouble(s);
    BigDecimal bigX = new BigDecimal(s);
    BigDecimal minInterval = new BigDecimal(-1);
    BigDecimal maxInterval = new BigDecimal(1);
    if (bigX.compareTo(minInterval) != 1 || bigX.compareTo(maxInterval) != -1) {
    	out.println("Ошибка. x должен принадлежать промежутку (-1; 1).");
    	return;
    }
    out.println("Введите k.");
    s = in.readLine();
    BigInteger k = new BigInteger(s);
    if (k.compareTo(BigInteger.ZERO) != 1) {
    	out.println("Ошибка. k должен быть натуральным.");
    	return;
    }
    fmt.format("Число k в 16 с/с = %#x, в 8 с/с = %#o.%n", k, k);
    BigDecimal y = bigX.add(new BigDecimal(1));
    MathContext mc = new MathContext(BIG_PRECISION);
    y = y.sqrt(mc);
    y = (new BigDecimal(1)).divide(y, BIG_PRECISION, BigDecimal.ROUND_HALF_UP);
    fmt.format("Значение выражения через BigDecimal  = %1$.16f.%n", y);
    fmt.format("Значение выражения через Math  = %1$.16f.%n", 1/Math.sqrt(x+1));
	Series a = new Series(bigX, k);
    fmt.format("Сумма ряда = %+07." + k.add(new BigInteger("1")) + "f.%n", a.value());
    fmt.format("Результат, умноженный на 10^7, = %,f.%n", a.value().multiply(new BigDecimal(10000000)));
    fmt.format("Умноженный на (-1) = %(,f.", a.value().multiply(new BigDecimal(-1)));
    out.println(fmt);
    fmt.close();
    }
}
