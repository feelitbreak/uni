/* ������� �������. ������ 9. ������ 33
�������:
����� �� ���������� ������� strlen
*/

//�� ��������
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	char str[256];
	cout << "������� ������.\n";
	cin >> str;
	int len;
	__asm
	{
		lea esi, str
		push esi
		call strlen
		pop edx
		mov len, eax
	}
	cout << "����� ������ = ";
	cout << len << endl;
	system("pause");
	return 0;
}