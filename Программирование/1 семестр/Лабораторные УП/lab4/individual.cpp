/* Кендысь Алексей. Группа 9. Ассемблер, Лаб 4. Индивидуальное задание 5
Условие:
Разработать программу, запрашивающую у пользователя матрицу размером MxN. Выполнить поворот ее на 180 градусов. Размерность матрицы считать с консоли, элементы матрицы - случайным образом.
Результаты вывести на консоль. Элементы матрицы целые беззнаковые числа.
Тесты:
1)M=4
  N=5
  Матрица:
	 73   79   65   37   76
	 36    9    2   27   20
	 84   61   24   80   22
	 39   64   72   79   31
  Результат:
	 31   79   72   64   39
	 22   80   24   61   84
	 20   27    2    9   36
	 76   37   65   79   73
2)M=3
  N=8
  Матрица:
	  2   25    7   31   40   20   86   59
	 30   57   46   10   58   91    6   82
	 38   44   73   27   17   92   61   38
  Результат:
	 38   61   92   17   27   73   44   38
	 82    6   91   58   10   46   57   30
	 59   86   20   40   31    7   25    2
3)M=5
  N=3
  Матрица:
	 65   50   82
	 51   78    3
	 20   12    8
	 49   96   99
	 73   25   36
  Результат:
	 36   25   73
	 99   96   49
	  8   12   20
	  3   78   51
	 82   50   65
4)M=-3
  Ошибка
*/
#include <iostream>
#include <iomanip>
#include <ctime>
using namespace std;
void Vvod(unsigned int **A, int n, int m)
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			A[i][j] = (unsigned int)rand() % 100;
}
void Vivod(unsigned int **A, int n, int m)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
			cout << setw(5) << A[i][j];
		cout << endl;
	}
}
int main()
{
	int M, N;
	setlocale(LC_ALL, "Russian");
	cout << "Введите M\n";
	cin >> M;
	if (M < 1)
	{
		cout << "Ошибка. Неверный формат\n";
		system("pause");
		return 0;
	}
	cout << "Введите N\n";
	cin >> N;
	if (N < 1)
	{
		cout << "Ошибка. Неверный формат\n";
		system("pause");
		return 0;
	}
	unsigned int **A;
	A = new unsigned int *[M];
	for (int i = 0; i < M; i++)
		A[i] = new unsigned int[N];
	Vvod(A, M, N);
	cout << "Матрица:" << endl;
	Vivod(A, M, N);
	//В C++:
	//for (int i = 0, a = M - 1; i <= a; i++, a--)
	//	for (int j = 0, b = N - 1; j < N, b >= 0; j++, b--)
	//	{
	//		if (a == i && ((b < j && N % 2 == 0) || (b == j && N % 2 == 1)))
	//			break;
	//		swap(A[i][j], A[a][b]);
	//	}
	int c, k = (M - 1) * 4;
	__asm
	{
		mov eax, N
		mov c, 2
		xor edx, edx
		div c
		mov c, edx
		mov ebx, 0                                  // i=ebx  a=k  j=eax   b=edx
		for1 : cmp ebx, k
			   jg end1
			   mov eax, 0
			   mov ecx, N
			   mov edx, N
			   dec edx
			   imul edx, 4
			   for2 : cmp k, ebx
					  jne prod
					  cmp edx, eax
					  jg prod
					  je proverka
					  cmp c, 0
					  je end1
					  jmp prod
					  proverka : cmp c, 1
								 je end1
								 prod : mov esi, A
										add esi, ebx
										mov edi, [esi]
										push edi
										mov esi, A
										add esi, k
										mov edi, [esi]
										pop esi
										push dword ptr[edi][edx]
										push dword ptr[esi][eax]
										pop dword ptr[edi][edx]
										pop dword ptr[esi][eax]
										sub edx, 4
										add eax, 4
										loop for2
										add ebx, 4
										sub k, 4
										cmp k, ebx
										jge for1
										end1 :
	}
	cout << "Результат:" << endl;
	Vivod(A, M, N);
	for (int i = 0; i < M; i++)
		delete (A[i]);
	delete (A);
	system("pause");
	return 0;
}