/* ������� �������. ������ 9. C++, ��� 4. �������������� ������� 5 �� ��
�������:
����������� ���������, ������������� � ������������ ������ ����������� N, ����������� ���������� ��� ���������
�� �����������. ���������� ������������ ����������� ����������. ����� �������� ������� � ������ �� �������(�������),
��� ���� ��������� ������ �������. ������� ������� ��  �������, ��� ���� ��������� ������ �������. ���������
���������� �� �������� � ������� ����������� ������� qsort
�����:
����������:
n=10
������: -2,904447 -1,318094 -7,144078 4,304636 5,732292 -8,923307 5,327006 4,212470 -4,819178 -8,187811
������� = 5
����� ������� = -6,99845
���������: -2,904447 -1,318094 -7,144078 4,304636 -6,998450 5,732292 -8,923307 5,327006 4,212470 -4,819178 -8,187811

��������:
n=20
������: -2,657247 -7,840510 4,881436 5,502792 6,376232 8,266549 0,888394 2,665792 4,837489 6,505020 -5,122532 5,875118 5,814081 -6,636250 0,126041 7,063509 8,202460 -1,935789 1,249123 5,560778
������� = 8
���������: -2,657247 -7,840510 4,881436 5,502792 6,376232 8,266549 0,888394 4,837489 6,505020 -5,122532 5,875118 5,814081 -6,636250 0,126041 7,063509 8,202460 -1,935789 1,249123 5,560780

���������� �� �������� � qsort:
n=10
������: -2,396008 -8,439894 -6,770531 9,510483 -8,911710 2,232429 9,747917 8,612629 5,038911 -5,070650
���������: 9,747917 9,510483 8,612629 5,038911 2,232429 -2,396008 -5,070650 -6,770531 -8,439894 -8,911710

���������� �� ����������� ���������:
n=15
������: -2,278817 -1,381573 -3,468429 -3,952452 2,524186 3,636891 1,829585 3,199255 -0,366527 -6,467177 -2,274545 -9,017304 -6,811426 -7,811823 -6,320078
���������: -9,017304 -7,811823 -6,811426 -6,467177 -6,320078 -3,952452 -3,468429 -2,278817 -2,274545 -1,381573 -0,366527 1,829585 2,524186 3,199255 3,636891
*/
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <ctime>
void Vvod(double *A, int n)
{
	srand((double)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (double)rand() / (double)RAND_MAX * 20 + -10;
}
void Vivod(double *A, int n)
{
	for (int i = 0; i < n; i++)
	{
		printf("%lf", A[i]);
		printf(" ");
	}
	printf("\n");
}
void Dobavlenie(double *A, int b, double c, int n)
{
	int i = n;
	A = (double *)realloc(A, (n + 1) * sizeof(double));
	for (i; i > b; i--)
		A[i] = A[i - 1];
	A[b] = c;
}
void Udalenie(double *A, int b, int n)
{
	A = (double *)realloc(A, (n - 1) * sizeof(double));
	for (int i = b; i < n - 1; i++)
		A[i] = A[i + 1];
	A = (double *)realloc(A, (n - 1) * sizeof(double));
}
int cmp1(const void *j, const void *i)
{

	double r = *(double *)j - *(double *)i;
	if (r == 0) return 0;
	return r < 0 ? -1 : 1;
}
int cmp2(const void *j, const void *i)
{

	double r = *(double *)j - *(double *)i;
	if (r == 0) return 0;
	return r < 0 ? 1 : -1;
}
void SortInsert(double *A, int n)
{
	double x;
	int i, j;
	for (i = 0; i < n; i++)
	{
		x = A[i];
		for (j = i - 1; j >= 0 && A[j] > x; j--)
			A[j + 1] = A[j];
		A[j + 1] = x;
	}
}
int main()
{
	setlocale(LC_ALL, "rus");
	int n, a, b;
	double c;
	printf("������� ����������� �������\n");
	scanf_s("%d", &n);
	if (n < 1)
	{
		printf("������ ������� �����������\n");
		system("pause");
		return 0;
	}
	double *A = (double*)malloc(n * sizeof(double));
	Vvod(A, n);
	do
	{
		printf("������:\n");
		void(*ptr)(double*, int);
		ptr = Vivod;
		ptr(A, n);
		printf("��� �� ������ ������� � ��������?\n");
		printf("������� 1, ����� �������� �������, 2-������� �������, 3-������� ���������� �� ��������, 4-������� ���������� �� �����������, 5-��������� ����� ��������, 6-����� �� ���������\n");
		scanf_s("%d", &a);
		if (a == 1)
		{
			printf("������� ������� ��������\n");
			scanf_s("%d", &b);
			b--;
			while (b > n || b < 0)
			{
				printf("������, ������� ������ ������� ��������\n");
				scanf_s("%d", &b);
				b--;
			}
			printf("������� �������, ������� �� ������ ��������\n");
			scanf_s("%lf", &c);
			Dobavlenie(A, b, c, n);
			n++;
		}
		if (a == 2)
		{
			printf("������� ������� ��������\n");
			scanf_s("%d", &b);
			b--;
			while (b > n || b < 0)
			{
				printf("������, ������� ������ ������� ��������\n");
				scanf_s("%d", &b);
				b--;
			}
			Udalenie(A, b, n);
			n--;
		}
		if (a == 3)
			qsort(A, n, sizeof(double), cmp2);
		if (a == 4)
			SortInsert(A, n);
		if (a == 5)
		{
			SortInsert(A, n);
			printf("��������������� ������:\n");
			Vivod(A, n);
			printf("������� �������, ������� �� ������ �����\n");
			scanf_s("%lf", &c);
			double * p = (double *)bsearch((const void*)&c, A, n, sizeof(double), cmp1);
			if (p == 0)
				printf("������� �� ��� ������\n");
			else
			{
				printf("������� ��� ������� ������. ������� ��������: ");
				printf("%d", p);
				printf("\n");
			}
			free(A);
			system("pause");
			return 0;
		}
		if (a != 0 && a != 1 && a != 2 && a != 3 && a != 4 && a != 5 && a != 6)
			printf("������, �������� ������\n");
	} while (a != 6);
	printf("�� ����� �� ���������!\n");
	free(A);
	system("pause");
	return 0;
}