#include <iostream>
using namespace std;
class List
{
private:
	struct Products
	{
		char* Name;
		double Price;
		Products* next, *pred;
	}; Products *head, *tail;
public:
	List();
	List(FILE*);
	bool Is_Empty();
	int Search();
	void DeleteElement(int);
	void OutputConsole();
	void Menu();
	~List();
};