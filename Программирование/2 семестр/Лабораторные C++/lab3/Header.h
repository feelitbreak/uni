#include <iostream>
using namespace std;
class Stack
{
private:
	char* ArrS;
	int top;
public:
	Stack();
	Stack(int N);
	bool Is_Empty();
	Stack(const Stack&);
	Stack& operator=(const Stack &);
	friend Stack operator+(const Stack&, const Stack&);
	Stack operator+(int);
	Stack operator-(int);
	Stack operator*(int);
	Stack operator/(int);
	Stack& operator++();
	Stack operator++(int);
	Stack& operator--();
	Stack operator--(int);
	bool operator==(const Stack&);
	bool operator!=(const Stack&);
	bool operator>=(const Stack&);
	bool operator<=(const Stack&);
	char& operator[](int);
	friend ostream &operator<<(ostream&, const Stack&);
	friend istream &operator>>(istream&, Stack&);
	int Size();
	~Stack();
};