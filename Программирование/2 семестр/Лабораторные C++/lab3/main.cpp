/* Кендысь Алексей. Группа 9. 1 курс, 2 семестр. C++, Лаб 3. Индивидуальное задание 5
Условие:
Класс стек (использовать массив, Тип: элемента char).  Операция ++ - добавить элемент, операция -- (удалить элемент) +( объединение двух cтеков). 
Операция +N(-N, *N, /N) – добавление числа N к  видимому  элементу стека (аналогично с вычитанием, умножением, делением).
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	cout << "Введите размерность стека.\n";
	int N;
	cin >> N;
	while (N < 1)
	{
		cout << "Ошибка. Введите ещё раз.\n";
		cin >> N;
	}
	Stack A(N);
	cout << "Введите элементы стека.\n";
	cin >> A;
	cout << "Стек A: ";
	cout << A<<endl;
	Stack B; 
	B = A;
	cout << "B=A\nРезультат:\nСтек A: " << A << "Стек B: " << B<<endl;
	cout << "B=++A\n";
	B = ++A;
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B << endl;
	cout << "B=A++\n";
	B = A++;
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B << endl;
	cout << "B=--A\n";
	B = --A;
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B << endl;
	cout << "B=A--\n";
	B = A--;
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B << endl;
	Stack C = A + B;
	cout << "C=A+B\nРезультат:\nСтек A: " << A << "Стек B: " << B << "Стек C: " << C << endl;
	cout << "C=A+3\n";
	C = A + 3;
	cout << "Результат:\nСтек A: " << A << "Стек C: " << C << endl;
	cout << "C=C-5\n";
	C = C - 5;
	cout << "Результат:\nСтек C: " << C << endl;
	cout << "C=C*2\n";
	C = C * 2;
	cout << "Результат:\nСтек C: " << C << endl;
	cout << "C=C/N" << endl << "Введите число N.\n";
	cin >> N;
	try 
	{
		if (N == 0) throw 1;
		C = C / N;
		cout << "Результат:\nСтек C: " << C << endl;
	}
	catch (int a)
	{
		if (a == 1)
			cout << "Ошибка. Деление на ноль.\n\n";
	}
	cout << "A>=B (Сравнение видимого элемента)\n";
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B;
	if (A >= B)
		cout << "Да.\n";
	else cout << "Нет.\n";
	cout << "\nA<=B (Сравнение видимого элемента)\n";
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B;
	if (A <= B)
		cout << "Да.\n";
	else cout << "Нет.\n";
	cout << "\nA==B (Сравнение видимого элемента)\n";
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B;
	if (A == B)
		cout << "Да.\n";
	else cout << "Нет.\n";
	cout << "\nA!=B (Сравнение видимого элемента)\n";
	cout << "Результат:\nСтек A: " << A << "Стек B: " << B;
	if (A != B)
		cout << "Да.\n";
	else cout << "Нет.\n";
	cout << "\nA[N]=n\n";
	cout << "Введите N.\n";
	cin >> N;
	try
	{
		if (N < 0 || N>=A.Size()) throw 2;
		cout << "A[N] == " << A[N];
		A[N] = '3';
		cout << "\nВозьмём n == '3'\nРезультат: \nA[N] == " << A[N] << endl;
	}
	catch (int a)
	{
		if (a==2)
			cout << "Ошибка. Такого элемента не существует.\n";
	}
	system("pause");
	return 0;
}