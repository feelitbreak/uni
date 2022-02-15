// абстрактный базовый класс Итератор
class AbstractIterator
{
public:
	virtual ~AbstractIterator() {};
	virtual bool InRange() const = 0; // индекс в допустимых пределах?
	virtual void Reset() = 0; // сбросить индекс в начало
	virtual int& operator *() const = 0; // разыменование (чтение элемента)
	virtual void operator ++() = 0; // сдвиг на элемент
};
// абстрактный базовый класс Контейнер
class AbstractContainer
{
public:
	virtual ~AbstractContainer() {};
	virtual bool IsEmpty() const = 0; // контейнер пуст
	virtual bool IsFull() const = 0; // контейнер полный
};
// абстрактный базовый класс Стек
class AbstractStack : public AbstractContainer
{
public:
	virtual void push(const int& n) = 0; // втолкнуть в стек
	virtual int pop(void) = 0; // вытолкнуть из стека
};
class ArrayStackIterator; // предваряющее объявление
class ArrayStack : public AbstractStack
{
protected:
	int size; // размерность массива
	int* p; // указатель на массив
	int top; // верхушка стека
public:
	ArrayStack(int _size);
	ArrayStack(ArrayStack &s);
	~ArrayStack();
	void push(const int& n); // втолкнуть в стек
	int pop(void); // вытолкнуть из стека
	bool IsEmpty() const;
	bool IsFull() const;
	friend class ArrayStackIterator;
};
class ArrayStackIterator : public AbstractIterator
{
	ArrayStack& a; // ссылка на стек
	int pos; // текущая позиция итератора
	ArrayStackIterator();
public:
	ArrayStackIterator(ArrayStack& _a);
	bool InRange() const; // индекс в допустимых пределах
	void Reset(); // сбросить индекс в начало
	int& operator *() const; // разыменование (чтение элемента)
	void operator ++(); // сдвиг на элемент
};