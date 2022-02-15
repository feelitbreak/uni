#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
Stack::Stack() 
{ 
	ArrS = (char*)malloc(1*sizeof(char));
	ArrS[0] = 0;
	top = 1;
};
Stack::Stack(int N)
{
	ArrS = (char*)malloc(N*sizeof(char));
	top = N;
};
bool Stack::Is_Empty()
{
	bool res = false;
	if (top == NULL)
		res = true;
	return res;
};
Stack::Stack(const Stack &A)
{
	ArrS = (char*)malloc(A.top * sizeof(char));
	for (int i = 0; i < A.top; i++)
		ArrS[i] = A.ArrS[i];
	top = A.top;
};
Stack& Stack::operator=(const Stack &A)
{
	ArrS = (char*)realloc(ArrS, A.top*sizeof(char));
	for (int i = 0; i < A.top; i++)
		ArrS[i] = A.ArrS[i];
	top = A.top;
	return *this;
};
Stack operator+(const Stack& A, const Stack& B)
{
	Stack C(A);
	C.top+= B.top;
	C.ArrS = (char*)realloc(C.ArrS, C.top * sizeof(char));
	for (int i = 0, j=A.top; j < C.top; i++, j++)
		C.ArrS[j] = B.ArrS[i];
	return C;
};
Stack Stack::operator+(int N)
{
	Stack B(*this);
	B.ArrS[B.top - 1] += N;
	return B;
};
Stack Stack::operator-(int N)
{
	Stack B(*this);
	B.ArrS[B.top - 1] -= N;
	return B;
};
Stack Stack::operator*(int N)
{
	Stack B(*this);
	B.ArrS[B.top - 1] *= N;
	return B;
};
Stack Stack::operator/(int N)
{
	Stack B(*this);
	B.ArrS[B.top - 1] /= N;
	return B;
};
Stack& Stack::operator++()
{
	cout << "Введите элемент, который хотите добавить.\n";
	char c;
	cin >> c;
	top++;
	ArrS = (char*)realloc(ArrS, top * sizeof(char));
	ArrS[top - 1] = c;
	return *this;
};
Stack Stack::operator++(int)
{
	Stack B(*this);
	cout << "Введите элемент, который хотите добавить.\n";
	char c;
	cin >> c;
	top++;
	ArrS = (char*)realloc(ArrS, top * sizeof(char));
	ArrS[top - 1] = c;
	return B;
};
Stack& Stack::operator--()
{
	ArrS[top - 1] = 0;
	top--;
	ArrS = (char*)realloc(ArrS, top * sizeof(char));
	return *this;
};
Stack Stack::operator--(int)
{
	Stack B(*this);
	ArrS[top - 1] = 0;
	top--;
	ArrS = (char*)realloc(ArrS, top * sizeof(char));
	return B;
};
bool Stack::operator==(const Stack& B)
{
	return (ArrS[top-1] == B.ArrS[B.top-1]) ? 1 : 0;
};
bool Stack::operator!=(const Stack& B)
{
	return (ArrS[top - 1] != B.ArrS[B.top - 1]) ? 1 : 0;
};
bool Stack::operator>=(const Stack& B)
{
	return (ArrS[top - 1] >= B.ArrS[B.top - 1]) ? 1 : 0;
};
bool Stack::operator<=(const Stack& B)
{
	return (ArrS[top - 1] <= B.ArrS[B.top - 1]) ? 1 : 0;
};
char& Stack::operator[](int N)
{
	return ArrS[N];
};
ostream &operator<<(ostream &os, const Stack& A)
{
	os << "Видимый эл. - " << A.ArrS[A.top - 1] << ". Размер стека - " << A.top << "." << endl;
	return os;
};
istream &operator>>(istream& is, Stack& A)
{
	char c;
	for (int i=0; i<A.top; i++)
	{
		is >> c;
		A.ArrS[i] = c;
	}
	return is;
};
int Stack::Size()
{
	return top;
};
Stack::~Stack() 
{
	free(ArrS);
};