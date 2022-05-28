/* Кендысь Алексей. Группа 9. Лекция 30. Четные числа в списке
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>
#include <iomanip>
#include <string>
using namespace std;
#define MAXLEN 200
struct ELEMENT
{
	int Numb;
	ELEMENT* next, *pred;
};
ELEMENT* InsertItem(int newnumb, ELEMENT* where)
{
	ELEMENT* h;
	h = new ELEMENT[1];
	h->Numb = newnumb;
	h->next = (where) ? where->next : NULL;
	h->pred = where;
	if (h->next) h->next->pred = h;
	if (h->pred) where->next = h;
	return h;
}
ELEMENT* SearchAndPrint(ELEMENT* head)
{
	ELEMENT* m = head;
	while (m != NULL)
	{
		if (m->Numb % 2 == 0)
			cout << m->Numb << " ";
		m = m->next;
	}
	return (head);
}
ELEMENT* Input(void)
{
	int a;
	ELEMENT* r = NULL, *head = NULL;
	cout << "Введите числа, если хотите выйти, то введите 0.\n";
	cin >> a;
	while (a != 0)
	{
		r = InsertItem(a, r);
		if (!head) head = r;
		cin >> a;
	}
	return head;
}
int main()
{
	setlocale(LC_ALL, "rus");
	ELEMENT* head = NULL;
	head = Input();
	cout << "Чётные числа: ";
	SearchAndPrint(head);
	system("pause");
	return 0;
}