/* ������� �������. ������ 9. ���������, ��� 2. �������������� ������� 5
�������:
������ �����, ���������� �� ��� �� 8 ����. ����� (� �����) ������ ������� �������� ����, �������� ����� 5.
�����:
100        1005
4567       45657
-22222     -222522
171717     17157175
-7654321   -765543251
-12345678  -1235456578
99         ������
-25        ������
-123456789 ������
*/
#include <iostream>
#include <Math.h>
using namespace std;
int main()
{
	int a, a1, d1, d10, d100;
	d10 = 10;
	setlocale(LC_ALL, "Russian");
	cout << "������� �����\n";
	cin >> a;
	if ((a<100 && a>-100) || a>=100000000 || a<=-100000000)
	{
		cout << "������. ����� ������ ��������� �� ��� �� ������ ����" << endl;
		system("pause");
		return 0;
	}
	__asm
	{
		    mov eax, a
		    cmp eax, 0
		    jge polozhitelnoe
		    mov ecx, -1
		    imul ecx
		    mov esi, 1
		polozhitelnoe :
			mov a1, eax
			mov ecx, 1
		    cmp a1, 999
			mov eax, ecx
			mul d10
			cmovg ecx, eax
			cmp a1, 9999
			mul d10
			cmovg ecx, eax
			cmp a1, 99999
			mul d10
			cmovg ecx, eax
			cmp a1, 999999
			mul d10
			cmovg ecx, eax
			cmp a1, 9999999
			mul d10
			cmovg ecx, eax
			mov eax, a1
		zanovo :
			mov edx, 0
			div ecx
			mov ebx, edx
			mul d10
			add eax, 5
			mul ecx
			add ebx, eax
			mov eax, ecx
			mov edx, 0
			mov ecx, 1000
			div ecx
			mov ecx, eax
			mov eax, ebx
			cmp ecx, 0
			jg zanovo
		    cmp esi, 1
			jne polozhitelnoe2
			mov ecx, -1
			imul ecx
		polozhitelnoe2 :
		    mov a, eax
	}
	cout << "��������� = "<< a << endl;
	system("pause");
	return 0;
}