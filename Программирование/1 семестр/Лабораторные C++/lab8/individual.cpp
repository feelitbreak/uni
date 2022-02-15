/* Кендысь Алексей. Группа 9. C++, Лаб 8. Индивидуальное задание 5
Условие:
Запустить 3ую лабораторную с помощью командной строки. В последовательности A=(ai), i=1..n , n<=100, продублировать 
неповторяющиеся элементы.
Тесты:
n=10    Последовательность: 8 9 7 4 9 6 3 7 6 4
Результат: 8 8 9 7 4 9 6 3 3 7 6 4
n=20    Последовательность: 89 95 97 51 2 77 47 86 68 94 71 84 5 62 26 65 51 24 0 77
Результат: 89 89 95 95 97 97 51 2 2 77 47 47 86 86 68 68 94 94 71 71 84 84 5 5 62 62 26 26 65 65 51 24 24 0 0 77
n=40    Последовательность: 90 16 30 17 74 2 29 44 39 12 86 82 82 10 55 90 8 50 16 46 23 15 92 75 72 80 24 69 89 69 2 85 24 31 16 13 64 11 43 47
Результат:  90 16 30 30 17 17 74 74 2 29 29 44 44 39 39 12 12 86 86 82 82 10 10 55 55 90 8 8 50 50 16 46 46 23 23 15 15 92 92 75 75 72 72 80 80 24 69 89 89 69 2 85 85 24 31 31 16 13 13 64 64 11 11 43 43 47 47
n=101   Ошибка. Количество элементов должно быть натуральным числом и не превышать 100
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctime>
using namespace std;
void Double(int A[], int a, int n)
{
	int i = n;
	for (i; i > a; i--)
		A[i] = A[i - 1];
}
int main(int argc, char *argv[])
{

	int n = atoi(argv[1]);
	if (n < 1 || n>100)
	{
		cout << "Error. Invalid format of the number of elements.\n";
		system("pause");
		return 0;
	}
	int A[200];
	/*cout << "Введите элементы последовательности" << endl;
	for (int i = 0; i < n; i++)
	cin >> A[i];*/
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = rand() % 200;
	cout << "Your sequence: ";
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
		{
			if (j == i)
				if (j + 1 < n)
					j++;
				else break;
			while (j < n && i < n)
			{
				if (j == i)
					if (j + 1 < n)
						j++;
					else break;
				if (A[i] == A[j])
				{
					i++;
					j = -1;
				}
				j++;
			}
			if (i < n)
			{
				Double(A, i, n);
				n++;
			}
		}
	cout << "Result: ";
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
	system("pause");
	return 0;
}