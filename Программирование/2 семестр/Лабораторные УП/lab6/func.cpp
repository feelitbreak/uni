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
	cout << "��� �� ������ �������?\n";
	cout << "0 - ������ ����������� �� �����.\n";
	cout << "1 - ����������� ��������� �����������.\n";
	cout << "2 - �������� ������� � �����������.\n";
	cout << "3 - ������� �������� �� ��������� ���������.\n";
	cout << "4 - ����� ���������, ������� ��������� �����.\n";
	cout << "5 - ����� �������� ���� �����������.\n";
	cout << "6 - ������ ��������.\n";
	cout << "E - ����� �� ���������.\n";
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
	cout << "�����������:\n";
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
	cout << "������� ���.\n";
	string str;
	cin >> str;
	cout << "������� �������.\n";
	double a;
	cin >> a;
	while (m.find(a) != m.end())
	{
		cout << "����� ������� ��� ����. ������� ������.\n";
		cin >> a;
	}
	while (a < 0)
	{
		cout << "������� �� ����� ���� �������������. ������� ��� ���.\n";
		cin >> a;
	}
	m[a] = str;
};
void DeleteElements(Map &m)
{
	double a, b;
	cout << "������� ��������.\n";
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
	cout << "������� �������.\n";
	double a;
	cin >> a;
	while (a < 0)
	{
		cout << "������� �� ����� ���� �������������. ������� ��� ���.\n";
		cin >> a;
	}
	Map::iterator i = m.upper_bound(a);
	if (i != m.end())
	{
		cout << "��������, ������� ��������� �����:\n";
		while (i != m.end())
		{
			cout << (*i).second << " - " << (*i).first << endl;
			i++;
		}
	}
	else cout << "����� ��������� ���.\n";
};
void Swap(Map& m)
{
	Map m2;
	cout << "������� ������ ����������� � ���� input2.txt\n";
	system("pause");
	if (!Input(m2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (m2.empty())
	{
		cout << "������. ������ ����������� �����.\n";
		return;
	}
	m.swap(m2);
};
void Replace(Map& m)
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	cout << "������� ���� (�������), ������� ������ ��������.\n";
	double a;
	cin >> a;
	while (m.find(a) == m.end())
	{
		cout << "������ �������� ���. ������� ������.\n";
		cin >> a;
	}
	string str;
	cout << "������� ����� ���.\n";
	cin >> str;
	m[a] = str;
};