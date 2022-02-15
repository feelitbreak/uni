/* Кендысь Алексей. Группа 9. Задание 10
Условие:
Количество разных цифр.
Тесты:
11112203791114 - 7 цифр
123 - 3 цифры
4444 - 1 цифра
*/
#include <iostream>
using namespace std;
int main()
{
	int b, k, x = 0;
	long a, c;
	setlocale(LC_ALL, "rus");
	cout << "Введите число\n";
	cin >> a;
	c = a;
	for (int i = 0; i <= 9; i++)
	{
		a = c;
		k = 0;
		while (a)
		{
			b = a % 10;
			if (b == i)
			{
				k++;
			}
			a /= 10;
		}
		if (k != 0)
			x++;
	}
	cout << "Количество различных цифр = " << x << endl;
	system("pause");
	return 0;
}