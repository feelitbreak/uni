//������� �������, ������ 9. ����������� ������. ������� 2
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
#include <fstream>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	DeQueue<double> A;
	A.ReadFile("input2.txt");
	cout << "��������� ����� ������� �� �����:\n";
	cout << A;
	A.OutputFile("output.txt");
	cout << "������� ����� ���� �������� � ���� output.txt\n";
	DeQueue<double> B(A);
	cout << "������� ���-�� ������ ���������, ������� ������ �������.\n";
	int K;
	cin >> K;
	while (K < 0)
	{
		cout << "�������� ������ �����. ������� ��� ���.\n";
		cin >> K;
	}
	A - K;
	cout << "��������� �������� ������ K ���������:\n";
	cout << A;
	if (A.IsEmpty())
		cout << "������� �����.\n";
	cout << "��������� ��������� ����������� ������� � ����������:\n";
	if (A < B)
		cout << "� ���������� ������� ������ ������������� ���������.\n";
	else cout << "� ���������� ������� �� ������ ������������� ���������.\n";
	DeQueue<Elem> C;
	cout << "������� ��������, ���� �� �����:\n";
	C.ReadFile("input22.txt");
	cout << C;
	cout << "������� ����� � �������.\n";
	double a;
	cin >> a;
	string s;
	cin >> s;
	Elem x(a, s);
	cout << "���������� ���������:\n" << x << endl;
	cout << "������� �����, � ������� ������ �������� ������� �����.\n";
	int k;
	cin >> k;
	if (x > k)
		cout << "������� ����� ����� ��������� ������ ��������� �����.\n";
	else cout << "������� ����� ����� ��������� �� ������ ��������� �����.\n";
	system("pause");
	return 0;
}