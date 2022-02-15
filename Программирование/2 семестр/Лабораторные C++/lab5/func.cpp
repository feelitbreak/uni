#define _CRT_SECURE_NO_WARNINGS
#include "header.h"
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
ArrayStack::ArrayStack(int _size)
{
	size = _size;
	p = new int[_size];
	top = 0;
};
ArrayStack::ArrayStack(ArrayStack &s)
{
	size = s.size;
	top = s.top;
	for (int i = 0; i < size; i++)
		p[i] = s.p[i];
};
ArrayStack::~ArrayStack()
{
	delete[] p;
};
void ArrayStack::push(const int& n)
{
	p[top] = n;
	top++;
};
int ArrayStack::pop(void)
{
	top--;
	return p[top];
};
bool ArrayStack::IsEmpty() const
{
	if (!top)
		return true;
	else return false;
};
bool ArrayStack::IsFull() const
{
	if (top == size)
		return true;
	else return false;
};
ArrayStackIterator::ArrayStackIterator(ArrayStack& _a) : a(_a)
{
	pos = 0;
};
bool ArrayStackIterator::InRange() const
{
	if (pos >= 0 && pos < a.top)
		return true;
	else return false;
};
void ArrayStackIterator::Reset()
{
	pos = 0;
};
int& ArrayStackIterator::operator *() const
{
	return a.p[pos];
};
void ArrayStackIterator::operator ++()
{
	pos++;
};