#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <algorithm>
#include <list>
#include <iterator>
#include <fstream>
#include <numeric>
#include <functional>
#include <vector>
using namespace std;
void Menu()
{
	cout << "��� �� ������ �������?\n";
	cout << "0 - ������ ������ �� �����.\n";
	cout << "1 - ����������� ��������� ������.\n";
	cout << "2 - ������� ��������� ��������� � �������� �������.\n";
	cout << "3 - �������� ��������� ��������� � �������� ������� c ������� list.\n";
	cout << "4 - ������� ������ ��������, unique.\n";
	cout << "5 - ���������� ������� ��������� ������ ������� ������ ��������������� ��������� ������� �������, inner_product.\n";
	cout << "6 - ����� � ������ ��� ���������� ������� ��������, adjacent_find.\n";
	cout << "7 - ��������� ������ �� ��� ������, partition.\n";
	cout << "8 - ����������� ����������� ��� ������, transform.\n";
	cout << "9 - �������������� ����������� ��� ������, transform.\n";
	cout << "a - �������� ����� ��������� �� ���������������, transform.\n";
	cout << "b - ����� ����������� ���������������� ���������, is_sorted_until.\n";
	cout << "c - ��������� ������������ ���������, accumulate.\n";
	cout << "d - ��������� ������ ���������� ������� � ��������� [0; �), generate.\n";
	cout << "e - ��������� �������� �� ��� ������, sort.\n";
	cout << "f - ����� � ������� ����� ����� ���������������������, ������ ������� ������� ����� �������� ������� �������, search.\n";
	cout << "g - ��������� ��� �������� �� �, transform.\n";
	cout << "h - ������� �������� �� �������, remove_if.\n";
	cout << "i - ������� ���������, ��������� �� 3, count_if.\n";
	cout << "j - ��������� ����� ��������� ���������, accumulate.\n";
	cout << "k - ������� �������� >k, remove_if.\n";
	cout << "l - ������������� ��� ������������ ��������� �������, next_permutation.\n";
	cout << "E - ����� �� ���������.\n";
};
bool Input(list<int>& l, const char str[])
{
	l.clear();
	filebuf fb;
	if (fb.open(str, std::ios::in))
	{
		istream is(&fb);
		copy(istream_iterator<int>(is), istream_iterator<int>(), back_inserter(l));
		fb.close();
		return true;
	}
	else return false;
};
void Output(list<int>& l)
{
	cout << "������:\n";
	copy(l.begin(), l.end(), ostream_iterator<int>(cout, " "));
	cout << endl;
};
void DeleteElements(list<int> &l)
{
	int a, b;
	cout << "������� �������.\n";
	cin >> a;
	while (a < 1 || a > (int)l.size())
	{
		cout << "�� ������� ����� �������. ������� ��� ���.\n";
		cin >> a;
	}
	cout << "������� ���-�� ���������.\n";
	cin >> b;
	while (b < 1 || a+b-1 > (int)l.size())
	{
		cout << "������. ������� ��� ���.\n";
		cin >> b;
	}
	list<int>::iterator i = l.begin();
	advance(i, a-1);
	list<int>::iterator j = i;
	advance(j, b);
	l.erase(i, j);
};
void AddElements(list<int> &l)
{
	list<int> l2;
	int a, b;
	cout << "������� �������.\n";
	cin >> a;
	while (a < 1 || a>(int)l.size()+1)
	{
		cout << "�� ������� ����� �������. ������� ��� ���.\n";
		cin >> a;
	}
	cout << "������� ������ ������ � ���� input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "������. ������ ������ ����.\n";
		return;
	}
	Output(l2);
	cout << "������� ���-�� ���������.\n";
	cin >> b;
	while (b < 1 || b>(int)l2.size())
	{
		cout << "������. ������� ��� ���.\n";
		cin >> b;
	}
	list<int>::iterator i = l.begin();
	advance(i, a - 1);
	list<int>::iterator j = l2.begin();
	list<int>::iterator k = l2.begin();
	advance(k, b);
	l.splice(i, l2, j, k);
};
void FindGreater(list<int>& l)
{
	list<int> l2;
	cout << "������� ������ ������ � ���� input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "������. ������ ������ ����.\n";
		return;
	}
	Output(l2);
	list<int>::iterator i = l.begin();
	list<int>::iterator j = i;
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	advance(j, min);
	list<int>::iterator k = l2.begin();
	int a = inner_product(i, j, k, 0, plus<int>(), greater<int>());
	//i - ������ ������� ������, j - �������� ������� ������, �� �������� ����� ���������, k - �������� ������ ������� ������
	cout << "��������� = " << a << endl;
	//�������� ���������� ��� ��������� ���������, ���� ��������� ��� ��������� � ������� �� ������
	//��������� �������� ��������� ����� �� ����� � ����� �����
	//x=(x1,x2,x3) y=(y1,y2,y3)
	//inner_product(i,j,k,0) = x1*y1+x2*y2+x3*y3
	//����� �������� ������ ���� � ��������� �� ��, ��� ������, � ����� ��������� ����
	//������ �������� ������ ���������, �� ������ �������� >
	//������ �������� a = (x1>y1)+(x2>y2)+(x3>y3)
	//��� ��, ��� � �������
};
void SearchPair(list<int>& l)
{
	list<int>::iterator i = adjacent_find(l.begin(), l.end());
	if (i != l.end())
		cout << "������ ������� ������� = " << *i << endl;
	else cout << "�� ������� ����� ������� ���������.\n";
};
void Divide(list<int>& l)
{
	partition(l.begin(), l.end(), bind2nd(greater<int>(), 5));
};
void MultiplyLists(list<int>& l)
{
	list<int> l2;
	cout << "������� ������ ������ � ���� input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "������. ������ ������ ����.\n";
		return;
	}
	Output(l2);
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	list<int>::iterator i = l.begin();
	advance(i, min);
	transform(l.begin(), i, l2.begin(), l.begin(), multiplies<int>()); 
	//������ - ������ ������� �����, 
	//������ - �������� ������� �����, �� ������ �������� ��������, 
	//������ - ������ ������� �����
	//��������� - ������ �����, ���� ���������� ���������
};
void Sum(list<int>& l)
{
	list<int> l2;
	cout << "������� ������ ������ � ���� input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "�� ������� ������� ���� input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "������. ������ ������ ����.\n";
		return;
	}
	Output(l2);
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	list<int>::iterator i = l.begin();
	advance(i, min);
	transform(l.begin(), i, l2.begin(), l.begin(), plus<int>());
	//������ - ������ ������� �����, 
	//������ - �������� ������� �����, �� ������ �������� ��������, 
	//������ - ������ ������� �����,
	//��������� - ������ �����, ���� ���������� ���������
};
void ChangeSign(list<int>& l)
{
	transform(l.begin(), l.end(), l.begin(), negate<int>());
};
void FindSorted(list<int>& l)
{
	list<int>::iterator i = is_sorted_until(l.begin(), l.end());
	cout << "���������� ��������������� ��������:\n";
	copy(l.begin(), i, ostream_iterator<int>(cout, " "));
	cout << endl;
};
int Multiply(int x, int y)
{
	return x * y;
};
void MultiplyElements(list<int>& l)
{
	int a;
	a = accumulate(l.begin(), l.end(), 1, Multiply);
	cout << "������������ ��������� = " << a << endl;
	//������ ������� ��������� � ����������. ���� ���������, �� �������� �� ����, ���� ��������, �� ����������, � �.�.
};
int X;
int RandomNumber()
{
	return (rand() % X);
}
void FillRandom(list<int>& l)
{
	cout << "������� ����� X ��� ���������.\n";
	cin >> X;
	while (X <= 0)
	{
		cout << "������. �������� ������ ���������. ������� ��� ���.\n";
		cin >> X;
	}
	generate(l.begin(), l.end(), RandomNumber);
};
bool Comparator(int x, int y)
{
	return (x % 2 == 0 && y % 2 == 1);
};
void DivideInTwo(list<int>& l)
{
	//sort(l.begin(), l.end(), Comparator);
	//��� ����� sort ������ ������, �� ��� ������� � ��������� �������, �� ����� ��� ��� ������� ��� ���� ��������
	//��� ����� �� ������ � �������� ��������
};
bool Square(int x, int y)
{
	return (x*x == y);
};
void FindSquare(list<int>& l)
{
	// int A[] = { 9, 9 }; ��������
	cout << "������� ����������� �������.\n";
	int n;
	cin >> n;
	while (n < 1)
	{
		cout << "�������� ������ �����. ������� ��� ���.\n";
		cin >> n;
	}
	cout << "������� ������.\n";
	int *A = new int[n];
	for (int i = 0; i < n; i++)
		cin >> A[i];
	list<int>::iterator i = search(l.begin(), l.end(), A, A+2, Square);
	if (i == l.end())
		cout << "�� ������� ����� ���������������������.\n";
	else cout << "������ ��������������������� ��������� �� ������� " << distance(l.begin(), i)+1 << endl;
	delete[] A;
};
void AddX(list<int>& l)
{
	int x;
	cout << "������� ����� X.\n";
	cin >> x;
	transform(l.begin(), l.end(), l.begin(), bind2nd(plus<int>(),x));
};
bool Remove(int x)
{
	return(x < 0);
};
void RemoveIf(list<int>& l)
{
	// list<int>::iterator i = remove_if(l.begin(), l.end(), [](int i)->bool {return (i < 0); }); ����� ������ ���������
	list<int>::iterator i = remove_if(l.begin(), l.end(), Remove);
	if (i != l.end())
		l.erase(i, l.end());
	//������� ��� �������� ������ ����
};
bool Count(int x)
{
	return (x % 3 == 0);
};
void CountIf(list<int>& l)
{
	int a = count_if(l.begin(), l.end(), Count);
	cout << "����� ���������, ��������� �� 3, ����� " << a << endl;
};
int Sum2(int x, int y)
{
	return x * x+ y * y;
}
void FindSquareSum(list<int>& l)
{
	int a = accumulate(l.begin(), l.end(), 1 , Sum2);
	cout << "����� ��������� ��������� ����� " << a << endl;
	//������ �������� - ��������� �������� �����
	//��� �� �� �������, �� ��� �� ����� ���� ����������� ����� ���, accumulate �� ��� ��������
	//���� �������� ����� ������� x*10 + y � ������ A= {1, 2, 3}
	//�� ��� ����� ������� ������ �������� �� 10 � ������� ������, �.�. ������� 12, � ����� ������� ��� �� 10 � ������� ������ ���, �� ���� ����� 123
	//����������� ����� ����� ������ 123
	//������� ����� ��������� �� ���� ����� � accumulate ����� �� ���������
	//��� ����� ������� �������, ��������� ������� �������, ����� ����� ������� ���� ����� � ��������� ��� ������� ��������, ���������� �������� �����
};
void RemoveIf2(list<int>& l)
{
	cout << "������� ����� k.\n";
	int k;
	cin >> k;
	list<int>::iterator i = remove_if(l.begin(), l.end(), bind2nd(greater<int>(), k));
	if (i != l.end())
		l.erase(i, l.end());
};
void Permutations(list<int>& l)
{
	cout << "��� ������������ ������:\n";
	list<int> l2 = l;
	l2.sort();
	do {
		copy(l2.begin(), l2.end(), ostream_iterator<int>(cout, " "));
		cout << endl;
	} while (next_permutation(l2.begin(), l2.end()));
	//��� ������������ �� ����������� ������, ������� ����� ������������� ����� ������
};