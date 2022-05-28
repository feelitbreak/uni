/* Кендысь Алексей Максимович. Группа 9. Контрольная, вариант 2*/
#include <iostream>
#include <ctime>
using namespace std;
void VvodFloat(float *A, int n) //ввод массива с элементами float рандомом (от -50 до 50)
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (float)rand() / (float)RAND_MAX * 100 + -50;
}
void VvodChar1(char *A, int n) //ввод массива с элементами char вручную
{
	for (int i = 0; i < n; i++)
		cin >> A[i];
}
void VvodChar2(char *A, int n) //ввод массива с элементами char рандомом
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (33 + rand() % (126 - 33));
}
void VivodFloat(float *A, int n) //вывод массива с элементами float
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
void VivodInt(int *A, int n) //вывод массива с элементами int
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
void VivodChar(char *A, int n) //вывод массива с элементами char
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
void Otrezki(float *A, float *C, int n) //поиск элементов, принадлежащих отрезку (5, 17] или [25,45)
{
	int k = 0;
	for (int i = 0; i < n; i++)
		if ((A[i] > 5 && A[i] <= 17) || (A[i] >= 25 && A[i] < 45))
		{
			C[k] = A[i];
			k++;
		}
}
void Copy(float *A, int *C, int n) //копирование элементов массива float в другой массив с округлением до целого
{
	for (int i = 0; i < n; i++)
		C[i] = A[i];
}
int Rasstoyanie(char *A, int n) //поиск наибольшего расстояния между одинаковыми элементами
{
	int max = 0;
	for (int i = 0; i < n; i++)
		for (int j = n - 1; j > i; j--)
			if (A[j] == A[i])
				if (max < j - i)
					max = j - i;
	return max;
}
int Kolichestvo(char *A, int n) //поиск кол-ва символов A-D в массиве
{
	int a = 0;
	for (int i = 0; i < n; i++)
		if (A[i] >= 65 && A[i] <= 68)
			a++;
	return a;
}
int main()
{
	setlocale(LC_ALL, "rus");
	int n, m, n2 = 0, a;
	cout << "Введите размерность массива_1\n";
	cin >> n;
	if (n < 1)
	{
		cout << "Ошибка формата размерности\n";
		system("pause");
		return 0;
	}
	cout << "Введите размерность массива_2\n";
	cin >> m;
	if (m < 1)
	{
		cout << "Ошибка формата размерности\n";
		system("pause");
		return 0;
	}
	char *B = new char[m];
	cout << "Как вы хотите ввести элементы массива_2? Введите 1, если вручную, и 2, если случайно\n";
	cin >> a;
	while (!(a == 1 || a == 2))
	{
		cout << "Ошибка формата. Введите ещё раз\n";
		cin >> a;
	}
	if (a == 1)
	{
		cout << "Введите элементы массива_2\n";
		VvodChar1(B, m);
	}
	else VvodChar2(B, m);
	float *A = new float[n];
	VvodFloat(A, n);
	cout << "Массив_1:" << endl;
	VivodFloat(A, n);
	cout << "Массив_2:" << endl;
	VivodChar(B, m);
	for (int i = 0; i < n; i++)
		if ((A[i] > 5 && A[i] <= 17) || (A[i] >= 25 && A[i] < 45))
			n2++;
	if (n2 == 0)
		cout << "Элементов из массива_1, значения которых принадлежат одному из отрезков (5, 17] или [25,45), не существует." << endl;
	else
	{
		float *C = new float[n2];
		cout << "Элементы из массива_1, значения которых принадлежат одному из отрезков (5, 17] или [25,45):" << endl;
		Otrezki(A, C, n);
		VivodFloat(C, n2);
		n2 = 0;
		delete (C);
	}
	int *C = new int[n];
	Copy(A, C, n);
	cout << "Округлённые до целых элементы из массива_1:" << endl;
	VivodInt(C, n);
	delete (C);
	delete (A);
	a = Rasstoyanie(B, m);
	if (a == 0)
		cout << "В массиве_2 нет одинаковых элементов." << endl;
	else
		cout << "Самое большое расстояние между одинаковыми элементами массива_2 = " << a << endl;
	a = Kolichestvo(B, m);
	cout << "Символы A-D в массиве_2 встречаются " << a << " раз(а)." << endl;
	delete (B);
	system("pause");
	return 0;
}