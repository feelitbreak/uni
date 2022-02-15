/* Кендысь Алексей. Группа 9. Задание 8
Условие:
Является ли число простым.
Тесты:
15 - Не является простым
23 - Простое
479 - Простое
2 - Простое
1 - Не является простым
*/
#include <iostream>
using namespace std;
int main()
{
    int a, b, c;
	setlocale(LC_ALL, "rus");
	cout << "Введите два числа\n" << endl;
	cin >> a >> b;
	c = a;
	while (a%c != 0 || b % c != 0)
	{
		c--;
	}
	cout << "НОД этих двух чисел равен " << c << ", а их НОК равен " << (a*b) / c << endl;
	system("pause");
	return 0;
}