/* Кендысь Алексей. Группа 9. Лекции. Упражнение 5.1
Условие:
Даны три числа a, b и c равные 0, 1 и 2. Числа не упорядочены. Не используя циклы (if, while), поменять их местами так, чтобы a=1, b=0, c=2
Тесты:
2 1 0 - a=1,b=0,c=2
1 2 0 - a=1,b=0,c=2
0 1 2 - a=1,b=0,c=2
1 0 2 - a=1,b=0,c=2
*/
#include <iostream>
using namespace std;
int main()
{
	int a, b, c, d, e;
	setlocale(LC_ALL, "Russian");
	cout << "Введите значения a,b,c\n";
	cin >> a >> b >> c;
	d = c;
	e = b;
	c = (a > b && a > c) ? a : c;
	c = (b > a && b > c) ? b : c;
	b = (a < b && a < d) ? a : b;
	b = (d < b && d < a) ? d : b;
	a = ((d<e && d>a) || (d > e && d < a)) ? d : a;
	a = ((e<d && e>a) || (e > d && e < a)) ? e : a;
	cout << "a = " << a << ", b = " << b << ", c = " << c << endl;
	system("pause");
	return 0;
}