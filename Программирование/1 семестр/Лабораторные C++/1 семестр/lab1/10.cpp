/* ������� �������. ������ 9. ������� 10
�������:
���������� ������ ����.
�����:
11112203791114 - 7 ����
123 - 3 �����
4444 - 1 �����
*/
#include <iostream>
using namespace std;
int main()
{
	int b, k, x = 0;
	long a, c;
	setlocale(LC_ALL, "rus");
	cout << "������� �����\n";
	cin >> a;
	c = a;
	for (int i = 0; i <= 9; i++)
	{
		a = c;
		k = 0;
		while (a)
		{
			b = a % 10;
			if (b == i)
			{
				k++;
			}
			a /= 10;
		}
		if (k != 0)
			x++;
	}
	cout << "���������� ��������� ���� = " << x << endl;
	system("pause");
	return 0;
}