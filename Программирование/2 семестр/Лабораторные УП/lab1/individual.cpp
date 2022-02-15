/*Кендысь Алексей. Группа 9. 1 курс, 2 семестр. УП, Лаб 1. Индивидуальное задание 5
Условие:
Ввести в строку, среди лексем не являющихся числами, найти лексемы состоящие только из одинаковых символов, записать в стек. Заменить 
в стеке элемент. Найти последнее в строке число Р(ввести с консоли) , вывести позицию в изначальной строке. Продублировать  в строке 
лексему состоящие только из одинаковых символов, добавить в конец строки. Любую подстроку из знаков препинания - удалить из строки.   
Все результаты вывести.
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stack>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
using namespace std;
bool Is_OneSymbol(string str)
{
	string temp;
	temp.assign(str, 0, 1);
	for (int i = 1; i < str.length(); i++)
	{
		if (str.compare(i, 1, temp))
			return false;
	}
	return true;
}
int main()
{
	setlocale(LC_ALL, "rus");
	stack<string> A;
	string str;
	size_t b = 0;
	cout << "Введите строку.\n";
	cin >> str;
	size_t a;
	string str1;
	str1.assign(str);
	string str2;
	str2.assign(str);
	while (1)
	{
		a = str1.find_first_not_of("1234567890");
		if (a == string::npos)
			break;
		if (a)
			str1.erase(0, a);
		a = str1.find_first_of("1234567890");
		if (a == string::npos)
			a = str1.length();
		string temp;
		temp.assign(str1, 0, a);
		if (Is_OneSymbol(temp))
		{
			A.push(temp);
			str2.append(temp);
		}
		str1.erase(0, a);
	}
	if (A.empty())
		cout << "Стек пуст.\n";
	else
	{
		cout << "Введите строку, на которую хотите заменить элемент стека.\n";
		string temp;
		cin >> temp;
		A.pop();
		A.push(temp);
		cout << "Полученный стек:\n";
		while (!A.empty())
		{
			cout << A.top() << endl;
			A.pop();
		}
	}
	string P;
	cout << "Введите число, которое хотите найти в изначальной строке.\n";
	cin >> P;
	a = str.rfind(P);
	if (a == string::npos)
		cout << "Такого числа в строке нет.\n";
	else cout << "Позиция последнего вхождения числа = " << a+1 << endl;
	cout << "Изначальная строка с продублированными лексемами, состоящими из одного символа:\n" << str2 << endl;
	str1.assign(str);
	a = str1.find_first_not_of(",./?'\":;[]{}()*!`-_");
	if (a == string::npos)
		cout << "Вся строка состоит из знаков препинания.\n";
	else
	{
		while (1)
		{
			a = str1.find_first_of(",./?'\":;[]{}()*!`-_");
			if (a == string::npos)
				break;
			size_t b = str1.find_first_not_of(",./?'\":;[]{}()*!`-_", a);
			if (b == string::npos)
				b = str1.length();
			str1.erase(a, b);
		}
		cout << "Изначальная строка без знаков препинания:\n" << str1 << endl;
	}
	system("pause");
	return 0;
}