#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <algorithm>
#include <iterator>
#include <fstream>
#include <numeric>
#include <map>
#include <string>
#include <Windows.h>
using namespace std;
typedef map<double, string, Sort> Map;
void Menu()
{
	cout << "Что вы хотите сделать?\n";
	cout << "0 - Ввести отображение из файла.\n";
	cout << "1 - Просмотреть состояние отображения.\n";
	cout << "2 - Добавить элемент в отображение.\n";
	cout << "3 - Удалить элементы из заданного диапазона.\n";
	cout << "4 - Поиск элементов, больших заданного ключа.\n";
	cout << "5 - Обмен значений двух отображений.\n";
	cout << "6 - Замена элемента.\n";
	cout << "E - Выйти из программы.\n";
};
bool Input(Map& m, const char str[])
{
	m.clear();
	ifstream fin;
	fin.open(str);
	if (!fin.is_open())
		return false;
	string str2;
	double a;
	while (!fin.eof())
	{
		fin >> str2;
		fin >> a;
		m[a] = str2;
	}
	fin.close();
	return true;
};
void Output(Map& m)
{
	cout << "Отображение:\n";
	Map::iterator i = m.begin();
	while (i != m.end())
	{
		cout << (*i).second << " - " << (*i).first << endl;
		i++;
	}
};
void AddElement(Map& m)
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	cout << "Введите имя.\n";
	string str;
	cin >> str;
	cout << "Введите возраст.\n";
	double a;
	cin >> a;
	while (m.find(a) != m.end())
	{
		cout << "Такой возраст уже есть. Введите другой.\n";
		cin >> a;
	}
	while (a < 0)
	{
		cout << "Возраст не может быть отрицательным. Введите ещё раз.\n";
		cin >> a;
	}
	m[a] = str;
};
void DeleteElements(Map &m)
{
	double a, b;
	cout << "Введите диапазон.\n";
	cin >> a;
	cin >> b;
	a = (a > b) ? b : a;
	Map::iterator i = m.lower_bound(a);
	Map::iterator j = m.upper_bound(b);
	if (i != m.end())
		m.erase(i, j);
};
void FindGreater(Map& m)
{
	cout << "Введите возраст.\n";
	double a;
	cin >> a;
	while (a < 0)
	{
		cout << "Возраст не может быть отрицательным. Введите ещё раз.\n";
		cin >> a;
	}
	Map::iterator i = m.upper_bound(a);
	if (i != m.end())
	{
		cout << "Элементы, большие заданного ключа:\n";
		while (i != m.end())
		{
			cout << (*i).second << " - " << (*i).first << endl;
			i++;
		}
	}
	else cout << "Таких элементов нет.\n";
};
void Swap(Map& m)
{
	Map m2;
	cout << "Введите второе отображение в файл input2.txt\n";
	system("pause");
	if (!Input(m2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (m2.empty())
	{
		cout << "Ошибка. Второе отображение пусто.\n";
		return;
	}
	m.swap(m2);
};
void Replace(Map& m)
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	cout << "Введите ключ (возраст), который хотите изменить.\n";
	double a;
	cin >> a;
	while (m.find(a) == m.end())
	{
		cout << "Такого возраста нет. Введите другой.\n";
		cin >> a;
	}
	string str;
	cout << "Введите новое имя.\n";
	cin >> str;
	m[a] = str;
};