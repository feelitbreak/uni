/* ������� �������. ������ 9. �������� ����������� ������ �� ��. ������� 3
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
#include <algorithm>
#include <list>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	list<double> l;
	char a;
	Menu();
	cin >> a;
	while (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != 'E')
	{
		cout << "�������� ������ �����. ������� ��� ���.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '0')
		{
			cout << "������� ������ � ���� input.txt\n";
			system("pause");
			if (!Input(l, "input.txt"))
				cout << "�� ������� ������� ���� input.txt\n";
		}
		if (a == '1' && !l.empty())
			Output(l);
		if (a == '2' && !l.empty())
			Multiply(l);
		if (a == '3')
			AddElements(l);
		if (a == '4' && !l.empty())
			FindGreater(l);
		if ((a == '1' || a == '2' || a == '4') && l.empty())
			cout << "������ ����.\n";
		Menu();
		cin >> a;
		while (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != 'E')
		{
			cout << "�������� ������ �����. ������� ��� ���.\n";
			cin >> a;
		}
	}
	cout << "�� ������� ����� �� ���������.\n";
	system("pause");
	return 0;
}