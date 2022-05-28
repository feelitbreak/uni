//Кендысь Алексей, группа 9. Контрольная работа. Вариант 2
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
#include <fstream>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	DeQueue<double> A;
	A.ReadFile("input2.txt");
	cout << "Результат ввода очереди из файла:\n";
	cout << A;
	A.OutputFile("output.txt");
	cout << "Очередь также была записана в файл output.txt\n";
	DeQueue<double> B(A);
	cout << "Введите кол-во первых элементов, которые хотите удалить.\n";
	int K;
	cin >> K;
	while (K < 0)
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> K;
	}
	A - K;
	cout << "Результат удаления первых K элементов:\n";
	cout << A;
	if (A.IsEmpty())
		cout << "Очередь пуста.\n";
	cout << "Результат сравнения изначальной очереди с полученной:\n";
	if (A < B)
		cout << "В полученной очереди меньше положительных элементов.\n";
	else cout << "В полученной очереди не меньше положительных элементов.\n";
	DeQueue<Elem> C;
	cout << "Очередь структур, ввод из файла:\n";
	C.ReadFile("input22.txt");
	cout << C;
	cout << "Введите число и лексему.\n";
	double a;
	cin >> a;
	string s;
	cin >> s;
	Elem x(a, s);
	cout << "Полученная структура:\n" << x << endl;
	cout << "Введите число, с которым хотите сравнить дробную часть.\n";
	int k;
	cin >> k;
	if (x > k)
		cout << "Дробная часть числа структуры больше введённого числа.\n";
	else cout << "Дробная часть числа структуры не больше введённого числа.\n";
	system("pause");
	return 0;
}