/* ������� �������. ������ 9. ������� 6
�������:
�������� �� ����� �������
�����:
6 - 720
-10 - ������
23 - 862453760
*/
#include <iostream>
using namespace std;
int main()
{
	int a, b;
	setlocale(LC_ALL, "rus");
	cout << "������� �����\n";
	cin >> a;
	b = 0;
	if (a <= 0 || a == 1)
	{
		cout << "����� �� �������� �������\n";
		system("pause");
		return 0;
	}
	for (int i = 2; i < a; ++i)
		if (a%i == 0)
		{
			b++;
		}
	if (b == 0)
	{
		cout << "����� �������� �������\n";
	}
	else
	{
		cout << "����� �� �������� �������\n";
	}
	system("pause");
	return 0;
}