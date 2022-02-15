#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;
Element1& Element1::operator= (const Element1& A)
{
	if (this == &A) return *this;
	strcpy(Name, A.Name);
	strcpy(Addr, A.Addr);
	return *this;
};
Element2& Element2::operator= (const Element2& A)
{
	if (this == &A) return *this;
	strcpy(Name, A.Name);
	Count = A.Count;
	return *this;
};
Element3& Element3::operator= (const Element3& A)
{
	if (this == &A) return *this;
	strcpy(Name, A.Name);
	strcpy(Addr, A.Addr);
	Count = A.Count;
	return *this;
};
Element3& Element3::operator= (const Element1& A)
{
	for (int i = 0; i <= strlen(A.Name); i++)
		Name[i] = A.Name[i];
	for (int i = 0; i <= strlen(A.Addr); i++)
		Addr[i] = A.Addr[i];
	Count = 0;
	return *this;
};
Element3& Element3::operator= (const Element2& A)
{
	for (int i = 0; i <= strlen(A.Name); i++)
		Name[i] = A.Name[i];
	*Addr = NULL;
	Count = A.Count;
	return *this;
};
bool Element3::operator==(const Element3& A)
{
	return (!strcmp(Name, A.Name) && (strcmp(Addr, A.Addr) || Count != A.Count)) ? true : false;
};
bool Element3::operator<(const Element3& A)
{
	return (Count<A.Count) ? true : false;
};
bool Element1::operator== (const Element2& A)
{
	return (!strcmp(Name, A.Name)) ? true : false;
};
bool Element2::operator== (const Element1& A)
{
	return (!strcmp(Name, A.Name)) ? true : false;
};
ostream& operator<<(ostream& os, const Element1& A)
{
	os << "Фамилия - ";
	if (A.Name)
		os << A.Name;
	else os << "Неизвестно";
	os << ". Адрес - ";
	if (A.Addr)
		os << A.Addr;
	else os << "Неизвестно";
	os << ".";
	return os;
};
ostream& operator<<(ostream& os, const Element2& A)
{
	os << "Фамилия - ";
	if (A.Name)
		os << A.Name;
	else os << "Неизвестно";
	os << ". Задолженности - ";
	if (A.Count)
		os << A.Count;
	else os << "Неизвестно";
	os << ".";
	return os;
};
ostream& operator<<(ostream& os, const Element3& A)
{
	os << "Фамилия - ";
	if (A.Name)
		os << A.Name;
	else os << "Неизвестно";
	os << ". Адрес - ";
	if (A.Addr)
		os << A.Addr;
	else os << "Неизвестно";
	os << ". Задолженности - ";
		os << A.Count;
	os << ".";
	return os;
};
istream& operator>>(istream& is, Element1& A)
{
	is >> A.Name >> A.Addr;
	return is;
};
istream& operator>>(istream& is, Element2& A)
{
	is >> A.Name >> A.Count;
	return is;
};
istream& operator>>(istream& is, Element3& A)
{
	is >> A.Name >> A.Addr >> A.Count;
	return is;
};