#include <iostream>
#include <stdio.h>
#include <fstream>
#include <string>
#include <math.h>
using namespace std;
//Шаблонный класс:
template <class T>
class DeQueue 
{
private:
	T* elems;
	int n;
public:
	DeQueue()
	{
		n = 0;
	};
	DeQueue(const DeQueue& m)
	{
		n = m.n;
		elems = new T[n];
		for (int i = 0; i < n; i++)
			elems[i] = m.elems[i];
	};
	void ReadFile(string);
	void OutputFile(string);
	bool IsEmpty()
	{
		if (n == 0) return true;
		else return false;
	}
	bool operator<(DeQueue& m)
	{
		int k1 = 0, k2 = 0;
		for (int i = 0; i < n; i++)
			if (elems[i] > 0) k1++;
		for (int i = 0; i < m.n; i++)
			if (m.elems[i] > 0) k2++;
		if (k1 < k2) return true;
		else return false;
	}
	DeQueue& operator-(int K);
	template<class T1>
	friend istream& operator>>(istream&, DeQueue<T1>&);
	template<class T2>
	friend ostream& operator<<(ostream&, const DeQueue<T2>&);
	~DeQueue()
	{
		delete[] elems;
	};
	
};
//Структура:
struct Elem 
{
	string leksema;
	double numb;
	Elem()
	{
		leksema = "";
		numb = 0;
	};
	Elem(double, string);
	Elem(const Elem&);
	Elem& operator= (const Elem&);
	friend istream& operator>>(istream&, Elem&);
	friend ostream& operator<<(ostream&, const Elem&);
	bool operator>(int);
	~Elem() {};
};
//Шаблонные методы:
template<class T>
void DeQueue<T>::ReadFile(string s)
{
	ifstream fin(s);
	if (!fin.is_open())
	{
		cout << "Не удалось открыть файл.\n";
		return;
	}
	fin >> *this;
	fin.close();
};
template<class T>
void DeQueue<T>::OutputFile(string s)
{
	ofstream fout(s);
	if (!fout.is_open())
	{
		cout << "Не удалось открыть файл.\n";
		return;
	}
	fout << *this;
	fout.close();
};
template<class T>
DeQueue<T>& DeQueue<T>:: operator-(int K)                                                         
{
	if (K > n)
	{
		cout << "В очереди нет такого кол-ва элементов.\n";
		return *this;
	}
	DeQueue<T> B(*this);
	delete[] elems;
	n = n - K;
	elems = new T[n];
	for (int i = 0; i < n; i++)
		elems[i] = B.elems[i + K];
	return *this;
};
template <class T1>
istream& operator>>(istream& is, DeQueue<T1>& A)
{
	is >> A.n;
	delete[] A.elems;
	A.elems = new T1[A.n];
	for (int i = 0; i < A.n; i++)
		is >> A.elems[i];
	return is;
};
template <class T2>
ostream& operator<<(ostream &os, const DeQueue<T2>& A)
{
	for (int i = 0; i < A.n; i++)
		os << A.elems[i] << endl;
	return os;
};
//Методы структуры:
Elem::Elem(double a, string s)
{
	leksema = s;
	numb = a;
};
Elem::Elem(const Elem& m)
{
	leksema = m.leksema;
	numb = m.numb;
};
Elem& Elem::operator= (const Elem& m)
{
	if (this == &m) return *this;
	leksema = m.leksema;
	numb = m.numb;
	return *this;
};
istream& operator>>(istream& is, Elem& A)
{
	is >> A.numb;
	is >> A.leksema;
	return is;
};
ostream& operator<<(ostream &os, const Elem& A)
{
	os << A.numb << " " << A.leksema;
	return os;
};
bool Elem::operator>(int x)
{
	double b;
	double a = modf(numb, &b);
	a *= 10;
	if (a > x)
		return true;
	else return false;
}