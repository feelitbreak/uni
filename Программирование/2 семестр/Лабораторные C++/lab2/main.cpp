/* ������� �������. ������ 9. 1 ����, 2 �������. C++, ��� 2. �������������� ������� 5
�������:
������� ���������� ������������� ������� � ������. ������� ������ ���������. ��������� �������� ���������� � 
�������, ����: ��������, ����. (char*, double).
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
int main()
{
	setlocale(LC_ALL, "rus");
	char a;
	FILE* f;
	cout << "������� �������� ����� � ���� input.txt\n";
	system("pause");
	if (!(f = fopen("input.txt", "r")))
	{
		printf("������. �� ������� ������� ���� input.\n");
		system("pause");
		return 0;
	}
	List A(f);
	cout << "������:\n";
	A.OutputConsole();
	A.Menu();
	cin >> a;
	while (a != '1' && a != '2' && a != 'E')
	{
		cout << "�������� ������ �����. ������� ��� ���.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '1' && A.Is_Empty() == NULL)
		{
			A.DeleteElement(A.Search());
			if (A.Is_Empty() == NULL)
			{
				cout << "���������:\n";
				A.OutputConsole();
			}
		}
		if (a == '2' && A.Is_Empty() == NULL)
			A.OutputConsole();
		if ((a == '1' || a == '2') && A.Is_Empty() != NULL)
			cout << "������ ����.\n";
		A.Menu();
		cin >> a;
		while (a != '1' && a != '2' && a != 'E')
		{
			cout << "�������� ������ �����. ������� ��� ���.\n";
			cin >> a;
		}
	}
	cout << "�� ������� ����� �� ���������.\n";
	system("pause");
	return 0;
}