//Кендысь Алексей. Группа 9. Лекция 32
//у = (3.5 + 6.5)*100. / 3.75 - 50.75
#include <iostream>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	double y, a1 = 3.5, a2 = 6.5, a3 = 100., a4 = 3.75, a5 = 50.75;
	__asm 
	{
		fld a1
		fadd a2
		fmul a3
		fdiv a4
		fsub a5
		fst y
	}
	cout <<"На ассемблере: "<< y << endl;
	y = (3.5 + 6.5)*100. / 3.75 - 50.75;
	cout << "На c++: " << y << endl;
	system("pause");
	return 0;
}