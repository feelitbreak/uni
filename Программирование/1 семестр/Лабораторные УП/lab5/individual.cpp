/* ������� �������. ������ 9. ���������, ��� 5. �������������� ������� 5
�������:
����������� ���������, ������� ����������� � ������������ ������. ������� ��������� *. ����������  � ��� ������� - ����� �����
� 2-� �\�. ������ ����� K (������). ������� k-� ����� �� ������. ������ �����, ����� ��� � ������. ���������� ������� �� �������.
�����:

*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
using namespace std;
extern "C" double __cdecl Udalenie(char*, char*, int, int, int);
char* Vvod1()
{
	char str[256];
	cout << "������� ������.\n";
	cin >> str;
	return str;
}
char* Vvod2(int K)
{
	char str[256];
	cout << "������� �����, ������� �� ������ ����� � �������������� ������, � 2�/�.\n";
	cin >> str;
	cout << "������� ������ �����, ������� �� ������ �������.\n";
	cin >> K;
	return str;
}
void Vivod_Chisla(int a)
{
	cout << a << endl;
}
void NeverniyFormat()
{
	cout << "������. �������� ������ �����.\n";
}
void Leksemi()
{
	cout << "�������:" << endl;
}
void VivodStroki(char *str)
{
	cout << str << endl;
}
void Str4()
{
	cout << "��������� ��������: ";
}
void Index()
{
	cout << "������ ��������� �����, ������� �� ������ �����, � �������������� ������ = ";
}
void NoIndex()
{
	cout << "�����, ������� �� �����, �� ���� �������.\n";
}
void NoIndex2()
{
	cout << "� ������ �� ���������� �������, ������� �� �����.\n";
}
int main()
{
	setlocale(LC_ALL, "rus");
	char str1[256], str2[256], str3[256], str4[256];
	int len;
	int a = 0, b = 0, K, c = 0, d = 1, e = 0, f = 0;
	__asm
	{
		call Vvod1 //���� ����� ������
		mov esi, eax
		push eax
		call strlen
		pop edx
		mov ecx, eax
		inc ecx
		mov len, eax
		/*push ds
		pop es*/
		/*cld*/
		lea edi, str1 
		rep movsb // str1 = [��������� ���� ������]
		/*push ds
		pop es*/
		/*	cld*/
		push K
		call Vvod2 //���� �����, ������� �������, � ������� �����, ������� ���� �������
		pop K // K = [���� ������]
		mov ebx, K
		cmp ebx, 1
		jge proverka1
		call NeverniyFormat //�������� �� �������� ������
		jmp end3
		proverka1 :
		mov esi, eax //� eax ����� ����� ������, ������� �� ����� ��� ������
			push eax
			call strlen
			pop edx
			mov ecx, eax
			inc ecx
			/*push ds
			pop es*/
			/*	cld*/
			lea edi, str3 
			rep movsb // str3 = [������, ������� ���� �����]
			/*push ds
			pop es*/
			/*	cld*/
			lea esi, str3
			mov ecx, eax
			m5 : lodsb
			cmp al, '0'
			je loop2
			cmp al, '1'
			je loop2
			cmp al, '\0'
			je loop2
			call NeverniyFormat //�������� ��������� ������ �� �������� ������
			jmp end3
			loop2 :
		loop m5 //����� ����� �� �������
			call Leksemi
			m2 :
		/*	push ds
				pop es*/
				/*cld*/
		mov ecx, len
			lea esi, str1
			lea edi, str2
			add esi, a
			m1 : lodsb //��������� ������� ������ � ��������� ���, ���� ������� ������� �� ������ �� 01, �� ����������� f
			cmp al, '*'
			je leksema //���� *, �� ������� �����������
			cmp al, '0'
			je meow
			cmp al, '1'
			je meow
			cmp al, '\0'
			je meow
			inc f
			meow :
		stosb
			inc a
			mov eax, a
			cmp eax, len
			je leksema
			loop m1
			leksema :
		mov ebx, f
			cmp ebx, 0
			je meow2
			mov eax, a
			cmp eax, len
			je end1
			inc a
			mov f, 0
			mov ebx, a
			mov d, ebx
			jmp m2
			meow2 :
		mov word ptr[edi], '\0'
			inc b
			lea esi, str2
			lea edi, str3
			push edi
			call strlen
			pop edx
			mov ecx, eax
			inc ecx
			rep cmpsb
			mov ebx, b
			jne prodolzhenie1
			mov e, ebx
			prodolzhenie1 :
		cmp ebx, K
			jne proverka2
			mov ebx, a
			mov c, ebx
			mov ebx, b
			cmp ebx, 1
			jne prodolzhenie2
			inc c
			prodolzhenie2 :
		push c
			push d
			proverka2 :
		lea edi, str2
			push edi
			call VivodStroki
			pop edx
			mov eax, a
			inc a
			mov ebx, a
			mov d, ebx
			cmp eax, len
			je end1
			jmp m2
			end1 :
		mov ebx, K
			cmp ebx, b
			jg abcde
			pop d
			pop c
			abcde :
		cmp e, 0
			je abcd
			call Index
			push e
			call Vivod_Chisla
			pop edx
			jmp end2
			abcd :
		call NoIndex
			end2 :
	}
	Udalenie(str1, str4, len, c, d);
	__asm
	{
		mov ebx, b
		cmp K, ebx
		jg Oshibka
		call Str4
		lea edi, str4
		push edi
		call VivodStroki
		pop edx
		jmp end3
		Oshibka :
		call NoIndex2
			end3 :
	}
	system("pause");
	return 0;
}