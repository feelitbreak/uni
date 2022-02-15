/* Кендысь Алексей. Группа 9. C++, Лаб 4. Индивидуальное задание 5 на C++
Условие:
Разработать программу, запрашивающую у пользователя массив размерности N, выполняющую сортировку его элементов
по возрастанию с помощью функции qsort
Тесты:
n=10
Массив: 5.89038 1.54881 -0.397046 -1.26255 6.2743 4.34004 -8.37886 4.82284 -8.36299 7.28263
Результат: -8.37886 -8.36299 -1.26255 -0.397046 1.54881 4.34004 4.82284 5.89038 6.2743 7.28263

n=15
Массив: 5.94165 -7.88385 3.09183 0.60152 9.95788 6.65395 1.01352 -1.6306 -6.33839 9.71801 -6.29261 1.38585 1.03732 -0.867031 8.37397
Результат: -7.88385 -6.33839 -6.29261 -1.6306 -0.867031 0.60152 1.01352 1.03732 1.38585 3.09183 5.94165 6.65395 8.37397 9.71801 9.95788
*/
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <ctime>
using namespace std;
void Vvod(double *A, int n)
{
	srand((double)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (double)rand() / (double)RAND_MAX * 20 + -10;
}
void Vivod(double *A, int n)
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
int cmp(const void *j, const void *i)
{

	double r = *(double *)j - *(double *)i;
	if (r == 0) return 0;
	return r < 0 ? -1 : 1;
}
int main()
{
	setlocale(LC_ALL, "rus");
	int n, a, b;
	double c;
	cout << "Введите размерность массива\n";
	cin >> n;
	if (n < 1)
	{
		cout << "Ошибка формата размерности\n";
		system("pause");
		return 0;
	}
	double *A = new double[n];
	Vvod(A, n);
	cout << "Массив:\n";
	Vivod(A, n);
	qsort(A, n, sizeof(double), cmp);
	cout << "Результат:\n";
	Vivod(A, n);
	delete A;
	system("pause");
	return 0;
}