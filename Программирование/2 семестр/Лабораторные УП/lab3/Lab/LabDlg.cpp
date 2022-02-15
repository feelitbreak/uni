
// LabDlg.cpp : implementation file
//

#include "pch.h"
#include "framework.h"
#include "Lab.h"
#include "LabDlg.h"
#include "afxdialogex.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CAboutDlg dialog used for App About

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// Dialog Data
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_ABOUTBOX };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support

// Implementation
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(IDD_ABOUTBOX)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CLabDlg dialog



CLabDlg::CLabDlg(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_LAB_DIALOG, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CLabDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CLabDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_COMMAND(1002, &CLabDlg::On1002)
	ON_COMMAND(1001, &CLabDlg::On1001)
	ON_BN_CLICKED(IDC_BUTTON1, &CLabDlg::OnBnClickedButton1)
	ON_WM_RBUTTONDOWN()
	ON_WM_RBUTTONDBLCLK()
	ON_WM_MOUSEMOVE()
END_MESSAGE_MAP()


// CLabDlg message handlers

BOOL CLabDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// Add "About..." menu item to system menu.

	// IDM_ABOUTBOX must be in the system command range.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != nullptr)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon

	// TODO: Add extra initialization here
	srand((int)time(NULL));
	able1 = false;
	able2 = false;
	m_wndMenu.LoadMenu(IDR_MENU);
	// Загрузить меню из файла ресурса
	SetMenu(&m_wndMenu);
	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CLabDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CLabDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CPaintDC dc(this);
		CBrush brush(RGB(255, 255, 255));
		GetClientRect(&rect);
		dc.FillRect(&rect, &brush);
		CDialogEx::OnPaint();
	}
}

// The system calls this function to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CLabDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

#include "MyDialog.h"
void CLabDlg::On1001()
{
	// TODO: Add your command handler code here
	MyDialog dlg;
	dlg.DoModal();
}

void CLabDlg::On1002()
{
	// TODO: Add your command handler code here
	EndDialog(-1);
}


void CLabDlg::OnBnClickedButton1()
{
	CClientDC dc(this);
	CString t = CTime::GetCurrentTime().Format("%X");
	int nHeight = -MulDiv(8, dc.GetDeviceCaps(LOGPIXELSY), 72);
	CFont font;
	font.CreateFont(nHeight, 0, 0, 0, 400, false, false, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, L"MS Shell Dlg");
	dc.SelectObject(font);
	dc.TextOut(170, 480, t);
	font.DeleteObject();
	/*GetDlgItem(IDC_STATIC)->SetWindowText(t);*/
}



void CLabDlg::OnRButtonDown(UINT nFlags, CPoint point)
{
	// TODO: Add your message handler code here and/or call default
	CClientDC dc(this);
	CString s;
	s.Format(_T("%d"), rand() % 10000);
	int nHeight = -MulDiv(8, dc.GetDeviceCaps(LOGPIXELSY), 72);
	CFont font;
	font.CreateFont(nHeight, 0, 0, 0, 400, false, false, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, L"MS Shell Dlg");
	dc.SelectObject(font);
	dc.TextOut(point.x, point.y, s);
	font.DeleteObject();
	CDialogEx::OnRButtonDown(nFlags, point);
}


void CLabDlg::OnRButtonDblClk(UINT nFlags, CPoint point)
{
	// TODO: Add your message handler code here and/or call default
	if (able1)
	{
		CClientDC dc(this);
		CString s(L"Была 2 раза нажата правая кнопка мыши");
		int nHeight = -MulDiv(8, dc.GetDeviceCaps(LOGPIXELSY), 72);
		CFont font; 
		font.CreateFont(nHeight, 0, 0, 0, 400, false, false, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, L"MS Shell Dlg");
		dc.SelectObject(font);
		dc.TextOut(x1, y1, s);
		font.DeleteObject();
	}
	else able1 = true;
	x1 = point.x;
	y1 = point.y;
	CDialogEx::OnRButtonDblClk(nFlags, point);
}


void CLabDlg::OnMouseMove(UINT nFlags, CPoint point)
{
	// TODO: Add your message handler code here and/or call default
	CPen Eraser(PS_SOLID, 2, RGB(255, 255, 255));
	CPen rPen(PS_SOLID, 2, RGB(255, 0, 0));
	GetClientRect(&rect);
	int x = rect.Width() / 2;
	int y = rect.Height() / 2;
	if (able2)
	{
		CClientDC dc(this);
		dc.MoveTo(x, y);
		dc.SelectObject(&Eraser);
		dc.LineTo(x2, y2);
	}
	else able2 = true;
	CClientDC dc(this);
	dc.MoveTo(x, y);
	dc.SelectObject(&rPen);
	dc.LineTo(point.x, point.y);
	x2 = point.x;
	y2 = point.y;
	CDialogEx::OnMouseMove(nFlags, point);
}


BOOL CLabDlg::PreTranslateMessage(MSG* pMsg)
{
	// TODO: Add your specialized code here and/or call the base class
	if (pMsg->message == WM_KEYDOWN)
	{
		CClientDC dc(this);
		int nHeight = -MulDiv(8, dc.GetDeviceCaps(LOGPIXELSY), 72);
		CFont font;
		CString s;
		font.CreateFont(nHeight, 0, 0, 0, 400, false, false, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, L"MS Shell Dlg");
		dc.SelectObject(font);
		int x = rect.Width() / 2;
		int y = rect.Height() / 2;
		pMsg->wParam;
		if (pMsg->wParam == VK_BACK)
			s = "Backspace";
		if (pMsg->wParam == VK_TAB)
			s = "Tab";
		if (pMsg->wParam == VK_RETURN)
			s = "Enter";
		if (pMsg->wParam == VK_SHIFT)
			s = "Shift";
		if (pMsg->wParam == VK_CONTROL)
			s = "Ctrl";
		if (pMsg->wParam == VK_PAUSE)
			s = "Pause";
		if (pMsg->wParam == VK_CAPITAL)
			s = "Caps Lock";
		if (pMsg->wParam == VK_ESCAPE)
			s = "Esc";
		if (pMsg->wParam == VK_SPACE)
			s = "Space";
		if (pMsg->wParam == VK_PRIOR)
			s = "Page Up";
		if (pMsg->wParam == VK_NEXT)
			s = "Page Down";
		if (pMsg->wParam == VK_END)
			s = "End";
		if (pMsg->wParam == VK_HOME)
			s = "Home";
		if (pMsg->wParam == VK_LEFT)
			s = "Left Arrow";
		if (pMsg->wParam == VK_UP)
			s = "Up Arrow";
		if (pMsg->wParam == VK_RIGHT)
			s = "Right Arrow";
		if (pMsg->wParam == VK_DOWN)
			s = "Down Arrow";
		if (pMsg->wParam == VK_INSERT)
			s = "Ins";
		if (pMsg->wParam == VK_SNAPSHOT)
			s = "Print Screen";
		if (pMsg->wParam == VK_DELETE)
			s = "Delete";
		if (pMsg->wParam == 0x30 || pMsg->wParam == VK_NUMPAD0)
			s = "0";
		if (pMsg->wParam == 0x31 || pMsg->wParam == VK_NUMPAD1)
			s = "1";
		if (pMsg->wParam == 0x32 || pMsg->wParam == VK_NUMPAD2)
			s = "2";
		if (pMsg->wParam == 0x33 || pMsg->wParam == VK_NUMPAD3)
			s = "3";
		if (pMsg->wParam == 0x34 || pMsg->wParam == VK_NUMPAD4)
			s = "4";
		if (pMsg->wParam == 0x35 || pMsg->wParam == VK_NUMPAD5)
			s = "5";
		if (pMsg->wParam == 0x36 || pMsg->wParam == VK_NUMPAD6)
			s = "6";
		if (pMsg->wParam == 0x37 || pMsg->wParam == VK_NUMPAD7)
			s = "7";
		if (pMsg->wParam == 0x38 || pMsg->wParam == VK_NUMPAD8)
			s = "8";
		if (pMsg->wParam == 0x39 || pMsg->wParam == VK_NUMPAD9)
			s = "9";
		if (pMsg->wParam == 0x41)
			s = "A";
		if (pMsg->wParam == 0x42)
			s = "B";
		if (pMsg->wParam == 0x43)
			s = "C";
		if (pMsg->wParam == 0x44)
			s = "D";
		if (pMsg->wParam == 0x45)
			s = "E";
		if (pMsg->wParam == 0x46)
			s = "F";
		if (pMsg->wParam == 0x47)
			s = "G";
		if (pMsg->wParam == 0x48)
			s = "H";
		if (pMsg->wParam == 0x49)
			s = "I";
		if (pMsg->wParam == 0x4A)
			s = "J";
		if (pMsg->wParam == 0x4B)
			s = "K";
		if (pMsg->wParam == 0x4C)
			s = "L";
		if (pMsg->wParam == 0x4D)
			s = "M";
		if (pMsg->wParam == 0x4E)
			s = "N";
		if (pMsg->wParam == 0x4F)
			s = "O";
		if (pMsg->wParam == 0x50)
			s = "P";
		if (pMsg->wParam == 0x51)
			s = "Q";
		if (pMsg->wParam == 0x52)
			s = "R";
		if (pMsg->wParam == 0x53)
			s = "S";
		if (pMsg->wParam == 0x54)
			s = "T";
		if (pMsg->wParam == 0x55)
			s = "U";
		if (pMsg->wParam == 0x56)
			s = "V";
		if (pMsg->wParam == 0x57)
			s = "W";
		if (pMsg->wParam == 0x58)
			s = "X";
		if (pMsg->wParam == 0x59)
			s = "Y";
		if (pMsg->wParam == 0x5A)
			s = "Z";
		if (pMsg->wParam == VK_LWIN)
			s = "Windows";
		if (pMsg->wParam == VK_MULTIPLY)
			s = "*";
		if (pMsg->wParam == VK_ADD)
			s = "+";
		if (pMsg->wParam == VK_SUBTRACT)
			s = "-";
		if (pMsg->wParam == VK_DECIMAL)
			s = ".";
		if (pMsg->wParam == VK_DIVIDE)
			s = "/";
		if (pMsg->wParam == VK_F1)
			s = "F1";
		if (pMsg->wParam == VK_F2)
			s = "F2";
		if (pMsg->wParam == VK_F3)
			s = "F3";
		if (pMsg->wParam == VK_F4)
			s = "F4";
		if (pMsg->wParam == VK_F5)
			s = "F5";
		if (pMsg->wParam == VK_F6)
			s = "F6";
		if (pMsg->wParam == VK_F7)
			s = "F7";
		if (pMsg->wParam == VK_F8)
			s = "F8";
		if (pMsg->wParam == VK_F9)
			s = "F9";
		if (pMsg->wParam == VK_F11)
			s = "F11";
		if (pMsg->wParam == VK_NUMLOCK)
			s = "Num Lock";
		if (pMsg->wParam == VK_OEM_1)
			s = ";:";
		if (pMsg->wParam == VK_OEM_PLUS)
			s = "=+";
		if (pMsg->wParam == VK_OEM_COMMA)
			s = ",<";
		if (pMsg->wParam == VK_OEM_MINUS)
			s = "-_";
		if (pMsg->wParam == VK_OEM_PERIOD)
			s = ".>";
		if (pMsg->wParam == VK_OEM_2)
			s = "/?";
		if (pMsg->wParam == VK_OEM_3)
			s = "`~";
		if (pMsg->wParam == VK_OEM_4)
			s = "[{";
		if (pMsg->wParam == VK_OEM_5)
			s = "\\|";
		if (pMsg->wParam == VK_OEM_6)
			s = "]}";
		if (pMsg->wParam == VK_OEM_7)
			s = "'\"";
		dc.TextOut(x-s.GetLength()*3, y-8, s);
		font.DeleteObject();
	}
	return CDialogEx::PreTranslateMessage(pMsg);
}
