/* Кендысь Алексей. Группа 9. Контрольная работа. Вариант 2
*/
#include <iostream>
#include <iomanip>
#include <ctime>
using namespace std;
void Vvod1(int *A, int n)
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = rand() % 200 - 100;
}
void Vvod2(int **A, int n, int m)
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			A[i][j] = (int)rand() % 3 - 1;
}
void Vvod3(short *A, int n)
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (short)rand() % 27;
}
void Vivod1(int *A, int n)
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
}
void Vivod2(int **A, int n, int m)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
			cout << setw(5) << A[i][j];
		cout << endl;
	}
}
void Vivod3(short *A, int n)
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
}
int main()
{
	short a, b, c, d, y;
	setlocale(LC_ALL, "Russian");
	cout << "Введите четыре числа.\n";
	cin >> a >> b >> c >> d;
	__asm
	{
		mov ax, a
		imul b
		sub ax, c
		mov bx, ax
		mov ax, d
		mov dx, 0
		cwd
		idiv b
		add ax, bx
		mov y, ax
	}
	cout << "Результат выражения = " << y << endl;
	cout << "Введите размерность массива.\n";
	int n, res1;
	cin >> n;
	if (n < 1)
	{
		cout << "Ошибка. Неверный формат ввода.\n";
		system("pause");
		return 0;
	}
	int *A = new int[n];
	Vvod1(A, n);
	cout << "Матрица1: ";
	Vivod1(A, n);
	cout << endl;
	__asm
	{
		mov eax, 0
		mov esi, A
		mov ecx, n
		m:
		mov ebx, [esi]
		add eax, ebx
		add esi, 4
		loop m
		mov ebx, n
		cdq
		idiv ebx
		mov res1, eax
	}
	cout << "Среднее арифмитеческое элементов массива1 = "<<res1<<endl;
	delete A;
	int **B;
	cout << "Введите размерность матрицы.\n";
	int M, N;
	cin >> M >> N;
	if (M <= 1 || N < 1)
	{
		cout << "Ошибка. Неверный формат ввода.\n";
		system("pause");
		return 0;
	}
	B = new  int *[M];
	for (int i = 0; i < M; i++)
		B[i] = new int[N];
	Vvod2(B, M, N);
	cout << "Матрица:\n";
	Vivod2(B, M, N);
	int k = 0;
	__asm
	{
		mov esi, B
		mov eax, M
		sub eax, 2
		mov ebx, 4
		mul ebx
		add esi, eax
		mov edi, [esi]
		mov ecx, N
		m2:
		mov eax, [edi]
		add edi, 4
		cmp eax, 1
		jne prod
		inc k
		prod:
		loop m2
	}
	cout << "Количество единиц в предпоследней строке = " << k << endl;
	for (int i = 0; i < M; i++)
		delete (B[i]);
	delete (B);
	cout << "Введите размерность массива2.\n";
	int m;
	cin >> m;
	if (m < 1)
	{
		cout << "Ошибка. Неверный формат ввода.\n";
		system("pause");
		return 0;
	}
	short *C;
	C = new  short[m];
	Vvod3(C, m);
	cout << "Матрица2: ";
	Vivod3(C, m);
	cout << endl;
	int e = 0;
	__asm
	{
		mov ecx, m
		lea esi, C
		m3:
		lodsb
		cmp al, 25
		jne prod2
		mov e, ecx
		prod2:
		loop m3
	}
	if (e == 0)
		cout << "Не удалось найти элемент со значением 25.\n";
	else
		cout << "Первое вхождение элемента 25 - " << e << endl;
	delete (C);
	system("pause");
	return 0;
}