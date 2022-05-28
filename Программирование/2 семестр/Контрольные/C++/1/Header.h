#include <iostream>
using namespace std;
class Queue
{
private:
	long* mas;
	int n, k1, k2;
public:
	Queue();
	Queue(int N);
	Queue(const Queue&);
	Queue& operator=(const Queue &);
	Queue operator+(long);
	Queue operator-(long);
	Queue operator/(long);
	long& operator[](int);
	bool operator<=(const Queue&);
	friend ostream &operator<<(ostream&, const Queue&);
	friend istream &operator>>(istream&, Queue&);
	~Queue();
};