
#include <iostream>
using namespace std;
int main()
{
	int a, b, c, max;
	cout << "Vvedite tri naturalnih chisla\n";
	cin >> a >> b >> c;
	if (a%2==1 && b%2==1 && c%2==1)
	{
		cout << "Vse chisla yavliayutsa nechetnimi";
	}
	else
	{
		if (a%2==0)
		{
			max=a;
		}
		if (b%2==0)
		{
			max = b;
		}
		if (c % 2 == 0)
		{
			max = c;
		}
		if (a%2==0 && a>max)
		{
			max = a;
		}
		if (b % 2 == 0 && b > max)
		{
			max = b;
		}
		if (c % 2 == 0 && c > max)
		{
			max = c;
		}
		cout << "Naibolshee chetnoe chislo = " << max << endl;
	}
	system("pause");
	return(0);
}