/* Кендысь Алексей. Группа 9. Задание 1 
Условие:
Найти сумму цифр целого числа(проверять и отрицательные числа).
Тесты:
-5673 - 21
123 - 6
4 - 4
1987659 - 45
*/

//Проверка
#include <iostream>
using namespace std;
int main()
{
	int a, b=0;
	setlocale(LC_ALL, "rus");
	cout<<"Введите целое число\n"
	cin >> a;
	if (a<0)
	{
		a *= -1;
	}
	while (a!=0)
	{
		b = a % 10 + b;
		a /= 10;
	}
	cout <<"Сумма цифр числа равна "<<b<<endl;
	system("pause");
	return 0;
}