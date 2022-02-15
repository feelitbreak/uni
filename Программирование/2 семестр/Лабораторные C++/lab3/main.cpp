/* ������� �������. ������ 9. 1 ����, 2 �������. C++, ��� 3. �������������� ������� 5
�������:
����� ���� (������������ ������, ���: �������� char).  �������� ++ - �������� �������, �������� -- (������� �������) +( ����������� ���� c�����). 
�������� +N(-N, *N, /N) � ���������� ����� N �  ��������  �������� ����� (���������� � ����������, ����������, ��������).
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	cout << "������� ����������� �����.\n";
	int N;
	cin >> N;
	while (N < 1)
	{
		cout << "������. ������� ��� ���.\n";
		cin >> N;
	}
	Stack A(N);
	cout << "������� �������� �����.\n";
	cin >> A;
	cout << "���� A: ";
	cout << A<<endl;
	Stack B; 
	B = A;
	cout << "B=A\n���������:\n���� A: " << A << "���� B: " << B<<endl;
	cout << "B=++A\n";
	B = ++A;
	cout << "���������:\n���� A: " << A << "���� B: " << B << endl;
	cout << "B=A++\n";
	B = A++;
	cout << "���������:\n���� A: " << A << "���� B: " << B << endl;
	cout << "B=--A\n";
	B = --A;
	cout << "���������:\n���� A: " << A << "���� B: " << B << endl;
	cout << "B=A--\n";
	B = A--;
	cout << "���������:\n���� A: " << A << "���� B: " << B << endl;
	Stack C = A + B;
	cout << "C=A+B\n���������:\n���� A: " << A << "���� B: " << B << "���� C: " << C << endl;
	cout << "C=A+3\n";
	C = A + 3;
	cout << "���������:\n���� A: " << A << "���� C: " << C << endl;
	cout << "C=C-5\n";
	C = C - 5;
	cout << "���������:\n���� C: " << C << endl;
	cout << "C=C*2\n";
	C = C * 2;
	cout << "���������:\n���� C: " << C << endl;
	cout << "C=C/N" << endl << "������� ����� N.\n";
	cin >> N;
	try 
	{
		if (N == 0) throw 1;
		C = C / N;
		cout << "���������:\n���� C: " << C << endl;
	}
	catch (int a)
	{
		if (a == 1)
			cout << "������. ������� �� ����.\n\n";
	}
	cout << "A>=B (��������� �������� ��������)\n";
	cout << "���������:\n���� A: " << A << "���� B: " << B;
	if (A >= B)
		cout << "��.\n";
	else cout << "���.\n";
	cout << "\nA<=B (��������� �������� ��������)\n";
	cout << "���������:\n���� A: " << A << "���� B: " << B;
	if (A <= B)
		cout << "��.\n";
	else cout << "���.\n";
	cout << "\nA==B (��������� �������� ��������)\n";
	cout << "���������:\n���� A: " << A << "���� B: " << B;
	if (A == B)
		cout << "��.\n";
	else cout << "���.\n";
	cout << "\nA!=B (��������� �������� ��������)\n";
	cout << "���������:\n���� A: " << A << "���� B: " << B;
	if (A != B)
		cout << "��.\n";
	else cout << "���.\n";
	cout << "\nA[N]=n\n";
	cout << "������� N.\n";
	cin >> N;
	try
	{
		if (N < 0 || N>=A.Size()) throw 2;
		cout << "A[N] == " << A[N];
		A[N] = '3';
		cout << "\n������ n == '3'\n���������: \nA[N] == " << A[N] << endl;
	}
	catch (int a)
	{
		if (a==2)
			cout << "������. ������ �������� �� ����������.\n";
	}
	system("pause");
	return 0;
}