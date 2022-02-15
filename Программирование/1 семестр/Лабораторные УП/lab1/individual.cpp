/* ������� �������. ������ 9. ��, ��� 1. �������������� ������� 5
�������:
��������� pow(x, 5) / sqrt(1 + x) - (fabs(pow(y, 3)) + pow(z, 2)) / 2 + (y * 100) / (10*sin(x))
�����:
����             C++   ���������
x=6 y=3 z=4     2810     2810
x=3 y=-4 z=-8   -225    -225
x=6 y=-3 z=-20  2832     2832
x=1 y=-34 z=6  -20073   -20073
x=-2            ������
x=0             ������
*/
#include <iostream>
#include <Math.h>
using namespace std;
int main()
{
	int x, y, z, a, c, sinx, sqrtx, resultat;
	setlocale(LC_ALL, "Russian");
	cout << "������� �������� ���������� x, y � z\n";
	cin >> x >> y >> z;
	sinx = 10 * (1.*sin(x));
	sqrtx = (1.*sqrt(1 + x));
	if (sinx == 0 || x <= -1)
	{
		cout << "������, ������� ������ �������� x\n";
		return 0;
	}
	c = pow(x, 5) / sqrtx - (fabs(pow(y, 3)) + pow(z, 2)) / 2 + (y * 100) / sinx;
	__asm
	{
		mov eax, y
		mov ecx, y
		imul ecx
		imul ecx
		mov a, eax
	}
	a = fabs(a);
	__asm
	{
		mov eax, a
		mov ecx, eax
		mov eax, z
		imul eax
		add eax, ecx
		mov ecx, 2
		idiv ecx
		mov ebx, eax
		mov eax, x
		mov ecx, x
		imul eax
		imul eax
		imul ecx
		idiv sqrtx
		sub eax, ebx
		mov ebx, eax
		mov eax, y
		mov ecx, 100
		imul ecx
		idiv sinx
		add eax, ebx
		mov resultat, eax
	}
	cout << "�������� ��������� � C++ = " << c << endl;
	cout << "�������� ��������� � ���������� = " << resultat << endl;
	system("pause");
	return 0;
}