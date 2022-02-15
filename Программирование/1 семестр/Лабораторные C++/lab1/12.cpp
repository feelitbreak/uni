/* Кендысь Алексей. Группа 9. Задание 12
Условие:
Найти все простые делители числа.
Тесты:
36 - 1
     2
     3
93 - 1
     3
     31 
22 - 1
     2
	 11
1  - 1
59 - 1
     59
*/
#include<iostream>
using namespace std;
int main()
{
	int N, a, b;
	setlocale(LC_ALL, "rus");
	cout << "Введите число\n";
	cin >> N;
	b = N;
	cout <<"Все простые делители этого числа:\n1" << endl;
	for (int i = 2; i <= b; i++)
	{
		if (N%i == 0)
		{
			while (N%i == 0)
			{
				N /= i;
			}
			cout << i << endl;
		}
	}
	system("pause");
	return 0;
}