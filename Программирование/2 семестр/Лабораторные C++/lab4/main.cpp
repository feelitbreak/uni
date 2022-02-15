/*Кендысь Алексей. Группа 9. 1 курс, 2 семестр. C++, Лаб 4. Индивидуальное задание 5
Условие:
Создать абстрактный класс. Создать класс жидкость(наследник абстрактного класса), имеющий
название (указатель на строку), плотность. Определить конструкторы, деструктор и функцию вывода.
Создать public-производный класс - сок, имеющий процент натуральности. Определить конструкторы по
умолчанию и с разным числом параметров, деструкторы, функцию вывода. Определить функции
переназначения плотности и крепости. Использовать статическую переменную для хранения количества
созданных объектов классов наследников и константы для хранения идентификационных номеров в
абстрактном классе, в классах наследниках.
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	char* str = new char[256];
	Liquid L1;
	cout << "L1: ";
	L1.Show();
	cout << endl;
	strcpy(str, "Жидкость L2");
	Liquid L2(str);
	cout << "L2: ";
	L2.Show();
	cout << endl;
	strcpy(str, "Жидкость L3");
	Liquid L3(str, 31);
	cout << "L3: ";
	L3.Show();
	cout << endl;
	Juice J1;
	cout << "J1: ";
	J1.Show();
	cout << endl;
	strcpy(str, "Сок J2");
	Juice J2(str);
	cout << "J2: ";
	J2.Show();
	cout << endl;
	strcpy(str, "Сок J3");
	Juice J3(str, 10);
	cout << "J3: ";
	J3.Show();
	cout << endl;
	strcpy(str, "Сок J4");
	Juice J4(str, 11, 24);
	cout << "J4: ";
	J4.Show();
	cout << endl;
	cout << "Плотность сока J3 = " << J3.GetDensity() << "." << endl;
	cout << "Процент натуральности сока J4 = " << J4.GetPercentage() << "." << endl;
	delete[] str;
	system("pause");
	return 0;
}