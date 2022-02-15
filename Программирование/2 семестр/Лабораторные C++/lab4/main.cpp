/*������� �������. ������ 9. 1 ����, 2 �������. C++, ��� 4. �������������� ������� 5
�������:
������� ����������� �����. ������� ����� ��������(��������� ������������ ������), �������
�������� (��������� �� ������), ���������. ���������� ������������, ���������� � ������� ������.
������� public-����������� ����� - ���, ������� ������� �������������. ���������� ������������ ��
��������� � � ������ ������ ����������, �����������, ������� ������. ���������� �������
�������������� ��������� � ��������. ������������ ����������� ���������� ��� �������� ����������
��������� �������� ������� ����������� � ��������� ��� �������� ����������������� ������� �
����������� ������, � ������� �����������.
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	char* str = new char[256];
	Liquid L1;
	cout << "L1: ";
	L1.Show();
	cout << endl;
	strcpy(str, "�������� L2");
	Liquid L2(str);
	cout << "L2: ";
	L2.Show();
	cout << endl;
	strcpy(str, "�������� L3");
	Liquid L3(str, 31);
	cout << "L3: ";
	L3.Show();
	cout << endl;
	Juice J1;
	cout << "J1: ";
	J1.Show();
	cout << endl;
	strcpy(str, "��� J2");
	Juice J2(str);
	cout << "J2: ";
	J2.Show();
	cout << endl;
	strcpy(str, "��� J3");
	Juice J3(str, 10);
	cout << "J3: ";
	J3.Show();
	cout << endl;
	strcpy(str, "��� J4");
	Juice J4(str, 11, 24);
	cout << "J4: ";
	J4.Show();
	cout << endl;
	cout << "��������� ���� J3 = " << J3.GetDensity() << "." << endl;
	cout << "������� ������������� ���� J4 = " << J4.GetPercentage() << "." << endl;
	delete[] str;
	system("pause");
	return 0;
}