/* ������� �������. ������ 9. ������� 8
�������:
�������� �� ����� �������.
�����:
15 - �� �������� �������
23 - �������
479 - �������
2 - �������
1 - �� �������� �������
*/
#include <iostream>
using namespace std;
int main()
{
    int a, b, c;
	setlocale(LC_ALL, "rus");
	cout << "������� ��� �����\n" << endl;
	cin >> a >> b;
	c = a;
	while (a%c != 0 || b % c != 0)
	{
		c--;
	}
	cout << "��� ���� ���� ����� ����� " << c << ", � �� ��� ����� " << (a*b) / c << endl;
	system("pause");
	return 0;
}