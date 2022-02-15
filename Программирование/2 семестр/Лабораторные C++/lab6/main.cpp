/* Кендысь Алексей. Группа 9. 1 курс, 2 семестр. C++, Лаб 6. Индивидуальное задание 5
Условие:
Container1 - «Студенты факультета», Container2 - «Студенты имеющие задолженности по сессии» и
Container3 - «Студенты, сдавшие сессию».
Функции:
1. Сформировать массив Container3, как разность Container1 и Container2, содержащий данные только
о тех студентах, которые присутствуют в первом массиве и отсутствуют во втором.
2. Поиск в Container3 студентов-однофамильцев
3. Сортировка Container3 по полю Count.
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "header.h"
#include <fstream>
using namespace std;
ifstream fin;
ofstream fout;
int main()
{
	setlocale(LC_ALL, "rus");
	MASSIV<int> I1(7);

	fin.open("input4.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input4.txt\n";
		system("pause");
		return 0;
	}
	fin >> I1;
	fin.close();
	cout << "Массив на int I1:\n";
	cout << I1;
	cout << "Индекс элемента, равного 6 - " << I1.Search(6) << endl;
	cout << "Сортировка массива I1:\n";
	I1.Sort();
	cout << I1;
	MASSIV<int> I2(3);
	fin.open("input5.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input5.txt\n";
		system("pause");
		return 0;
	}
	fin >> I2;
	fin.close();
	cout << "Массив на int I2:\n";
	cout << I2;
	MASSIV<int> I3;
	I3.Difference(I1, I2);
	cout << "Разность массивов I1 и I2:\n" << I3;
	MASSIV <Element1> M(6);
	fin.open("input1.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input1.txt\n";
		system("pause");
		return 0;
	}
	fin >> M;
	cout << "Массив Container1:\n";
	cout << M;
	fin.close();
	fout.open("output1.txt");
	if (!fout.is_open())
	{
		cout << "Не удалось открыть файл output1.txt\n";
		system("pause");
		return 0;
	}
	fout << M;
	cout << "Массив Container1 был также записан в текстовый файл output1.txt\n";
	fout.close();
	MASSIV <Element2> K(5);
	fin.open("input2.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input2.txt\n";
		system("pause");
		return 0;
	}
	fin >> K;
	cout << "Массив Container2:\n";
	cout << K;
	fin.close();
	fout.open("output2.txt", ios::binary);
	if (!fout.is_open())
	{
		cout << "Не удалось открыть файл output2.txt\n";
		system("pause");
		return 0;
	}
	K.WriteBin(fout);
	MASSIV <Element3> R;
	R.Difference(M, K);
	cout << "Результат операции разности. Массив Container3:\n";
	cout << R;
	cout << "Оператор []. Второй элемент массива Container3:\n";
	cout << R[1] << endl;
	cout << "Оператор =. Создаем новый массив Container3 и приравниваем к существующему:\n";
	MASSIV <Element3> R2;
	R2 = R;
	cout << R2;
	fin.open("input3.txt");
	if (!fin.is_open())
	{
		cerr << "Не удалось открыть файл input3.txt\n";
		system("pause");
		return 0;
	}
	MASSIV <Element3> G(7);
	fin >> G;
	fin.close();
	cout << "Новый массив Container3:\n";
	cout << G;
	cout << "Студент_ка, имеющая_ий одну фамилию с третьим элементом:\n";
	int k = G.Search(G[2]);
	if (k) 
		cout << G[k-1] << endl;
	else cout << "Не найдено.\n";
	G.Sort();
	cout << "Сортировка массива Container3 по Count:\n";
	cout << G;
	system("pause");
	return 0;
}