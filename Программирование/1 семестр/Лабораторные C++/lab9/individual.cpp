/* ������� �������. ������ 9. C++, ��� 9. �������������� ������� 5
�������:
���������� ������������ ������ ���� �������� ��������� (����) ������� (1,2,3,4,5,6 ����: 2 ,6,12,20,30).
*/
#include <iostream>
#include <fstream>
#include <stdlib.h>
using namespace std;
ifstream fin;
ofstream fout;
void Vivod1(double *A, int n)
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
double* Multiply(double* A, int n, int b=0)
{
	A[b] *= A[b + 1];
	if (b + 2 == n)
		return A;
	else return Multiply(A, n, b+1);
}
void Solve()
{
	cout << "������� ������ � ���� input.txt, ������������ ���������� ��������� - 200." << endl;
	system("pause");
	fin.open("input.txt");
	if (!fin.is_open())
	{
		cerr << "�� ������� ������� ���� input.txt\n";
		return;
	}
	fout.open("output.txt");
	if (!fout.is_open())
	{
		cerr << "�� ������� ������� ���� output.txt\n";
		return;
	}
	int n = 0;
	double a;
	double *A = new double[200];
	while (!fin.eof())
	{
		fin >> a;
		A[n] = a;
		n++;
	}
	cout << "������: ";
	Vivod1(A, n);
	A = Multiply(A, n);
	n--;
	fout << "���������:\n";
	for (int i = 0; i < n; i++)
	{
		fout << A[i] << " ";
	}
	cout << "��������� ��� ������� ������� � ���� output.txt\n";
	fin.close();
	fout.close();
}
int main()
{
	setlocale(LC_ALL, "rus");
	Solve();
	system("pause");
	return 0;
}