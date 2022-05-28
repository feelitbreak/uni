#include "Header.h"
#include <iostream>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	Ar21 A;
	Ar21 B(5);
	Ar21 C(30, 6);
	cout << "Массив A:" << endl << A;
	cout << "Массив B:" << endl << B;
	cout << "Массив C:" << endl << C;
	cin >> B;
	cout << "Массив B:" << endl << B;
	B = B;
	C = A;
	cout << "Массив B:" << endl << B;
	cout << "Массив C:" << endl << C;
	cout << "Второй элемент массива B = " << B[1] << endl;
	//B.Sort();
	cout << "Массив B:" << endl << B;
	cout << B.Multiply() << endl;
	cout << B.Increase() << endl;
	system("pause");
	return 0;
}