/* Кендысь Алексей. Группа 9. Ассемблер, Лаб 6. Индивидуальное задание 5
Условие:
Функция представлена в виде своего ряда Тейлора. Вычислить приближённое значение суммы этого бесконечного ряда.
Вычисления заканчивать, когда очередное слагаемое окажется по модулю меньше заданного числа eps. Сравнить полученный
результат со значением, вычисленным через стандартные функции, x и eps запросить у пользователя перед началом вычислений.

*/
#include <iostream>
#include <math.h>
using namespace std;
int Error()
{
	printf("Ошибка ввода. X должен принадлежать промежутку (-1;1).\n");
	return 0;
}
int main()
{
	long double x;
	setlocale(LC_ALL, "Russian");
	printf("Введите значение x.\n");
	cin >> x;
	int k;
	__asm
	{
		fld1
		fld x
		fabs
		fcompp
		fstsw ax
		sahf
		jb end1
		call Error
		mov k, eax
		end1 :
	}
	if (k == 0)
	{
		system("pause");
		return 0;
	}
	long double e, y1 = 0, y2 = 0;
	long double a = 1, b = 2, d = 1, g = 2;
	printf("Введите точность вычисления суммы e.\n");
	cin >> e;
	__asm
	{// jnz - переход если неравны  jb - переход если st(0) меньше, надо обязательно добавлять sahf если два раза те же флаги проверяем
		fld e
		fld a
		fabs
		fcompp
		fstsw ax
		sahf
		jb end2
		fld y2
		fld a
		fadd
		fstp y2
		fld x
		fld b
		fdiv
		fchs
		fstp a
		slagaemoe :
		fld e
			fld a
			fabs
			fcompp
			fstsw ax
			sahf
			jb end2
			fld y2
			fld a
			fadd
			fstp y2
			fld d
			fld g
			fadd
			fstp d
			fld b
			fld g
			fadd
			fstp b
			fld a
			fld d
			fmul
			fld b
			fdiv
			fld x
			fmul
			fchs
			fstp a
			ffree st
			jmp slagaemoe
			end2 :
	}
	long double c = 1;
	for (long double i = 1, j = 2; fabs(c) >= e; i += 2, j += 2)
	{
		y1 += c;
		c *= ((-1)*x*i) / j;
	}
	long double y3, y4;
	y3 = 1 / (sqrt(1 + x));
	__asm
	{
		fld1
		fld x
		fadd
		fsqrt
		fld1
		fdivr
		fstp y4
		ffree st
	}
	printf("Результат вычисления функции по формуле Тейлора на C++ = ");
	printf("%.10Lf\n", y1);
	printf("Результат вычисления функции по формуле Тейлора на Ассемблере = ");
	printf("%.10Lf\n", y2);
	printf("Результат вычисления функции 1/корень из (1+x) на C++ = ");
	printf("%.10Lf\n", y3);
	printf("Результат вычисления функции 1/корень из (1+x) на Ассемблере = ");
	printf("%.10Lf\n", y4);
	system("pause");
	return 0;
}