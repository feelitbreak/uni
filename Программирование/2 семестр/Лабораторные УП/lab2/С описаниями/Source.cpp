//Кендысь Алексей. Группа 9. 1 курс, 2 семестр. УП, Лаб 2. Индивидуальное задание 5
#include <windows.h>
#include <winuser.h>
LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
char szAppName[] = "Программа";
HWND hwndDlg = NULL;
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
	PSTR szCmdLine, int iCmdShow)
{
	HWND        hwnd;
	MSG         msg;
	WNDCLASSEX  wndclass;

	wndclass.cbSize = sizeof(wndclass);
	wndclass.style = CS_HREDRAW | CS_VREDRAW;
	wndclass.lpfnWndProc = WndProc;
	wndclass.cbClsExtra = 0;
	wndclass.cbWndExtra = 0;
	wndclass.hInstance = hInstance;
	wndclass.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wndclass.hCursor = LoadCursor(NULL, IDC_ARROW);
	wndclass.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);
	wndclass.lpszMenuName = NULL;
	//если создано меню как ресурс с идентификатором IDR_MENU1, то отображения меню нужно :
	//wndclass.lpszMenuName = MAKEINTRESOURCE(IDR_MENU1);  

	wndclass.lpszClassName = szAppName;
	wndclass.hIconSm = LoadIcon(NULL, IDI_APPLICATION);

	RegisterClassEx(&wndclass);

	hwnd = CreateWindow(szAppName,        // window class name
		"Лабораторная 2",   // window caption
		WS_OVERLAPPEDWINDOW,     // window style
		CW_USEDEFAULT,           // initial x position
		CW_USEDEFAULT,           // initial y position
		CW_USEDEFAULT,           // initial x size
		CW_USEDEFAULT,           // initial y size
		NULL,                    // parent window handle
		NULL,                    // window menu handle
		hInstance,               // program instance handle
		NULL);		   // creation parameters

	ShowWindow(hwnd, iCmdShow);
	UpdateWindow(hwnd);
	hwndDlg = (HWND)0;
	while (GetMessage(&msg, 0, 0, 0))
	{
		if (hwndDlg == 0 || !IsDialogMessage(hwndDlg, &msg))
		{
			TranslateMessage(&msg);
			DispatchMessage(&msg);
		}
	}
	return msg.wParam;
};
BOOL CALLBACK lpfnDlgProc(HWND hdlg, UINT msg, WPARAM wParam, LPARAM lParam)
{
	switch (msg)
	{ // Инициализация диалоговой панели
	case WM_INITDIALOG:
	{
	
	} return TRUE;
	case WM_COMMAND:
	{
		switch (wParam)
		{ // Еще один вариант завершения  работы немодального диалогового окна
		// Первый – в обработчике «WM_DESTROY»
		// Сообщение от кнопки "OK"
		case IDOK:
		{//{ сохранение необходимых данных ...}
		 // Сообщение от «Cancel» – завершение без сохранения
		}
		case IDCANCEL:
		{ // Уничтожаем диалоговую панель
			DestroyWindow(hdlg);
			return TRUE;
		}
		}
	}
	}
	return FALSE;

};
LRESULT CALLBACK WndProc(HWND hwnd, UINT iMsg, WPARAM wParam, LPARAM lParam) {
	static HWND hbutton, hwndedit;
	// Главное меню.
	static HMENU hMenu;
	// Подменю.
	static HMENU hPopupMenu1;
	static HMENU hPopupMenu2;
	PAINTSTRUCT ps;
	RECT        rect;
	HDC          hdc;
	static bool able1 = false;
	static bool able2 = false;
	static bool able3 = false;
	static bool able4 = false;
	static bool able5 = false;
	static int x1, x2, y1, y2, x3, y3;
	static int A;
	static char *str;
	static int len;
	switch (iMsg)
	{

	case WM_CREATE:
		// Создаем главое меню.
		hMenu = CreateMenu();
		//// Создаем подменю.
		hPopupMenu1 = CreatePopupMenu();
		hPopupMenu2 = CreatePopupMenu();
		//добавить элементы меню
		AppendMenu(hMenu, MF_STRING | MF_POPUP, (UINT)hPopupMenu1, "Файл");
		AppendMenu(hMenu, MF_STRING | MF_POPUP, (UINT)hPopupMenu2, "Операции");
		/*AppendMenu(hMenu, MF_STRING, 1001, "Help");*/
		//добавить подменю
		AppendMenu(hPopupMenu2, MF_STRING, 1001, "Вывод текста в окно");
		AppendMenu(hPopupMenu2, MF_STRING, 1002, "Вывод графического изображения");
		AppendMenu(hPopupMenu2, MF_STRING, 1003, "Немодальное окно с картинкой");
		// пункт подменю, помеченный галочкой
		//AppendMenu(hPopupMenu2, MF_STRING | MF_CHECKED, 1004, "С галочкой");
		/*AppendMenu(hPopupMenu, MF_STRING, 1003, "Печать");*/
		//привязать созданые меню пункты к окну
		SetMenu(hwnd, hMenu);
		hbutton = CreateWindow("button", "Вывод графического изображения", WS_CHILD | WS_VISIBLE, 870, 40, 250, 22, hwnd, (HMENU)10001, ((LPCREATESTRUCT)lParam)->hInstance, NULL);
		SendMessage(hbutton, WM_CHANGEUISTATE, MAKEWPARAM(UIS_SET, UISF_HIDEFOCUS), 0);
		hwndedit = CreateWindow("edit", NULL, WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 870, 10, 250, 22, hwnd, (HMENU)2, ((LPCREATESTRUCT)lParam)->hInstance, NULL);
		return 0;
	case WM_SETFOCUS:
		SetFocus(hwndedit);//установка курсора в текстовое поле
		return 0;
	case WM_SIZE:
		return 0;
	case WM_COMMAND:
		// Если выбрали пункт подмею меню.
	 //для меню из ресурса,необходимо посмотреть в файле resource.h, какое число назначено
		if (LOWORD(wParam) == 1001)
		{
			/*	MessageBox(hwnd, "Кендысь Алексей Максимович", "Вывод текста в окно", NULL);*/
			able1 = true;
			InvalidateRect(hwnd, NULL, TRUE);
		}
		if (LOWORD(wParam) == 1002)
		{
			len = GetWindowTextLength(hwndedit);
			str = new char[len +1];
			GetWindowText(hwndedit, str, len+1);
			if (strspn(str, "0123456789") == strlen(str))
			{
				A = atoi(str);
				able2 = true;
				InvalidateRect(hwnd, NULL, TRUE);
			}
			else MessageBox(hwnd, "Неверный формат ввода размера A.", "Ошибка.", NULL);
		}
		if (LOWORD(wParam) == 10001)
		{
			len = GetWindowTextLength(hwndedit);
			str = new char[len+1];
			GetWindowText(hwndedit, str, len+1);
			if (strspn(str, "0123456789") == strlen(str))
			{
				A = atoi(str);
			able2 = true;
			InvalidateRect(hwnd, NULL, TRUE);//-предназначена для перерисовки окна из любого места программы. Эта функция посылает в программу сообщение WM_PAINT. 
											 //Именно эту API-функцию вы должны использовать для перерисовки из обработчиков для разных сообщений Windows.
			}
			else MessageBox(hwnd, "Неверный формат ввода размера A.", "Ошибка.", NULL);
		}
		if (LOWORD(wParam) == 1003)
		{
			hwndDlg = CreateDialog(GetModuleHandle(NULL), MAKEINTRESOURCE(1003),
				hwnd, (DLGPROC)lpfnDlgProc);
			ShowWindow(hwndDlg, SW_SHOW);
		}
		return 0;
	case WM_PAINT:
		hdc = BeginPaint(	//Подготавливает окно к pаскpаске в ответ на сообщение wm_Paint. Заполняет Paint инфоpмацией для pаскpаски.
			hwnd, //Вновь pаскpашиваемое окно.
			&ps	//Стpуктуpа, пpинимающая инфоpмацию о pаскpаске.
		);   // Возвpащаемое значение: Идентификатоp контекста устpойства.
		if (able1)
		{
			GetClientRect(	//Функция GetClientRect возвращает координаты клиентской области окна. Клиентские координаты определяют верхний левый и правый нижний углы клиентской области. 
				//Поскольку клиентские координаты относительны левого угла клиентской области окна, то координатой верхнего левого угла является (0, 0).
				hwnd, //идентифицирует окно, клиентские координаты которого возвращаются.
				&rect //указывает на структуру, получающую клиентские координаты. Члены left и top равны нулю. Члены right и bottom содержат ширину и высоту окна.
			);//В случае успеха возвращается ненулевое значение. В случае неудачи возвращается нуль. Для получения дополнительной информации об ошибке вызовите функцию GetLastError.
			int nHeight = -MulDiv(20, GetDeviceCaps(hdc, LOGPIXELSY), 72);
			HFONT hfont = CreateFontA(nHeight, 0, 0, 0, 400, false, false, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, TEXT("Arial"));
			SelectObject(hdc, hfont); //Он будет иметь силу только когда мы его выберем
			SetTextColor(hdc, RGB(10, 10, 110));
			DrawText(//Рисует фоpматиpованный текст.
				hdc, //Идентификатоp контекста устpойства.
				"Кендысь Алексей Максимович", //Рисуемая стpока.
				-1, //Если Count=-1, то строка должна заканчиваться пустым символом.
				&rect, //огpаничивающий текст.
				DT_SINGLELINE | DT_LEFT | DT_TOP //Тип фоpматиpования
			);	// Возвpащаемое значение:   Высота текста.
			DeleteObject(hfont);
			able1 = false;
		}
		if (able2)
		{
			//hbr = CreateSolidBrush(RGB(0, 255, 0)); //создание кисти зелёного цвета
			HBRUSH hbr1, hbr2;
			hbr1 = CreateHatchBrush(HS_DIAGCROSS, RGB(0, 255, 0));
			hbr2 = CreateHatchBrush(HS_HORIZONTAL, RGB(0, 255, 0));
			SelectObject(hdc, hbr2);
			HPEN hPen; //объявляем объект-перо
			hPen = CreatePen(PS_SOLID, 2, RGB(0, 0, 255)); //создаём перо синего цвета
			SelectObject(hdc, hPen); //но в одним момент времени может быть только 1
			RoundRect(hdc, 29, 500-1.5*A, 30+A, 500-0.5*A, 1000, 1000);
			SelectObject(hdc, hbr1);
			Rectangle(hdc, 30, 500, 30+A, 500-A); //рисуем фигуру соответствующим пером
			//FillRect(hdc, &rect, hbr);//нарисуем прямоугольник  и закрасим зелёной кистью
			DeleteObject((HGDIOBJ)(HPEN)(hPen)); //удаление пера
			DeleteObject((HGDIOBJ)(HBRUSH)(hbr1));  //удаление кисти
			DeleteObject((HGDIOBJ)(HBRUSH)(hbr2));
			able2 = false;
		}
		if (able3 || able5)
		{
			HPEN hPen;
			hPen = CreatePen(PS_SOLID, 2, RGB(255, 0, 0));
			SelectObject(hdc, hPen);
			if (able3)
			{
				Rectangle(hdc, x1, y1, x2, y2);
				able3 = false;
			}
			else
			{
				MoveToEx(hdc, x1, y1, NULL);
				LineTo(hdc, x3, y3);
			}
			DeleteObject((HGDIOBJ)(HPEN)(hPen));
		}
		if (able4)
		{
			TextOut(hdc, 0, 0, "Выберите нужную операцию во вкладке \"Операции\" или введите размер A для фигуры в поле справа.", strlen("Выберите нужную операцию во вкладке \"Операции\" или введите размер A для фигуры в поле справа."));
			able4 = false;
		}
		EndPaint(	//Указывает конец pаскpаски в Wnd.
			hwnd, //Пеpекpашиваемое окно.
			&ps	//Стpуктуpа, полученная из функции BeginPaint.
		);
		return 0;
	case WM_LBUTTONDOWN:
	{
		x1 = LOWORD(lParam);
		y1 = HIWORD(lParam);
		able5 = true;
	}
	return 0;
	case WM_MOUSEMOVE:
	{
		if (able5)
		{
			x3 = LOWORD(lParam);
			y3 = HIWORD(lParam);
			if (x1 != x3 || y1 != y3)
				InvalidateRect(hwnd, NULL, TRUE);
		}
	}
	return 0;
	case WM_LBUTTONUP:
	{
		able5 = false;
		x2 = LOWORD(lParam);
		y2 = HIWORD(lParam);
		if (x1 != x2 || y1 != y2)
		{
			able3 = true;
		}
		else able4 = true;
		InvalidateRect(hwnd, NULL, TRUE);
	}
	return 0;
	case WM_CLOSE:
		DestroyWindow(hwnd);
	case WM_DESTROY:
		DestroyWindow(hwndDlg);// разрушение немодального окна
		PostQuitMessage(0);
		return 0;
	}
	return DefWindowProc(hwnd, iMsg, wParam, lParam);
};