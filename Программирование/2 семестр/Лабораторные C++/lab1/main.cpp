/* ������� �������. ������ 9. 1 ����, 2 �������. C++, ��� 1. �������������� ������� 5
�������:
����. ������� ������ ���������. ��������� �������� ���������� � �������, ����: ��������, ���� (char*, double).
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
int main()
{
	setlocale(LC_ALL, "rus");
	Stack A;
	char a;
	A.Menu();
	cin >> a;
	while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != 'E')
	{
		cout << "�������� ������ �����. ������� ��� ���.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '1')
		{
			cout << "������� �������� ����� � ���� input.txt\n";
			system("pause");
			A.Input();
			cout << "���������:\n";
			A.OutputConsole();
		}
		if (a == '2')
		{
			A.AddElement();
			cout << "���������:\n";
			A.OutputConsole();
		}
		if (a == '3' && A.Is_Empty() == NULL)
		{
			A.Pop();
			if (A.Is_Empty() == NULL)
			{
				cout << "���������:\n";
				A.OutputConsole();
			}
		}
		if (a == '4' && A.Is_Empty() == NULL)
			if (A.OutputFile() != 0)
				cout << "���� ��� ������� ������� � ����.\n";
		if (a == '5' && A.Is_Empty() == NULL)
			A.OutputConsole();
		if ((a == '6' || a == '3' || a == '4' || a == '5') && A.Is_Empty() != NULL)
			cout << "���� ����.\n";
		if (a == '6' && A.Is_Empty() == NULL)
		{
			A.Delete();
			cout << "���� ��� ������� �����.\n";
		}
		A.Menu();
		cin >> a;
		while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != 'E')
		{
			cout << "�������� ������ �����. ������� ��� ���.\n";
			cin >> a;
		}
	}
	if (!A.Is_Empty())
		A.Delete();
	cout << "�� ������� ����� �� ���������.\n";
	system("pause");
	return 0;
}