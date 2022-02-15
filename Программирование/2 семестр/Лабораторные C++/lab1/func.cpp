#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
Stack::Stack() { top = 0; }
bool Stack::Is_Empty()
{
	bool res = false;
	if (top == NULL)
		res = true;
	return res;
};
void Stack::Push(char* newname, double newprice)
{
	Products* h;
	h = new Products[1];
	h->Name = new char[strlen(newname) + 1];
	strcpy(h->Name, newname);
	h->Price = newprice;
	h->pred = top;
	top = h;
	return;
};
void Stack::AddElement()
{
	cout << "������� �������� ������ ������ � ��� ����.\n";
	char newname[256];
	double newprice;
	cout << "�����: ";
	cin >> newname;
	cout << "��� ����: ";
	cin >> newprice;
	Push(newname, newprice);
	return;
};
void Stack::Pop()
{
	Products* m;
	m = (top->pred) ? top->pred : NULL;
	delete (top->Name);
	delete (top);
	top = m;
	return;
};
void Stack::Input()
{
	char str[256];
	double a;
	char *p = new char[256];
	FILE *f;
	if (!(f = fopen("input.txt", "r")))
	{
		printf("������. �� ������� ������� ���� input.txt\n");
		system("pause");
		return;
	}
	Products *r = NULL;
	while (fgets(str, 256, f) != NULL)
	{
		p = strpbrk(str, "0123456789");
		if (p == NULL)
		{
			cout << "������. �������� ������ �����\n";
			return;
		}
		str[strlen(str) - strlen(p) - 1] = '\0';
		a = strtod(p, &p);
		Push(str, a);
	}
	fclose(f);
	return;
};
int Stack::OutputFile()
{
	FILE *f;
	if (!(f = fopen("output.txt", "w")))
	{
		printf("������. �� ������� ������� ���� output.\n");
		system("pause");
		return 0;
	}
	char *str;
	char *p = new char[256];
	Products* m = top;
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
		m = m->pred;
	}
	fclose(f);
	return 1;
};
void Stack::OutputConsole()
{
	Products* m = top;
	while (m)
	{
		cout << m->Name << " - " << m->Price << endl;
		m = m->pred;
	}
};
void Stack::Delete()
{
	Products* d = top;
	while (d)
	{
		d = top->pred;
		delete top->Name;
		delete top;
		top = d;
	}
	return;
};
void Stack::Menu()
{
	cout << "��� �� ������ �������?\n";
	cout << "1 - ������� ����� ����.\n";
	cout << "2 - �������� ������� � ����.\n";
	cout << "3 - ������� ������� �� �����.\n";
	cout << "4 - ������� ���� � ����.\n";
	cout << "5 - ������� ���� �� �������.\n";
	cout << "6 - ������� ����.\n";
	cout << "E - ����� �� ���������.\n";
};
Stack::~Stack() {} 