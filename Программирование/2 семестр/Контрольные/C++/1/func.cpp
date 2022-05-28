#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
#include <fstream>
using namespace std;
Queue::Queue() 
{ 
	mas = (long*)malloc(1*sizeof(long));
	n = 1;
};
Queue::Queue(int N)
{
	mas = (long*)malloc(N*sizeof(long));
	n = N;
};
Queue::Queue(const Queue &A)
{
	mas = (long*)malloc(A.n * sizeof(long));
	for (int i = 0; i < A.n; i++)
		mas[i] = A.mas[i];
	n = A.n;
	k1 = A.k1;
	k2 = A.k2;
};
Queue& Queue::operator=(const Queue &A)
{
	if (this == &A) return *this;
	mas = (long*)realloc(mas, A.n * sizeof(long));
	n = A.n;
	k1 = A.k1;
	k2 = A.k2;
	for (int i = 0; i <= k2; i++)
		mas[i] = A.mas[i];
	return *this;
};
Queue Queue::operator+(long k)
{
	Queue B(*this);
	if (k > B.k2+1 || k < B.k1+1)
	{
		if (B.k2+1 == B.n)
			throw 1;
		B.mas[B.k2 + 1] = k;
		B.k2++;
		return B;
	}
	B.mas[k - 1] += k;
	return B;
};
Queue Queue::operator-(long X)
{
	Queue B(*this);
	for(int i=0; i<=B.k2; i++)
		if (B.mas[i] == X)
		{
			for (int j = i; j < B.k2; j++)
				B.mas[j] = B.mas[j + 1];
			B.k2--;
			i--;
		}
	return B;
};
Queue Queue::operator/(long k)
{
	Queue B(*this);
	for (int i=0; i<=k2; i++)
		B.mas[i] /= k;
	return B;
};
long& Queue::operator[](int N)
{
	return mas[N];
};
bool Queue::operator<=(const Queue& B)
{
	if (n < B.n) return true;
	if (n > B.n) return false;
	if (k2 > B.k2) return false;
	if (k2 < B.k2) return true;
	for (int i = 0; i <= k2; i++)
		if (mas[i] > B.mas[i]) return false;
	return true;
};
ostream &operator<<(ostream &os, const Queue& A)
{
	for (int i = 0; i <= A.k2; i++)
		os << A.mas[i] << endl;
	return os;
};
istream &operator>>(istream& is, Queue& A)
{
	int i;
	for (i = 0; i<A.n && !is.eof(); i++)
	{
		is >> A.mas[i];
	}
	A.k1 = 0;
	A.k2 = i-2;
	if (i == A.n) A.k2 = A.n - 1;
	return is;
};
Queue::~Queue() 
{
	free(mas);
};