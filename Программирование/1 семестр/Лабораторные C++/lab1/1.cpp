/* ������� �������. ������ 9. ������� 1 
�������:
����� ����� ���� ������ �����(��������� � ������������� �����).
�����:
-5673 - 21
123 - 6
4 - 4
1987659 - 45
*/

//��������
#include <iostream>
using namespace std;
int main()
{
	int a, b=0;
	setlocale(LC_ALL, "rus");
	cout<<"������� ����� �����\n"
	cin >> a;
	if (a<0)
	{
		a *= -1;
	}
	while (a!=0)
	{
		b = a % 10 + b;
		a /= 10;
	}
	cout <<"����� ���� ����� ����� "<<b<<endl;
	system("pause");
	return 0;
}