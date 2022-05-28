#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <algorithm>
#include <list>
#include <iterator>
#include <fstream>
#include <numeric>
using namespace std;
void Menu()
{
	cout << "Что вы хотите сделать?\n";
	cout << "0 - Ввести список из файла.\n";
	cout << "1 - Вывести список на консоль.\n";
	cout << "2 - Перемножить поэлементно два списка, transform.\n";
	cout << "3 - Добавить несколько элементов в заданную позицию c другого list.\n";
	cout << "4 - Подсчитать сколько элементов одного массива больше соответствующих элементов другого массива.\n";
	cout << "E - Выйти из программы.\n";
};
bool Input(list<double>& l, const char str[])
{
	l.clear();
	filebuf fb;
	if (fb.open(str, std::ios::in))
	{
		istream is(&fb);
		copy(istream_iterator<double>(is), istream_iterator<double>(), back_inserter(l));
		fb.close();
		return true;
	}
	else return false;
};
void Output(list<double>& l)
{
	copy(l.begin(), l.end(), ostream_iterator<double>(cout, " "));
	cout << endl;
};
void AddElements(list<double>& l)
{
	list<double> l2;
	int a, b;
	cout << "Введите позицию.\n";
	cin >> a;
	while (a < 1 || a>l.size() + 1)
	{
		cout << "Не удалось найти позицию. Введите ещё раз.\n";
		cin >> a;
	}
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	cout << "Второй список:\n";
	Output(l2);
	cout << "Введите кол-во элементов.\n";
	cin >> b;
	while (b < 1 || b>l2.size())
	{
		cout << "Ошибка. Введите ещё раз.\n";
		cin >> b;
	}
	list<double>::iterator i = l.begin();
	advance(i, a - 1);
	list<double>::iterator j = l2.begin();
	list<double>::iterator k = l2.begin();
	advance(k, b);
	l.splice(i, l2, j, k);
};
void FindGreater(list<double>& l)
{
	list<double> l2;
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	cout << "Второй список:\n";
	Output(l2);
	list<double>::iterator i = l.begin();
	list<double>::iterator j = i;
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	advance(j, min);
	list<double>::iterator k = l2.begin();
	int a = inner_product(i, j, k, 0, plus<int>(), greater<int>());
	cout << "Результат = " << a << endl;
	return;
};
void Multiply(list<double>& l)
{
	list<double> l2;
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	cout << "Второй список:\n";
	Output(l2);
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	list<double>::iterator i = l.begin();
	advance(i, min);
	transform(l.begin(), i, l2.begin(), l.begin(), multiplies<double>());
};