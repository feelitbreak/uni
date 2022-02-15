#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
struct Products
{
	char* Name;
	double Price;
	Products* next, *pred;
};
Products* InsertItem(char* newname, double newprice, Products* where)
{
	Products* h;
	h = new Products[1];
	h->Name = new char[strlen(newname) + 1];
	strcpy(h->Name, newname);
	h->Price = newprice;
	h->next = (where) ? where->next : NULL;
	h->pred = where;
	if (h->next) h->next->pred = h;
	if (h->pred) where->next = h;
	return h;
};
Products* AddElement1(Products* head, char *newname, double newprice, int i)
{
	Products* m = head;
	if (i == 1)
	{
		Products* h;
		h = new Products[1];
		h->Name = new char[strlen(newname) + 1];
		strcpy(h->Name, newname);
		h->Price = newprice;
		h->next = head;
		h->pred = 0;
		if (head) head->pred = h;
		head = h;
		return head;
	}
	i -= 2;
	if (i >= 0 && head == 0)
	{
		cout << "Такого товара не существует. Список не был изменён.\n";
		return head;
	}
	while (i > 0)
	{
		m = m->next;
		i--;
		if (!m) {
			cout << "Такого товара не существует. Список не был изменён.\n";
			return head;
		}
	}
	InsertItem(newname, newprice, m);
	return head;
};
Products* AddElement2(Products* head)
{
	cout << "Введите название нового товара и его цену.\n";
	char newname[256];
	Products* m = head;
	double newprice;
	cout << "Товар: ";
	cin >> newname;
	cout << "Его цена: ";
	cin >> newprice;
	cout << "Введите позицию для нового товара.\n";
	int i;
	cin >> i;
	while (i <= 0)
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> i;
	}
	head = AddElement1(head, newname, newprice, i);
	return head;
};
Products* DeleteElement1(Products* head, int i)
{
	Products* m = head;
	if (i == 1)
	{
		m->next->pred = NULL;
		head = m->next;
		delete (m->Name);
		delete (m);
		return head;
	}
	i--;
	while (i != 0)
	{
		m = m->next;
		i--;
	}
	if (!m)
	{
		cout << "Такого товара не существует. Список не был изменён.\n";
		return head;
	}
	if (m->next)
	{
		m->next->pred = m->pred;
		m->pred->next = m->next;
	}
	else m->pred->next = NULL;
	delete (m->Name);
	delete(m);
	return head;
};
Products* DeleteElement2(Products* head)
{
	cout << "Введите позицию товара, который хотите удалить.\n";
	int i;
	cin >> i;
	head = DeleteElement1(head, i);
	return head;
};
void Menu2()
{
	cout << "Как вы хотите найти элемент?\n";
	cout << "1 - по названию товара.\n";
	cout << "2 - по цене товара.\n";
};
int Search1(Products* head, char* str)
{
	int i = 1;
	Products* m = head;
	while (m)
	{
		if (!strcmp(m->Name, str))
			return i;
		i++;
		m = m->next;
	}
	return 0;
};
int Search2(Products* head, double b)
{
	int i = 1;
	Products* m = head;
	while (m)
	{
		if (m->Price == b)
			return i;
		i++;
		m = m->next;
	}
	return 0;
};
int Search3(Products* head)
{
	Menu2();
	char a;
	int i;
	cin >> a;
	while (a != '1' && a != '2')
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> a;
	}
	if (a == '1')
	{
		cout << "Введите название товара, который вы хотите найти.\n";
		char str[256];
		cin >> str;
		i = Search1(head, str);
	}
	if (a == '2')
	{
		cout << "Введите цену товара, который вы хотите найти.\n";
		double b;
		cin >> b;
		i = Search2(head, b);
	}
	return i;
};
Products* Input(void)
{
	char str[256];
	double a;
	char *p = new char[256];
	FILE *f;
	if (!(f = fopen("input.txt", "r")))
	{
		printf("Ошибка. Не удалось открыть файл input.\n");
		system("pause");
		return 0;
	}
	Products* r = NULL, *head = NULL;
	while (fgets(str, 256, f) != NULL)
	{
		p = strpbrk(str, "0123456789");
		if (p == NULL)
		{
			cout << "Ошибка. Неверный формат ввода\n";
			return 0;
		}
		str[strlen(str) - strlen(p) - 1] = '\0';
		a = strtod(p, &p);
		r = InsertItem(str, a, r);
		if (!head) head = r;
	}
	fclose(f);
	return head;
};
int OutputFile(Products* head)
{
	FILE *f;
	if (!(f = fopen("output.txt", "w")))
	{
		printf("Ошибка. Не удалось открыть файл output.\n");
		system("pause");
		return 0;
	}
	char *str;
	char *p = new char[256];
	Products* m = head;
	int decimal, sign;
	while (m)
	{
		fputs(m->Name, f);
		fputs(" ", f);
		str = _ecvt(m->Price, 16, &decimal, &sign);
		if (decimal == 0)
			fputs("0", f);
		else
		{
			strcpy(p, str);
			p[decimal] = '\0';
			fputs(p, f);
		}
		str += decimal;
		if (str[0] != '0' || str[1] != '0')
		{
			fputs(",", f);
			strcpy(p, str);
			if (p[2] == '9')
				p[1]++;
			p[2] = '\0';
			if (p[1] == '0')
				p[1] = '\0';
			fputs(p, f);
		}
		fputs("\n", f);
		m = m->next;
	}
	fclose(f);
	return 1;
};
void OutputConsole(Products* head)
{
	Products* m = head;
	while (m)
	{
		cout << m->Name << " - " << m->Price << endl;
		m = m->next;
	}
};
Products* Delete(Products* head)
{
	Products* d = head;
	while (d)
	{
		d = head->next;
		delete head->Name;
		delete head;
		head = d;
	}
	return head;
};
Products* Switch(Products* head, Products* m, Products* p)
{
	if (m->pred != NULL)
		m->pred->next = p;
	if (p->next != NULL)
		p->next->pred = m;
	m->next = (p->next) ? p->next : NULL;
	p->pred = (m->pred) ? m->pred : NULL;
	m->pred = p;
	p->next = m;
	if (m == head)
		head = p;
	return head;
};
Products* Sort(Products* head)
{
	while (1)
	{
		Products* m = head;
		while (1)
		{
			Products* p;
			if (m->next)
				p = m->next;
			else return head;
			int a = 1;
			while (strncmp(m->Name, p->Name, a) == 0)
				a++;
			if (m->Name[a - 1] <= p->Name[a - 1])
				m = m->next;
			else break;
		}
		int k = 0;
		while (k == 0)
		{
			Products* p;
			if (m->next)
				p = m->next;
			else break;
			int a = 1;
			while (strncmp(m->Name, p->Name, a) == 0)
				a++;
			if (m->Name[a - 1] > p->Name[a - 1])
				head = Switch(head, m, p);
			else
				k++;
		}
	}
};
void Menu1()
{
	cout << "Что вы хотите сделать?\n";
	cout << "1 - Создать новый список.\n";
	cout << "2 - Добавить элемент на i-ую позицию.\n";
	cout << "3 - Удалить i-ый элемент из списка.\n";
	cout << "4 - Найти элемент в списке.\n";
	cout << "5 - Отсортировать список по названию товара.\n";
	cout << "6 - Вывести список в файл.\n";
	cout << "7 - Удалить список.\n";
	cout << "E - Выйти из программы.\n";
};
void Solve()
{
	int i;
	char a;
	Products* head = NULL;
	Menu1();
	cin >> a;
	while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != 'E')
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '1')
		{
			cout << "Введите элементы списка в файл input.txt\n";
			system("pause");
			head = Input();
			cout << "Результат:\n";
			OutputConsole(head);
		}
		if (a == '2')
		{
			head = AddElement2(head);
			cout << "Результат:\n";
			OutputConsole(head);
		}
		if (a == '3' && head != NULL)
		{
			head = DeleteElement2(head);
			if (head != NULL)
			{
				cout << "Результат:\n";
				OutputConsole(head);
			}
		}
		if (a == '4' && head != NULL)
		{
			i = Search3(head);
			if (i == 0)
				cout << "Не удалось найти товар.\n";
			else
				cout << "Позиция товара = " << i << endl;
		}
		if (a == '5' && head != NULL)
		{
			head = Sort(head);
			cout << "Результат:\n";
			OutputConsole(head);
		}
		if (a == '6' && head != NULL)
			if (OutputFile(head) != 0)
				cout << "Список был успешно записан в файл.\n";
		if ((a == '7' || a == '3' || a == '4' || a == '5' || a == '6') && head == NULL)
			cout << "Ошибка. Список пуст.\n";
		if (a == '7' && head != NULL)
		{
			head = Delete(head);
			cout << "Список был успешно удалён.\n";
		}
		Menu1();
		cin >> a;
		while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != 'E')
		{
			cout << "Неверный формат ввода. Введите ещё раз.\n";
			cin >> a;
		}
	}
	cout << "Вы успешно вышли из программы.\n";
	return;
};