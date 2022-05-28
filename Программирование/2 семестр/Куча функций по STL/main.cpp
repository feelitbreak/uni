/* Кендысь Алексей. Группа 9. Контрольная работа по УП. Вариант
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
#include <algorithm>
#include <list>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	list<int> l;
	char a;
	Menu();
	cin >> a;
	while (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != '8' && a != '9' && a != 'a' && a != 'b' && a != 'c' && a != 'd' && a != 'e' && a != 'f' && a != 'g' && a != 'h' && a != 'i' && a != 'j' && a != 'k' && a != 'l' && a != 'E')
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '0')
		{
			cout << "Введите список в файл input.txt\n";
			system("pause");
			if (!Input(l, "input.txt"))
				cout << "Не удалось открыть файл input.txt\n";
		}
		if (a == '1' && !l.empty())
			Output(l);
		if (a == '2' && !l.empty())
			DeleteElements(l);
		if (a == '3')
			AddElements(l);
		if (a == '4' && !l.empty())
			l.unique();
		if (a == '5' && !l.empty())
			FindGreater(l);
		if (a == '6' && !l.empty())
			SearchPair(l);
		if (a == '7' && !l.empty())
			Divide(l);
		if (a == '8' && !l.empty())
			MultiplyLists(l);
		if (a == '9' && !l.empty())
			Sum(l);
		if (a == 'a' && !l.empty())
			ChangeSign(l);
		if (a == 'b' && !l.empty())
			FindSorted(l);
		if (a == 'c' && !l.empty())
			MultiplyElements(l);
		if (a == 'd' && !l.empty())
			FillRandom(l);
		if (a == 'e' && !l.empty())
			DivideInTwo(l);
		if (a == 'f' && !l.empty())
			FindSquare(l);
		if (a == 'g' && !l.empty())
			AddX(l);
		if (a == 'h' && !l.empty())
			RemoveIf(l);
		if (a == 'i' && !l.empty())
			CountIf(l);
		if (a == 'j' && !l.empty())
			FindSquareSum(l);
		if (a == 'k' && !l.empty())
			RemoveIf2(l);
		if (a == 'l' && !l.empty())
			Permutations(l);
		if ((a == '1' || a == '2' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9' || a == 'a' || a == 'b' || a == 'c' || a != 'd' || a != 'e' || a != 'f' || a != 'g' || a != 'h' || a != 'i' || a != 'j' || a != 'k' || a != 'l') && l.empty())
			cout << "Список пуст.\n";
		Menu();
		cin >> a;
		while (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != '8' && a != '9' && a != 'a' && a != 'b' && a != 'c' && a != 'd' && a != 'e' && a != 'f' && a != 'g' && a != 'h' && a != 'i' && a != 'j' && a != 'k' && a != 'l' && a != 'E')
		{
			cout << "Неверный формат ввода. Введите ещё раз.\n";
			cin >> a;
		}
	}
	cout << "Вы успешно вышли из программы.\n";
	system("pause");
	return 0;
}