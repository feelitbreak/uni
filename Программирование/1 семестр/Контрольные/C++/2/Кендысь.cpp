// Кендысь Алексей. Группа 9. Контрольная работа, Вариант 1.
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iomanip>
using namespace std;
struct Massiv1
{
	int Celoe;
	double Deystv;
};
Massiv1* Vvod1()
{
	cout << "Введите элементы массива структуры, размерности 2." << endl;
	Massiv1* m = new Massiv1[2];
	for (int i = 0; i < 2; i++)
	{

		cout << "Введите целое число: " << endl;
		cin >> m[i].Celoe;
		cout << "Введите дейсвительное число: " << endl;
		cin >> m[i].Deystv;
	}
	return m;
}
void Vivod(Massiv1* m)
{
	cout << "Полученный массив структур:" << endl;
	cout << "Первый элемент: ";
	cout << "целое число = " << m[0].Celoe << ", действительное = " << m[0].Deystv << endl;
	cout << "Второй элемент: ";
	cout << "целое число = " << m[1].Celoe << ", действительное = " << m[1].Deystv << endl;
}
int Massiv(char **A, int n, double* B)
{
	int n2 = 0;
	for (int i = 0; i < n; i++)
		if (strspn(A[i], "0123456789,") == strlen(A[i]))
		{
			B[n2] = atof(A[i]);
			n2++;
		}
	return n2;
}
double Summa(double* B, int n2)
{
	double a = 0;
	for (int i = 0; i < n2; i++)
		a += B[i];
	return a;
}
void Podstroki(char* str)
{
	char* p = new char[256];
	p = strpbrk(str, "uvwx");
	if (p == NULL)
	{
		cout << "Не удалось найти нужные подстроки.\n";
		return;
	}
	else {
		cout << "Нужные подстроки:\n";
		cout << p << endl;
	}
	while (strpbrk(p + 1, "uvwx") != NULL)
	{
		p = strpbrk(p + 1, "uvwx");
		cout << p << endl;
	}
	return;
}
void Vvod(unsigned int **A, int n)
{
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			if (i == j || j == n - i - 1)
				A[i][j] = 1;
			else A[i][j] = 0;
}
void Vivod(unsigned int **A, int n)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
			cout << setw(5) << A[i][j];
		cout << endl;
	}
}
void Solve()
{
	FILE *f1, *f2;
	char str1[256];
	char str2[] = { "?! _;" };
	char *str3 = new char[256];
	char *str4 = new char[256];
	char *str5 = new char[256];
	char *str6 = new char[256];
	if (!(f1 = fopen("input.txt", "r")))
	{
		printf("Ошибка. Не удалось открыть файл input.\n");
		return;
	}
	if (!(f2 = fopen("output.txt", "w")))
	{
		printf("Ошибка. Не удалось открыть файл output.\n");
		return;
	}
	if (fgets(str1, 256, f1) == NULL)
	{
		printf("Ошибка. Не удалость прочитать строку.\n");
		return;
	}
	cout << "Введённая строка:\n";
	cout << str1 << endl;
	char *p;
	char *h = new char[256];
	char *c = new char[256];
	strcpy(c, str1);
	p = strtok(c, str2);
	int n = 0;
	char **A;
	A = new char *[256];
	for (int i = 0; i < 256; i++)
		A[i] = new char[256];
	fputs("Лексемы:\n", f2);
	while (p != NULL)
	{
		strcpy(A[n], p);
		fputs(A[n], f2);
		n++;
		fputs("\n", f2);
		p = strtok(NULL, str2);
	}
	cout << "Лексемы были успешно записаны в файл output.txt\n";
	double* B = new double[256];
	int m = Massiv(A, n, B);
	cout << "Полученные лексемы:\n";
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; A[i][j] != '\0'; j++)
			cout << A[i][j];
		cout << endl;
	}
	cout << "Полученный массив из действителньых чисел:\n";
	for (int i = 0; i < m; i++)
		cout << B[i] << endl;
	double a = Summa(B, m);
	cout << "Сумма чисел массива = " << a << endl;
	Podstroki(str1);
	for (int i = 0; i < 256; i++)
		delete (A[i]);
	delete (A);
	delete (B);
	cout << "Введите размерность матрицы.\n";
	int N;
	cin >> N;
	unsigned int **C;
	C = new unsigned int *[N];
	for (int i = 0; i < N; i++)
		C[i] = new unsigned int[N];
	Vvod(C, N);
	cout << "Полученная матрица:\n";
	Vivod(C, N);
	Massiv1* r = Vvod1();
	Vivod(r);
	delete (r);
	for (int i = 0; i < N; i++)
		delete (C[i]);
	delete (C);
	fclose(f1);
	fclose(f2);
}
int main()
{
	setlocale(LC_ALL, "Russian");
	Solve();
	system("pause");
	return 0;
}