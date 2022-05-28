/* Кендысь Алексей. Группа 9. Контрольная работа. Вариант 1
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
#include <fstream>
using namespace std;
ifstream fin;
int main()
{
	setlocale(LC_ALL, "rus");
	cout << "Введите размерность очереди.\n";
	int N;
	cin >> N;
	while (N <= 0)
	{
		cout << "Ошибка ввода. Введите ещё раз.\n";
		cin >> N;
	}
	Queue A(N);
	Queue B(6);
	fin.open("input1.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input1.txt\n";
		system("pause");
		return 0;
	}
	fin >> A;
	cout << "Очередь A:" << endl << A;
	try 
	{
		A + 15;
	}
	catch (int a)
	{
		if (a == 1) cout << "Очередь заполнена.\n";
	}
	cout << "Очередь A:" << endl << A;
	cout << "Введите очередь B с консоли.\n";
	cin >> B;
	try
	{
		B + 3;
	}
	catch (int a)
	{
		if (a == 1) cout << "Очередь заполнена.\n";
	}
	cout << "Очередь B:" << endl << B;
	Queue C;
	C = B;
	cout << "Очередь C:" << endl<< C;
	cout<<"Третий элемент C = " << C[2]<<endl<<"Изменим его на 5.\n"<<endl;
	C[2] = 5;
	cout << "Очередь C:"<<endl<< C;
	if (C <= B)
		cout << "C меньше либо равен B.\n";
	else cout << "C не меньше либо равен B.\n";
	cout << "Введите элемент, на который хотите поделить.\n";
	cin >> N;
	try
	{
		if (N == 0) throw 1;
		C = B / N;
	}
	catch (int a)
	{
		if (a == 1) cout << "Ошибка. Деление на ноль.\n";
	}
	cout << "Удалим все элементы 4 из очереди B.\n";
	C = B - 4;
	cout << "Очередь C:" << endl << C;
	system("pause");
	return 0;
}