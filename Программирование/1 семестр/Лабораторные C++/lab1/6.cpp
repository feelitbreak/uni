/* Кендысь Алексей. Группа 9. Задание 6
Условие:
Является ли число простым
Тесты:
6 - 720
-10 - Ошибка
23 - 862453760
*/
#include <iostream>
using namespace std;
int main()
{
	int a, b;
	setlocale(LC_ALL, "rus");
	cout << "Введите число\n";
	cin >> a;
	b = 0;
	if (a <= 0 || a == 1)
	{
		cout << "Число не является простым\n";
		system("pause");
		return 0;
	}
	for (int i = 2; i < a; ++i)
		if (a%i == 0)
		{
			b++;
		}
	if (b == 0)
	{
		cout << "Число является простым\n";
	}
	else
	{
		cout << "Число не является простым\n";
	}
	system("pause");
	return 0;
}