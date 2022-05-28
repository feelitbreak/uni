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
	cout << "Что вы хотите сделать?\n";
	cout << "0 - Ввести список из файла.\n";
	cout << "1 - Просмотреть состояние списка.\n";
	cout << "2 - Удалить несколько элементов с заданной позиции.\n";
	cout << "3 - Добавить несколько элементов в заданную позицию c другого list.\n";
	cout << "4 - Удалить парные элементы, unique.\n";
	cout << "5 - Подсчитать сколько элементов одного массива больше соответствующих элементов другого массива, inner_product.\n";
	cout << "6 - Поиск в списке два одинаковых смежных элемента, adjacent_find.\n";
	cout << "7 - Разделить список на две группы, partition.\n";
	cout << "8 - Перемножить поэлементно два списка, transform.\n";
	cout << "9 - Просуммировать поэлементно два списка, transform.\n";
	cout << "a - Поменять знаки элементов на противоположный, transform.\n";
	cout << "b - Поиск наибольшего отсортированного диапазона, is_sorted_until.\n";
	cout << "c - Вычислить произведение элементов, accumulate.\n";
	cout << "d - Заполнить массив случайными числами в интервале [0; Х), generate.\n";
	cout << "e - Разделить элементы на две группы, sort.\n";
	cout << "f - Найти в массиве целых чисел подпоследовательность, каждый элемент которой равен квадрату другого массива, search.\n";
	cout << "g - Увеличить все элементы на Х, transform.\n";
	cout << "h - Удалить элементы по условию, remove_if.\n";
	cout << "i - Подсчёт элементов, делящихся на 3, count_if.\n";
	cout << "j - Вычислить сумму квадратов элементов, accumulate.\n";
	cout << "k - Удалить элементы >k, remove_if.\n";
	cout << "l - Сгенерировать все перестановки элементов массива, next_permutation.\n";
	cout << "E - Выйти из программы.\n";
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
	cout << "Список:\n";
	copy(l.begin(), l.end(), ostream_iterator<int>(cout, " "));
	cout << endl;
};
void DeleteElements(list<int> &l)
{
	int a, b;
	cout << "Введите позицию.\n";
	cin >> a;
	while (a < 1 || a > (int)l.size())
	{
		cout << "Не удалось найти позицию. Введите ещё раз.\n";
		cin >> a;
	}
	cout << "Введите кол-во элементов.\n";
	cin >> b;
	while (b < 1 || a+b-1 > (int)l.size())
	{
		cout << "Ошибка. Введите ещё раз.\n";
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
	cout << "Введите позицию.\n";
	cin >> a;
	while (a < 1 || a>(int)l.size()+1)
	{
		cout << "Не удалось найти позицию. Введите ещё раз.\n";
		cin >> a;
	}
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	Output(l2);
	cout << "Введите кол-во элементов.\n";
	cin >> b;
	while (b < 1 || b>(int)l2.size())
	{
		cout << "Ошибка. Введите ещё раз.\n";
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
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	Output(l2);
	list<int>::iterator i = l.begin();
	list<int>::iterator j = i;
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	advance(j, min);
	list<int>::iterator k = l2.begin();
	int a = inner_product(i, j, k, 0, plus<int>(), greater<int>());
	//i - начало первого списка, j - итератор первого списка, до которого нужно выполнять, k - итератор начала второго списка
	cout << "Результат = " << a << endl;
	//работает изначально как скалярное умножение, если последние два аргумента в функции не писать
	//четвертый параметр добавляет какое то число к общей сумме
	//x=(x1,x2,x3) y=(y1,y2,y3)
	//inner_product(i,j,k,0) = x1*y1+x2*y2+x3*y3
	//пятый параметр меняет плюс в скалярной на то, что хочешь, в нашем оставляем плюс
	//шестой параметр меняет умножение, мы ставим оператор >
	//значит получаем a = (x1>y1)+(x2>y2)+(x3>y3)
	//это то, что и просили
};
void SearchPair(list<int>& l)
{
	list<int>::iterator i = adjacent_find(l.begin(), l.end());
	if (i != l.end())
		cout << "Первый смежный элемент = " << *i << endl;
	else cout << "Не удалось найти смежных элементов.\n";
};
void Divide(list<int>& l)
{
	partition(l.begin(), l.end(), bind2nd(greater<int>(), 5));
};
void MultiplyLists(list<int>& l)
{
	list<int> l2;
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	Output(l2);
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	list<int>::iterator i = l.begin();
	advance(i, min);
	transform(l.begin(), i, l2.begin(), l.begin(), multiplies<int>()); 
	//первое - начало первого листа, 
	//второе - итератор первого листа, до какого элемента умножать, 
	//третье - начало второго листа
	//четвертое - начало листа, куда записывать результат
};
void Sum(list<int>& l)
{
	list<int> l2;
	cout << "Введите второй список в файл input2.txt\n";
	system("pause");
	if (!Input(l2, "input2.txt"))
	{
		cout << "Не удалось открыть файл input2.txt\n";
		return;
	}
	if (l2.empty())
	{
		cout << "Ошибка. Второй список пуст.\n";
		return;
	}
	Output(l2);
	int min = (l.size() < l2.size()) ? l.size() : l2.size();
	list<int>::iterator i = l.begin();
	advance(i, min);
	transform(l.begin(), i, l2.begin(), l.begin(), plus<int>());
	//первое - начало первого листа, 
	//второе - итератор первого листа, до какого элемента умножать, 
	//третье - начало второго листа,
	//четвертое - начало листа, куда записывать результат
};
void ChangeSign(list<int>& l)
{
	transform(l.begin(), l.end(), l.begin(), negate<int>());
};
void FindSorted(list<int>& l)
{
	list<int>::iterator i = is_sorted_until(l.begin(), l.end());
	cout << "Наибольший отсортированный диапозон:\n";
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
	cout << "Произведение элементов = " << a << endl;
	//третий элемент добавляет к результату. если умножение, то умножает на него, если сложение, то прибавляет, и т.д.
};
int X;
int RandomNumber()
{
	return (rand() % X);
}
void FillRandom(list<int>& l)
{
	cout << "Введите число X для интервала.\n";
	cin >> X;
	while (X <= 0)
	{
		cout << "Ошибка. Неверный формат интервала. Введите ещё раз.\n";
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
	//для листа sort делать нельзя, тк нет доступа к элементам прямого, но можно это для вектора или дека написать
	//тут делит на четные и нечетные элементы
};
bool Square(int x, int y)
{
	return (x*x == y);
};
void FindSquare(list<int>& l)
{
	// int A[] = { 9, 9 }; например
	cout << "Введите размерность массива.\n";
	int n;
	cin >> n;
	while (n < 1)
	{
		cout << "Неверный формат ввода. Введите ещё раз.\n";
		cin >> n;
	}
	cout << "Введите массив.\n";
	int *A = new int[n];
	for (int i = 0; i < n; i++)
		cin >> A[i];
	list<int>::iterator i = search(l.begin(), l.end(), A, A+2, Square);
	if (i == l.end())
		cout << "Не удалось найти подпоследовательность.\n";
	else cout << "Начало подпоследовательности находится на позиции " << distance(l.begin(), i)+1 << endl;
	delete[] A;
};
void AddX(list<int>& l)
{
	int x;
	cout << "Введите число X.\n";
	cin >> x;
	transform(l.begin(), l.end(), l.begin(), bind2nd(plus<int>(),x));
};
bool Remove(int x)
{
	return(x < 0);
};
void RemoveIf(list<int>& l)
{
	// list<int>::iterator i = remove_if(l.begin(), l.end(), [](int i)->bool {return (i < 0); }); через лямбда выражение
	list<int>::iterator i = remove_if(l.begin(), l.end(), Remove);
	if (i != l.end())
		l.erase(i, l.end());
	//Удаляет все элементы меньше нуля
};
bool Count(int x)
{
	return (x % 3 == 0);
};
void CountIf(list<int>& l)
{
	int a = count_if(l.begin(), l.end(), Count);
	cout << "Число элементов, делящихся на 3, равно " << a << endl;
};
int Sum2(int x, int y)
{
	return x * x+ y * y;
}
void FindSquareSum(list<int>& l)
{
	int a = accumulate(l.begin(), l.end(), 1 , Sum2);
	cout << "Сумма квадратов элементов равна " << a << endl;
	//третий параметр - начальное значение суммы
	//это из ее примера, но это на самом деле неправильно вроде как, accumulate не так работает
	//если например брать функцию x*10 + y и массив A= {1, 2, 3}
	//то оно сразу умножит первый элемента на 10 и добавит второй, т.е. получит 12, а потом умножит это на 10 и добавит третий уже, то есть будет 123
	//результатом этого будет именно 123
	//поэтому сумму квадратов по идее никак с accumulate найти не получится
	//оно берет квадрат первого, добавляет квадрат второго, потом берет квадрат этой суммы и добавляет уже квадрат третьего, получается огромное число
};
void RemoveIf2(list<int>& l)
{
	cout << "Введите число k.\n";
	int k;
	cin >> k;
	list<int>::iterator i = remove_if(l.begin(), l.end(), bind2nd(greater<int>(), k));
	if (i != l.end())
		l.erase(i, l.end());
};
void Permutations(list<int>& l)
{
	cout << "Все перестановки списка:\n";
	list<int> l2 = l;
	l2.sort();
	do {
		copy(l2.begin(), l2.end(), ostream_iterator<int>(cout, " "));
		cout << endl;
	} while (next_permutation(l2.begin(), l2.end()));
	//оно перестановки по возрастанию делает, поэтому лучше отсортировать сразу список
};