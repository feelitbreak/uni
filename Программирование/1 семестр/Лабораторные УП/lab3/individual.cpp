/* Кендысь Алексей. Группа 9. Ассемблер, Лаб 3. Индивидуальное задание 5
Условие:
Упорядочить элементы последовательности A=(ai), i=1..n, по возрастанию (убыванию), используя метод подсчeта;
Тесты:
n=10    Последовательность: 137 -46 29 170 116 -177 -59 -143 -107 -194
Результат: -194 -177 -143 -107 -59 -46 29 116 137 170
n=20    Последовательность: 5 35 71 28 54 -26 15 -54 93 99 -16 -23 8 80 -44 -91 -18 36 -18 93
Результат: -91 -54 -44 -26 -23 -18 -18 -16 5 8 15 28 35 36 54 71 80 93 93 99
n=40    Последовательность: -80 -19 -75 44 16 -63 47 -37 -25 -75 -24 -82 37 -22 50 -82 -60 -42 -79 57 -75 36 75 1 -6 -25 -31 -15 -34 -1 70 46 -94 56 -91 26 82 -2 -40 63
Результат: -94 -91 -82 -82 -80 -79 -75 -75 -75 -63 -60 -42 -40 -37 -34 -31 -25 -25 -24 -22 -19 -15 -6 -2 -1 1 16 26 36 37 44 46 47 50 56 57 63 70 75 82
n=-1   Ошибка
*/
#include <iostream>
#include <ctime>
using namespace std;
void Sort_Count(int *A, int *B, int n)
{
	int k, d;
	for (int i = 0; i < n; i++)
	{
		k = 0, d = 0;
		for (int j = 0; j < n; j++)
			if (A[i] > A[j])
				k++;
		while (B[k + d] == A[i])
			d++;
		B[k + d] = A[i];
	}
}
int main()
{
	int n, k, d = 0;
	setlocale(LC_ALL, "Russian");
	cout << "Введите n\n";
	cin >> n;
	if (n < 1)
	{
		cout << "Ошибка. Количество элементов должно быть натуральным числом\n";
		system("pause");
		return 0;
	}
	int *A = new int[n];
	int *B = new int[n];
	/*cout << "Введите элементы последовательности" << endl;
	for (int i = 0; i < n; i++)
	cin >> A[i];*/
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = rand() % 200 - 100;
	cout << "Последовательность: ";
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
	__asm
	{
		mov ecx, n
		mov esi, A
		loop1 :
		mov ebx, ecx
			mov k, 0
			mov ecx, n
			mov edi, A
			loop2 :
		mov eax, [esi]
			mov edx, [edi]
			cmp eax, edx
			jng end1
			add k, 4
			end1 :
			add edi, 4
			loop loop2
			mov edi, B
			add edi, k
			mov eax, [esi]
			while1 :
			cmp[edi], eax
			jne end2
			add edi, 4
			jmp while1
			end2 :
		mov[edi], eax
			mov ecx, ebx
			add esi, 4
			loop loop1
	}
	/*Sort_Count(A, B, n);*/
	cout << "Результат: ";
	for (int i = 0; i < n; i++)
		cout << B[i] << " ";
	cout << endl;
	delete A;
	delete B;
	system("pause");
	return 0;
}