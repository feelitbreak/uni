#include <iostream>
using namespace std;
class Stack
{
private:
	struct Products
	{
		char* Name;
		double Price;
		Products *pred;
	}; Products *top;
public:
	Stack();
	bool Is_Empty();
	void Push(char*, double);
	void AddElement();
	void Pop();
	void Input();
	int OutputFile();
	void OutputConsole();
	void Delete();
	void Menu();
	~Stack();
};