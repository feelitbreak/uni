#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
int Abstract::Count = 0;
Liquid::Liquid(char* newName)
{
	Name = new char[256];
	strcpy(Name, newName);
};
Liquid::Liquid(char* newName, int newDensity)
	:Liquid(newName)
{
	Density = newDensity;
};
Juice::Juice(char* newName, int newDensity, int newPercentage)
	: Liquid(newName, newDensity)
{
	Percentage = newPercentage;
};
void Liquid::Show()
{
	if (Name)
		cout << "�������� - " << Name << ".";
	if (Density)
		cout << " ��������� - " << Density << ".";
	if (Name) cout << " ";
	cout << "���������� ��������� �������� - " << Count << ".";
	cout << " ������ �������� ������� - " << GetIndex() << ".";
};
const int Liquid::GetIndex()
{
	return Index;
}
int Liquid::GetDensity()
{
	return Density;
};
void Juice::Show()
{
	Liquid::Show();
	if (Percentage)
		cout << " ������� ������������� - " << Percentage << ".";
};
int Juice::GetPercentage()
{
	return Percentage;
};
Liquid:: ~Liquid()
{
	delete[] Name;
};