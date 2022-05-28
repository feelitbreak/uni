#include <iostream>
using namespace std;
struct list
{
	int field;
	list *ptr;
};
list* search(list * lst, int x)
{
	list* p;
	p = lst;
	do 
	{
		p = p->ptr;
	} while (p != lst && p->field != x);
	if (p == lst && p->field != x)
	{
		cout << "Ёлемент не был найден" << endl;
		return 0;
	}
	else return p;
}
