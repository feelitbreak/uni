/* ������� �������. ������ 9. ����������� ������. ������� 1
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
	cout << "������� ����������� �������.\n";
	int N;
	cin >> N;
	while (N <= 0)
	{
		cout << "������ �����. ������� ��� ���.\n";
		cin >> N;
	}
	Queue A(N);
	Queue B(6);
	fin.open("input1.txt");
	if (!fin.is_open())
	{
		cerr << "�� ������� ������� ���� input1.txt\n";
		system("pause");
		return 0;
	}
	fin >> A;
	cout << "������� A:" << endl << A;
	try 
	{
		A + 15;
	}
	catch (int a)
	{
		if (a == 1) cout << "������� ���������.\n";
	}
	cout << "������� A:" << endl << A;
	cout << "������� ������� B � �������.\n";
	cin >> B;
	try
	{
		B + 3;
	}
	catch (int a)
	{
		if (a == 1) cout << "������� ���������.\n";
	}
	cout << "������� B:" << endl << B;
	Queue C;
	C = B;
	cout << "������� C:" << endl<< C;
	cout<<"������ ������� C = " << C[2]<<endl<<"������� ��� �� 5.\n"<<endl;
	C[2] = 5;
	cout << "������� C:"<<endl<< C;
	if (C <= B)
		cout << "C ������ ���� ����� B.\n";
	else cout << "C �� ������ ���� ����� B.\n";
	cout << "������� �������, �� ������� ������ ��������.\n";
	cin >> N;
	try
	{
		if (N == 0) throw 1;
		C = B / N;
	}
	catch (int a)
	{
		if (a == 1) cout << "������. ������� �� ����.\n";
	}
	cout << "������ ��� �������� 4 �� ������� B.\n";
	C = B - 4;
	cout << "������� C:" << endl << C;
	system("pause");
	return 0;
}