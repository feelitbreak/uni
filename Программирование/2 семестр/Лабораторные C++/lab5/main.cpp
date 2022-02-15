/*Кендысь Алексей. Группа 9. 1 курс, 2 семестр. C++, Лаб 5. Индивидуальное задание 5
Условие:
Используя абстрактные классы, реализовать конкретные классы: ArrayStack( на массиве) и
ArrayStackIterator.
*/
#include <iostream>
#include "header.h"
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	int N;
	cout << "Введите размерность стека.\n";
	cin >> N;
	while (N < 0)
	{
		cout << "Ошибка формата размерности. Введите ещё раз.\n";
		cin >> N;
	}
	ArrayStack S(N);
	cout << "Введите элементы стека. Введите 101, чтобы остановиться.\n";
	int a=0;
	while (!S.IsFull())
	{
		cin >> a;
		if (a == 101) break;
		S.push(a);
	}
	if (S.IsFull()) cout << "Стек заполнен.\n";
	if (!S.IsEmpty())
	{
		cout << "Удалим последний элемент из стека.\n";
		cout << "Удалённый элемент - " << S.pop() << endl;
	}
	ArrayStackIterator A(S);
	cout << "Полученный стек:\n";
	while (A.InRange())
	{
		cout << *A << endl;
		++A;
	}
	system("pause");
	return 0;
}