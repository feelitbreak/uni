#include <iostream>
using namespace std;
class Abstract
{
public:
	static int Count;
	const int Index;
	Abstract() : Index(Count+1) { Count++; };
	virtual void Show() = 0;
	virtual const int GetIndex() = 0;
	virtual ~Abstract() {};
};
class Liquid : public Abstract 
{
protected:
	char* Name;
	int Density;
public:
	Liquid() : Abstract() {};
	Liquid(char* newName);
	Liquid(char* newName, int newDensity);
	void Show();
	const int GetIndex();
	int GetDensity();
	~Liquid();
};
class Juice : public Liquid
{
protected:
	int Percentage;
public:
	Juice() : Liquid() {};
	Juice(char* newName) : Liquid(newName) {};
	Juice(char* newName, int newDensity) : Liquid(newName, newDensity) {};
	Juice(char*, int, int);
	void Show();
	int GetPercentage();
	~Juice() {};
};