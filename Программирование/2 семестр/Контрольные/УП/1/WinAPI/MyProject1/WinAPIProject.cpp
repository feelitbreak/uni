#include <windows.h>


LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
char szAppName[] = "Программа";

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
	wndclass.lpszClassName = szAppName;
	wndclass.hIconSm = LoadIcon(NULL, IDI_APPLICATION);

	RegisterClassEx(&wndclass);

	hwnd = CreateWindow(szAppName, "The Program", WS_OVERLAPPEDWINDOW, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, NULL, NULL, hInstance, NULL);

	ShowWindow(hwnd, iCmdShow);
	UpdateWindow(hwnd);

	while (GetMessage(&msg, NULL, 0, 0))
	{
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return msg.wParam;
}

LRESULT CALLBACK WndProc(HWND hwnd, UINT iMsg, WPARAM wParam, LPARAM lParam)
{
	static HWND hbutton;
	RECT         rect;
	HDC          hdc;
	PAINTSTRUCT  ps;
	HPEN hPen;
	static int x = 20;
	switch (iMsg)
	{

	case WM_CREATE:

		hbutton = CreateWindow("button", "Увеличить длину линии", WS_CHILD | WS_VISIBLE, 20, 20, 200, 22, hwnd, (HMENU)10001, ((LPCREATESTRUCT)lParam)->hInstance, NULL);

		return 0;
	case WM_SIZE:

		return 0;

	case WM_COMMAND:
		if (HIWORD(wParam) == BN_CLICKED)
		{
			x += 20;
			InvalidateRect(hwnd, NULL, TRUE);
		}
		return 0;
	case WM_PAINT:

		hdc = BeginPaint(hwnd, &ps);
		GetClientRect(hwnd, &rect);
		MoveToEx(hdc, 10, 650, NULL);
		hPen = CreatePen(PS_SOLID, 2, RGB(255, 0, 0));
		SelectObject(hdc, hPen);
		LineTo(hdc, 10 + x, 650 - x);
		DeleteObject((HGDIOBJ)(HPEN)(hPen));
		EndPaint(hwnd, &ps);
		return 0;

	case WM_DESTROY:
		PostQuitMessage(0);
		return 0;
	}

	return DefWindowProc(hwnd, iMsg, wParam, lParam);
}