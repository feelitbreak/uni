/* Кендысь Алексей. Группа 9. C++, Лаб 9. Индивидуальное задание 5
Условие:
Вычисление произведения каждых двух соседних элементов (пары) массива (1,2,3,4,5,6 итог: 2 ,6,12,20,30).
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
	cout << "Введите массив в файл input.txt, максимальное количество элементов - 200." << endl;
	system("pause");
	fin.open("input.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input.txt\n";
		return;
	}
	fout.open("output.txt");
	if (!fout.is_open())
	{
		cerr << "Не удалось открыть файл output.txt\n";
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
	cout << "Массив: ";
	Vivod1(A, n);
	A = Multiply(A, n);
	n--;
	fout << "Результат:\n";
	for (int i = 0; i < n; i++)
	{
		fout << A[i] << " ";
	}
	cout << "Результат был успешно записан в файл output.txt\n";
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