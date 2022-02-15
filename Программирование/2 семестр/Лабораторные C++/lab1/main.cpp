/* Кендысь Алексей. Группа 9. 1 курс, 2 семестр. C++, Лаб 1. Индивидуальное задание 5
Условие:
Стек. Элемент списка структура. Структура содержит информацию о товарах, поля: название, цена (char*, double).
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
int main()
{
	setlocale(LC_ALL, "rus");
	Stack A;
	char a;
	A.Menu();
	cin >> a;
	while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != 'E')
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '1')
		{
			cout << "Введите элементы стека в файл input.txt\n";
			system("pause");
			A.Input();
			cout << "Результат:\n";
			A.OutputConsole();
		}
		if (a == '2')
		{
			A.AddElement();
			cout << "Результат:\n";
			A.OutputConsole();
		}
		if (a == '3' && A.Is_Empty() == NULL)
		{
			A.Pop();
			if (A.Is_Empty() == NULL)
			{
				cout << "Результат:\n";
				A.OutputConsole();
			}
		}
		if (a == '4' && A.Is_Empty() == NULL)
			if (A.OutputFile() != 0)
				cout << "Стек был успешно записан в файл.\n";
		if (a == '5' && A.Is_Empty() == NULL)
			A.OutputConsole();
		if ((a == '6' || a == '3' || a == '4' || a == '5') && A.Is_Empty() != NULL)
			cout << "Стек пуст.\n";
		if (a == '6' && A.Is_Empty() == NULL)
		{
			A.Delete();
			cout << "Стек был успешно удалён.\n";
		}
		A.Menu();
		cin >> a;
		while (a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != 'E')
		{
			cout << "Неверный формат ввода. Введите ещё раз.\n";
			cin >> a;
		}
	}
	if (!A.Is_Empty())
		A.Delete();
	cout << "Вы успешно вышли из программы.\n";
	system("pause");
	return 0;
}