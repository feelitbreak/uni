#include "Header.h"
#include <iostream>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	Ar21 A;
	Ar21 B(5);
	Ar21 C(30, 6);
	cout << "������ A:" << endl << A;
	cout << "������ B:" << endl << B;
	cout << "������ C:" << endl << C;
	cin >> B;
	cout << "������ B:" << endl << B;
	B = B;
	C = A;
	cout << "������ B:" << endl << B;
	cout << "������ C:" << endl << C;
	cout << "������ ������� ������� B = " << B[1] << endl;
	//B.Sort();
	cout << "������ B:" << endl << B;
	cout << B.Multiply() << endl;
	cout << B.Increase() << endl;
	system("pause");
	return 0;
}