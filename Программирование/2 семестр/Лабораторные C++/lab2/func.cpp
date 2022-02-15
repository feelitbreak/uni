#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
List::List() { head = NULL; tail = NULL; };
bool List::Is_Empty()
{
	bool res = false;
	if (tail == NULL)
		res = true;
	return res;
};
List::List(FILE* f)
{
	char str[256];
	double a;
	char *p = new char[256];
	while (fgets(str, 256, f) != NULL)
	{
		p = strpbrk(str, "-0123456789");
		if (p == NULL)
		{
			cout << "Ошибка. Неверный формат ввода\n";
			return;
		}
		str[strlen(str) - strlen(p) - 1] = '\0';
		a = strtod(p, &p);
		Products* h;
		h = new Products[1];
		h->Name = new char[strlen(str) + 1];
		strcpy(h->Name, str);
		h->Price = a;
		h->next = NULL;
		h->pred = tail;
		if (tail) tail->next = h;
		tail = h;
		if (!head) head = h;
	}
	fclose(f);
};
void List::OutputConsole()
{
	Products* m = head;
	while (m)
	{
		cout << m->Name << " - " << m->Price << endl;
		m = m->next;
	}
};
void List::Menu()
{
	cout << "Что вы хотите сделать?\n";
	cout << "1 - Удалить наименьший положительный элемент в списке.\n";
	cout << "2 - Вывести список на консоль.\n";
	cout << "E - Выйти из программы.\n";
};
int List::Search()
{
	int min = 0;
	double minprice = 0;
	Products* d = head;
	while (d)
	{
		if (d->Price > 0 && (d->Price < minprice || minprice == 0))
			minprice = d->Price;
		d = d->next;
	}
	d = head;
	while (d)
	{
		if (d->Price == minprice)
			break;
		d = d->next;
		min++;
	}
	if (!minprice) return 0;
	else return min+1;
};
void List::DeleteElement(int i)
{
	Products* m = head;
	if (i == 1)
	{
		if (m->next)
		{
			m->next->pred = NULL;
			head = m->next;
		}
		else
		{
			head = 0;
			tail = 0;
		}
		delete (m->Name);
		delete (m);
		return;
	}
	if (i == 0)
	{
		cout << "В списке нет положительных элементов.\n";
		return;
	}
	i--;
	while (i != 0)
	{
		m = m->next;
		i--;
	}
	if (m->next)
	{
		m->next->pred = m->pred;
		m->pred->next = m->next;
	}
	else
	{
		m->pred->next = NULL;
		tail = m;
	}
	delete (m->Name);
	delete(m);
	return;
};
List::~List()
{
	if (head)
	{
		Products* d = head;
		while (d)
		{
			d = head->next;
			delete head->Name;
			delete head;
			head = d;
		}
	}
}