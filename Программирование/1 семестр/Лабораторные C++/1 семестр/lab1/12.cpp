/* ������� �������. ������ 9. ������� 12
�������:
����� ��� ������� �������� �����.
�����:
36 - 1
     2
     3
93 - 1
     3
     31 
22 - 1
     2
	 11
1  - 1
59 - 1
     59
*/
#include<iostream>
using namespace std;
int main()
{
	int N, a, b;
	setlocale(LC_ALL, "rus");
	cout << "������� �����\n";
	cin >> N;
	b = N;
	cout <<"��� ������� �������� ����� �����:\n1" << endl;
	for (int i = 2; i <= b; i++)
	{
		if (N%i == 0)
		{
			while (N%i == 0)
			{
				N /= i;
			}
			cout << i << endl;
		}
	}
	system("pause");
	return 0;
}