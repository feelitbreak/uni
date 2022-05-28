#define _CRT_SECURE_NO_WARNINGS
#include "Header.h"
#include <iostream>
using namespace std;
Ar21::Ar21()
{
	count = 1;
	M = new int[1];
	M[0] = rand()%100;
};
Ar21::Ar21(int N)
{
	count = N;
	M = new int[N];
	for (int i = 0; i < N; i++)
		M[i] = rand() % 100;
};
Ar21::Ar21(int N, int a)
{
	count = N;
	M = new int[N];
	for (int i = 0; i < N; i++)
		M[i] = a;
};
Ar21::Ar21(const Ar21& A)
{
	count = A.count;
	M = new int[count];
	for (int i = 0; i < count; i++)
		M[i] = A.M[i];
};
Ar21& Ar21::operator=(const Ar21& A)
{
	if (this == &A) return *this;
	count = A.count;
	delete[] M;
	M = new int[count];
	for (int i = 0; i < count; i++)
		M[i] = A.M[i];
	return *this;
};
int& Ar21::operator[](int a)
{
	return M[a];
};
istream& operator>>(istream& is, Ar21& A)
{
	for (int i = 0; i < A.count; i++)
		is >> A.M[i];
	return is;
};
ostream& operator<<(ostream& os, Ar21& A)
{
	for (int i = 0; i < A.count; i++)
		os << A.M[i]<< " ";
	os << endl;
	return os;
};
void Ar21::Sort()
{
	int a;
	for (int i = 0; i < count; i++)
		for(int j=i+1; j<count; j++)
			if (M[i] > M[j])
				swap(M[i], M[j]);
};
int Ar21::Multiply()
{
	int a=1;
	for (int i = 0; i < count; i++)
		if (M[i] != 0 && M[i] % 3 == 0)
			a *= M[i];
	return a;
};
int Ar21::Increase()
{
	int N= 0;
	for (int i = 0; i < count-1; i++)
		if (M[i] > M[i + 1])
			N++;
	N++;
	return N;
};
Ar21::~Ar21()
{
	delete[] M;
};