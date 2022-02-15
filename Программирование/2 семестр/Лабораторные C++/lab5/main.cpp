/*������� �������. ������ 9. 1 ����, 2 �������. C++, ��� 5. �������������� ������� 5
�������:
��������� ����������� ������, ����������� ���������� ������: ArrayStack( �� �������) �
ArrayStackIterator.
*/
#include <iostream>
#include "header.h"
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	int N;
	cout << "������� ����������� �����.\n";
	cin >> N;
	while (N < 0)
	{
		cout << "������ ������� �����������. ������� ��� ���.\n";
		cin >> N;
	}
	ArrayStack S(N);
	cout << "������� �������� �����. ������� 101, ����� ������������.\n";
	int a=0;
	while (!S.IsFull())
	{
		cin >> a;
		if (a == 101) break;
		S.push(a);
	}
	if (S.IsFull()) cout << "���� ��������.\n";
	if (!S.IsEmpty())
	{
		cout << "������ ��������� ������� �� �����.\n";
		cout << "�������� ������� - " << S.pop() << endl;
	}
	ArrayStackIterator A(S);
	cout << "���������� ����:\n";
	while (A.InRange())
	{
		cout << *A << endl;
		++A;
	}
	system("pause");
	return 0;
}