#ifndef Header_H
#define Header_H
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;
struct Element3;
template<class T>
class MASSIV
{
public:
	int count;
	T*M;

	MASSIV()
	{
		count = 1;
		M = new T[1];
	};
	MASSIV(int n)
	{
		count = n;
		M = new T[n];
	};
	MASSIV(MASSIV <T>& m)
	{
		count = m.count;
		M = new T[count];
		for (int i = 0; i < count; i++)
			M[i] = m.M[i];
	};
	MASSIV& operator= (const MASSIV<T>& A)
	{
		if (this == &A) return *this;
		count = A.count;
		delete[] M;
		M = new T[count];
		for (int i = 0; i < count; i++)
			M[i] = A.M[i];
		return *this;
	};
	T& operator[](int a)
	{
		return M[a];
	};
	template<class T1, class T2>
	void Difference(const MASSIV<T1>&, const MASSIV<T2>&);
	template <class T3>
	friend istream& operator>>(istream&, MASSIV<T3>&);
	template <class T4>
	friend ostream& operator<<(ostream&, const MASSIV<T4>&);
	void WriteBin(ofstream&);
	int Search(T a)
	{
		for (int i = 0; i < count; i++)
			if (M[i] == a)
				return i+1;
		return 0;
	};
	void Sort()
	{
		for (int i = 0; i < count; i++)
			for (int j = 0; j < count; j++)
				if (M[i] < M[j])
					swap(M[i], M[j]);
	};
	~MASSIV()
	{
		delete[] M;
	};
};
//Структуры для контейнеров:
struct Element2;
struct Element1
{
	char Name[50];
	char Addr[50];
	Element1& operator= (const Element1&);
	bool operator== (const Element2&);
	friend ostream& operator<<(ostream&, const Element1&);
	friend istream& operator>>(istream&, Element1&);
};
struct Element2
{
	char Name[50];
	int Count;
	Element2& operator= (const Element2&);
	bool operator== (const Element1&);
	friend ostream& operator<<(ostream&, const Element2&);
	friend istream& operator>>(istream&, Element2&);
};
struct Element3
{
	char Name[50];
	char Addr[50];
	int Count;
	Element3& operator= (const Element3&);
	Element3& operator= (const Element1&);
	Element3& operator= (const Element2&);
	bool operator==(const Element3&);
	bool operator< (const Element3&);
	friend ostream& operator<<(ostream&, const Element3&);
	friend istream& operator>>(istream&, Element3&);
};

//Шаблонные методы:
template <class T3>
istream& operator>>(istream& is, MASSIV<T3>& A)
{
	for (int i = 0; i < A.count; i++)
		is >> A.M[i];
	return is;
};
template <class T4>
ostream& operator<<(ostream &os, const MASSIV<T4>& A)
{
	for (int i = 0; i < A.count; i++)
		os << A.M[i] << endl;
	return os;
};
template <class T>
void MASSIV<T>::WriteBin(ofstream& out)
{
	out.write((const char*)M, sizeof(T)*count);
	out.close();
	cout << "Массив Container2 был также записан в бинарный файл output2.txt\n";
	cout << "Чтение Container2 из бинарного файла output2.txt для проверки:\n";
	ifstream fin1;
	fin1.open("output2.txt", ios::binary);
	MASSIV <T> K(count);
	fin1.read((char*)K.M, sizeof(T)*count);
	cout << K;
	fin1.close();
};
template <class T> 
template <class T1, class T2>
void MASSIV<T>::Difference(const MASSIV<T1>& A, const MASSIV<T2>& B)
{
	delete[] M;
	int k = 0;
	for (int i = 0; i < A.count; i++)
		for (int j = 0; j < B.count; j++)
			if (A.M[i] == B.M[j])
			{
				k++;
				j = B.count;
			}
	count = A.count - k;
	M = new T[count];
	k = 0;
	for (int i = 0; i< A.count; i++)
		for (int j = 0; j < B.count; j++)
		{
			if (A.M[i] == B.M[j])
				j = B.count;
			if (j == B.count - 1)
			{
				M[k] = A.M[i];
				k++;
			}
		}
};
#endif