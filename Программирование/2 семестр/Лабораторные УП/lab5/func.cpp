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
	cout << "��� �� ������ �������?\n";
	cout << "0 - ������ ������ �� �����.\n";
	cout << "1 - ����������� ��������� ������.\n";
	cout << "2 - ������� ��������� ��������� � �������� �������.\n";
	cout << "3 - �������� ��������� ��������� � �������� ������� c ������� list.\n";
	cout << "4 - ������� ������ ��������.\n";
	cout << "5 - ���������� ������� ��������� ������ ������� ������ ��������������� ��������� ������� �������.\n";
	cout << "6 - ����� � ������ ��� ���������� ������� ��������, ������������ ��������.\n";
	cout << "E - ����� �� ���������.\n";
};
bool Input(list<int>& l, const char str[])
{
	l.clear();
	filebuf fb;
	if (fb.open(str, std::ios::in))
	{
		istream is(&fb);
		copy(istream_iterator<int>(is), istream_iterator<int>(), back_inserter(l));
		fb.close();
		return true;
	}
	else return false;
};
void Output(list<int>& l)
{
	cout << "������:\n";
	copy(l.begin(), l.end(), ostream_iterator<int>(cout, " "));
	cout << endl;
};
void DeleteElements(list<int> &l)
{
	int a, b;
	cout << "������� �������.\n";
	cin >> a;
	while (a < 1 || a > l.size())
	{
		cout << "�� ������� ����� �������. ������� ��� ���.\n";
		cin >> a;
	}
	cout << "������� ���-�� ���������.\n";
	cin >> b;
	while (b < 1 || a+b-1 > l.size())
	{
		cout << "������. ������� ��� ���.\n";
		cin >> b;
	}
	list<int>::iterator i = l.begin();
	advance(i, a-1);
	list<int>::iterator j = i;
	advance(j, b);
	l.erase(i, j);
};
void AddElements(list<int> &l)
{
	list<int> l2;
	int a, b;
	cout << "������� �������.\n";
	cin >> a;
	while (a < 1 || a>l.size()+1)
	{
		cout << "�� ������� ����� �������. ������� ��� ���.\n";
		cin >> a;
	}
	cout << "������� ������ ������ � ���� input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "������. ������ ������ ����.\n";
		return;
	}
	Output(l2);
	cout << "������� ���-�� ���������.\n";
	cin >> b;
	while (b < 1 || b>l2.size())
	{
		cout << "������. ������� ��� ���.\n";
		cin >> b;
	}
	list<int>::iterator i = l.begin();
	advance(i, a - 1);
	list<int>::iterator j = l2.begin();
	list<int>::iterator k = l2.begin();
	advance(k, b);
	l.splice(i, l2, j, k);
};
void FindGreater(list<int>& l)
{
	list<int> l2;
	cout << "������� ������ ������ � ���� input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "������. ������ ������ ����.\n";
		return;
	}
	Output(l2);
	list<int>::iterator i = l.begin();
	list<int>::iterator j = i;
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	advance(j, min);
	list<int>::iterator k = l2.begin();
	int a = inner_product(i, j, k, 0, plus<int>(), greater<int>());
	cout << "��������� = " << a << endl;
	return;
};
void SearchPair(list<int>& l)
{
	list<int>::iterator i = adjacent_find(l.begin(), l.end());
	if (i != l.end())
		cout << "������ ������� ������� = " << *i << endl;
	else cout << "�� ������� ����� ������� ���������.\n";
};