#include <iostream>
using namespace std;
class Ar21
{
private:
	int *M;
	int count;
public:
	Ar21();
	Ar21(int);
	Ar21(int, int);
	Ar21(const Ar21&);
	Ar21& operator=(const Ar21&);
	int& operator[](int);
	friend istream& operator>>(istream&, Ar21&);
	friend ostream& operator<<(ostream&, Ar21&);
	void Sort();
	int Multiply();
	int Increase();
	~Ar21();
};