/* ������� ������� ����������. ������ 9. �����������, ������� 2*/
#include <iostream>
#include <ctime>
using namespace std;
void VvodFloat(float *A, int n) //���� ������� � ���������� float �������� (�� -50 �� 50)
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (float)rand() / (float)RAND_MAX * 100 + -50;
}
void VvodChar1(char *A, int n) //���� ������� � ���������� char �������
{
	for (int i = 0; i < n; i++)
		cin >> A[i];
}
void VvodChar2(char *A, int n) //���� ������� � ���������� char ��������
{
	srand((int)time(NULL));
	for (int i = 0; i < n; i++)
		A[i] = (33 + rand() % (126 - 33));
}
void VivodFloat(float *A, int n) //����� ������� � ���������� float
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
void VivodInt(int *A, int n) //����� ������� � ���������� int
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
void VivodChar(char *A, int n) //����� ������� � ���������� char
{
	for (int i = 0; i < n; i++)
		cout << A[i] << " ";
	cout << endl;
}
void Otrezki(float *A, float *C, int n) //����� ���������, ������������� ������� (5, 17] ��� [25,45)
{
	int k = 0;
	for (int i = 0; i < n; i++)
		if ((A[i] > 5 && A[i] <= 17) || (A[i] >= 25 && A[i] < 45))
		{
			C[k] = A[i];
			k++;
		}
}
void Copy(float *A, int *C, int n) //����������� ��������� ������� float � ������ ������ � ����������� �� ������
{
	for (int i = 0; i < n; i++)
		C[i] = A[i];
}
int Rasstoyanie(char *A, int n) //����� ����������� ���������� ����� ����������� ����������
{
	int max = 0;
	for (int i = 0; i < n; i++)
		for (int j = n - 1; j > i; j--)
			if (A[j] == A[i])
				if (max < j - i)
					max = j - i;
	return max;
}
int Kolichestvo(char *A, int n) //����� ���-�� �������� A-D � �������
{
	int a = 0;
	for (int i = 0; i < n; i++)
		if (A[i] >= 65 && A[i] <= 68)
			a++;
	return a;
}
int main()
{
	setlocale(LC_ALL, "rus");
	int n, m, n2 = 0, a;
	cout << "������� ����������� �������_1\n";
	cin >> n;
	if (n < 1)
	{
		cout << "������ ������� �����������\n";
		system("pause");
		return 0;
	}
	cout << "������� ����������� �������_2\n";
	cin >> m;
	if (m < 1)
	{
		cout << "������ ������� �����������\n";
		system("pause");
		return 0;
	}
	char *B = new char[m];
	cout << "��� �� ������ ������ �������� �������_2? ������� 1, ���� �������, � 2, ���� ��������\n";
	cin >> a;
	while (!(a == 1 || a == 2))
	{
		cout << "������ �������. ������� ��� ���\n";
		cin >> a;
	}
	if (a == 1)
	{
		cout << "������� �������� �������_2\n";
		VvodChar1(B, m);
	}
	else VvodChar2(B, m);
	float *A = new float[n];
	VvodFloat(A, n);
	cout << "������_1:" << endl;
	VivodFloat(A, n);
	cout << "������_2:" << endl;
	VivodChar(B, m);
	for (int i = 0; i < n; i++)
		if ((A[i] > 5 && A[i] <= 17) || (A[i] >= 25 && A[i] < 45))
			n2++;
	if (n2 == 0)
		cout << "��������� �� �������_1, �������� ������� ����������� ������ �� �������� (5, 17] ��� [25,45), �� ����������." << endl;
	else
	{
		float *C = new float[n2];
		cout << "�������� �� �������_1, �������� ������� ����������� ������ �� �������� (5, 17] ��� [25,45):" << endl;
		Otrezki(A, C, n);
		VivodFloat(C, n2);
		n2 = 0;
		delete (C);
	}
	int *C = new int[n];
	Copy(A, C, n);
	cout << "���������� �� ����� �������� �� �������_1:" << endl;
	VivodInt(C, n);
	delete (C);
	delete (A);
	a = Rasstoyanie(B, m);
	if (a == 0)
		cout << "� �������_2 ��� ���������� ���������." << endl;
	else
		cout << "����� ������� ���������� ����� ����������� ���������� �������_2 = " << a << endl;
	a = Kolichestvo(B, m);
	cout << "������� A-D � �������_2 ����������� " << a << " ���(�)." << endl;
	delete (B);
	system("pause");
	return 0;
}