/* Кендысь Алексей. Группа 9. 1 курс, 2 семестр. C++, Лаб 2. Индивидуальное задание 5
Условие:
Удалить наименьший положительный элемент в списке. Элемент списка структура. Структура содержит информацию о 
товарах, поля: название, цена. (char*, double).
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
int main()
{
	setlocale(LC_ALL, "rus");
	char a;
	FILE* f;
	cout << "Введите элементы стека в файл input.txt\n";
	system("pause");
	if (!(f = fopen("input.txt", "r")))
	{
		printf("Ошибка. Не удалось открыть файл input.\n");
		system("pause");
		return 0;
	}
	List A(f);
	cout << "Список:\n";
	A.OutputConsole();
	A.Menu();
	cin >> a;
	while (a != '1' && a != '2' && a != 'E')
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> a;
	}
	while (a != 'E')
	{
		if (a == '1' && A.Is_Empty() == NULL)
		{
			A.DeleteElement(A.Search());
			if (A.Is_Empty() == NULL)
			{
				cout << "Результат:\n";
				A.OutputConsole();
			}
		}
		if (a == '2' && A.Is_Empty() == NULL)
			A.OutputConsole();
		if ((a == '1' || a == '2') && A.Is_Empty() != NULL)
			cout << "Список пуст.\n";
		A.Menu();
		cin >> a;
		while (a != '1' && a != '2' && a != 'E')
		{
			cout << "Неверный формат ввода. Введите ещё раз.\n";
			cin >> a;
		}
	}
	cout << "Вы успешно вышли из программы.\n";
	system("pause");
	return 0;
}