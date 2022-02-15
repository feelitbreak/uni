#include <map>
#include <string>
using namespace std;
class Sort
{
public:
	bool operator () (double x, double y) const
	{
		return (x<y);
	};
};
typedef map<double, string, Sort> Map;
void Menu();
bool Input(Map&, const char[]);
void Output(Map&);
void AddElement(Map&);
void DeleteElements(Map&);
void FindGreater(Map&);
void Swap(Map&);
void Replace(Map&);