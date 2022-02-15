/* Кендысь Алексей. Группа 9. C++, Лаб 6. Индивидуальное задание 5
Условие:
Использовать стандартные функции обработки строк на СИ! Данные для индивидуальных задач - считать из файла и записать в файл.
Разработать программу, которая запрашивает у пользователя 2 строки. Вторая строка содержит разделители. Разбивает первую строку на
лексемы и определяет  в ней целые числа  в 2-й с\с. Числа из 2-й с\с  переводит в 10-ю с\с  и заменяет их в первоначальной строке.
Формирует из найденных групп чисел  новые строки (числа в строках разделяются подчёркиванием). Сортирует одну из строк методом
«подсчёта». Результат выводит в файл.
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;
void Sort_Count(int *A, int n)
{
	int k;
	k = A[0];
	for (int i = 0; i < n; i++)
		if (A[i] > k)
			k = A[i];
	k++;
	int *C = new int[k];
	for (int i = 0; i < k; i++)
		C[i] = 0;
	for (int i = 0; i < n; i++)
	{
		C[A[i]] += 1;
	}
	int b = 0;
	for (int i = 0; i < k; i++)
	{
		for (int j = 0; j < C[i]; j++)
		{
			A[b] = i;
			b += 1;
		}
	}
}
void Sort_Str(char *str)
{
	char *str2 = new char[256];
	strcpy(str2, str);
	int x, n = 0;
	int *A = new int[256];
	x = strtol(str2, &str2, 0);
	A[n] = x;
	str2 += 1;
	while (strspn(str2, "0123456789") != NULL)
	{
		x = strtol(str2, &str2, 0);
		n++;
		A[n] = x;
		str2 += 1;
	}
	n++;
	/*int *B = new int[n];*/
	Sort_Count(A, n);
	char *h = new char[256];
	h = _itoa(A[0], h, 10);
	char *h2 = new char[256];
	for (int i = 1; i < n; i++)
	{
		strcat(h, "_");
		h2 = _itoa(A[i], h2, 10);
		strcat(h, h2);
	}
	strcpy(str, h);
}
void BinToDec(char *str)
{
	long a = atoi(str);
	int b = 1, c = 0;
	while (a)
	{
		c += a % 10 * b;
		b *= 2;
		a /= 10;
	}
	str = _itoa(c, str, 10);
}
int Dvoichnaya(char *str1, char *str2, char *str3)
{
	int b = 0;
	char *d = new char[256];
	int a = 0;
	while (strpbrk(str1, "01") != NULL)
	{
		char *p = new char[256];
		char *h = new char[256];
		str1 = strpbrk(str1, "01");
		strncpy(h, str1, strspn(str1, "01"));
		h[strspn(str1, "01")] = '\0';
		strcpy(p, h);
		BinToDec(p);
		if (a == 0)
		{
			strcpy(d, h);
			strcpy(str2, p);
		}
		else
		{
			strcat(d, h);
			strcat(str2, p);
		}
		a++;
		strcat(d, "_");
		strcat(str2, "_");
		str1 += strspn(str1, "01");
	}
	if (a != 0)
	{
		d[strlen(d) - 1] = '\0';
		str2[strlen(str2) - 1] = '\0';
		strcpy(str3, d);
		return b;
	}
	else
	{
		b++;
		return b;
	}
}
void DecStr1(char *str1, char *str2)
{
	char *p = new char[256];
	char *c = new char[256];
	char *h = new char[256];
	char *str3 = new char[256];
	int a = 0;
	strcpy(h, str1);
	strcpy(str3, str1);
	int len;
	int x;
	while (strpbrk(h, "01") != NULL)
	{
		h = strpbrk(h, "01");
		int len2 = strspn(h, "01");
		x = strtol(h, &h, 2);
		char *d = new char[256];
		d = _itoa(x, d, 10);
		len = strlen(str3) - strlen(h) - len2;
		strncpy(p, str3, len);
		p[len] = '\0';
		strcpy(str3, h);
		str3[strlen(h)] = '\0';
		strcat(p, d);
		if (a == 0)
			strcpy(c, p);
		else strcat(c, p);
		a++;
	}
	strcat(c, h);
	strcpy(str2, c);
}
int main()
{
	setlocale(LC_ALL, "Russian");
	printf("Введите две строки в файл input. Вторая строка должна содержать разделители.\n");
	FILE *f1, *f2;
	char str1[256];
	char str2[256];
	char *str3 = new char[256];
	char *str4 = new char[256];
	char *str5 = new char[256];
	char *str6 = new char[256];
	if (!(f1 = fopen("input.txt", "r")))
	{
		printf("Ошибка. Не удалось открыть файл input.\n");
		system("pause");
		return 0;
	}
	if (!(f2 = fopen("output.txt", "w")))
	{
		printf("Ошибка. Не удалось открыть файл output.\n");
		system("pause");
		return 0;
	}
	if (fgets(str1, 256, f1) == NULL || fgets(str2, 256, f1) == NULL || fgets(str3, 256, f1) != NULL)
	{
		printf("Ошибка. Не удалость прочитать строки. Нужно ввести две строки.\n");
		system("pause");
		return 0;
	}
	fputs("Введённая первая строка: ", f2);
	fputs(str1, f2);
	str1[strlen(str1) - 1] = '\0';
	char *p;
	char *h = new char[256];
	strcpy(h, str1);
	strcpy(str3, str1);
	int a = Dvoichnaya(str3, str4, str5);
	if (a == 1)
	{
		printf("Ошибка. В первой строке нет двоичных чисел.\n");
		system("pause");
		return 0;
	}
	DecStr1(str1, str6);
	fputs("Первая строка с двоичными числами, переведёнными в десятичные: ", f2);
	fputs(str6, f2);
	fputs("\n", f2);
	fputs("Все двоичные числа в строке: ", f2);
	fputs(str5, f2);
	fputs("\n", f2);
	fputs("Все двоичные числа в строке, переведённые в десятичные: ", f2);
	fputs(str4, f2);
	fputs("\n", f2);
	Sort_Str(str4);
	fputs("Отсортированные десятичные: ", f2);
	fputs(str4, f2);
	fputs("\n", f2);
	fputs("Разделители: ", f2);
	fputs(str2, f2);
	fputs("\n", f2);
	fputs("Все лексемы с двоичными числами, переведёнными в десятичные (последняя строка отсортирована):\n", f2);
	char *c = new char[256];
	p = strtok(h, str2);
	while (p != NULL)
	{
		char *d = new char[256];
		a = 0;
		while (strpbrk(p, "01") != NULL)
		{
			p = strpbrk(p, "01");
			/*if (strspn(p, "01") % 4 == 0)
			{*/
			strncpy(c, p, strspn(p, "01"));
			c[strspn(p, "01")] = '\0';

			BinToDec(c);
			if (a == 0)
				strcpy(d, c);
			else strcat(d, c);
			a++;
			strcat(d, "_");
			/*}*/
			p += strspn(p, "01");
		}
		p = strtok(NULL, str2);
		if (a != 0)
		{
			d[strlen(d) - 1] = '\0';
			if (p == NULL)
			{
				Sort_Str(d);
				fputs(d, f2);
				fputs(" (отсортированная)\n", f2);
			}
			else
			{
				fputs(d, f2);
				fputs("\n", f2);
			}
		}
	}
	printf("Результат был успешно записан в output.\n");
	fclose(f1);
	fclose(f2);
	system("pause");
	return 0;
}