#pragma once

#include <vcclr.h>
#include <cstdlib>
#include <cstring>

#include <iostream>

namespace Lab {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Сводка для Lab
	/// </summary>
	public ref class Lab : public System::Windows::Forms::Form
	{
	public:
		Lab(void)
		{
			InitializeComponent();
			//
			//TODO: добавьте код конструктора
			//
		}

	protected:
		/// <summary>
		/// Освободить все используемые ресурсы.
		/// </summary>
		~Lab()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::Button^ button1;
	private: System::Windows::Forms::ListBox^ listBox1;
	private: System::Windows::Forms::Label^ label1;
	private: System::Windows::Forms::TextBox^ textBox1;
	private: System::Windows::Forms::Label^ label2;

	private: System::Windows::Forms::Label^ label3;
	private: System::Windows::Forms::TextBox^ textBox2;



	protected:

	private:
		/// <summary>
		/// Обязательная переменная конструктора.
		/// </summary>
		System::ComponentModel::Container^ components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Требуемый метод для поддержки конструктора — не изменяйте 
		/// содержимое этого метода с помощью редактора кода.
		/// </summary>
		void InitializeComponent(void)
		{
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->listBox1 = (gcnew System::Windows::Forms::ListBox());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->textBox1 = (gcnew System::Windows::Forms::TextBox());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->textBox2 = (gcnew System::Windows::Forms::TextBox());
			this->SuspendLayout();
			// 
			// button1
			// 
			this->button1->Location = System::Drawing::Point(44, 103);
			this->button1->Margin = System::Windows::Forms::Padding(4);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(100, 28);
			this->button1->TabIndex = 0;
			this->button1->Text = L"Перевести";
			this->button1->UseVisualStyleBackColor = true;
			this->button1->Click += gcnew System::EventHandler(this, &Lab::button1_Click);
			// 
			// listBox1
			// 
			this->listBox1->Font = (gcnew System::Drawing::Font(L"Microsoft Sans Serif", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->listBox1->FormattingEnabled = true;
			this->listBox1->ItemHeight = 17;
			this->listBox1->Items->AddRange(gcnew cli::array< System::Object^  >(4) { L"2 с/c", L"8 c/c", L"10 c/c", L"16 c/c" });
			this->listBox1->Location = System::Drawing::Point(44, 196);
			this->listBox1->Margin = System::Windows::Forms::Padding(4);
			this->listBox1->Name = L"listBox1";
			this->listBox1->Size = System::Drawing::Size(169, 72);
			this->listBox1->TabIndex = 1;
			this->listBox1->SelectedIndexChanged += gcnew System::EventHandler(this, &Lab::listBox1_SelectedIndexChanged);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->BackColor = System::Drawing::SystemColors::Control;
			this->label1->Location = System::Drawing::Point(40, 25);
			this->label1->Margin = System::Windows::Forms::Padding(4, 0, 4, 0);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(46, 17);
			this->label1->TabIndex = 2;
			this->label1->Text = L"label1";
			// 
			// textBox1
			// 
			this->textBox1->Location = System::Drawing::Point(44, 58);
			this->textBox1->Margin = System::Windows::Forms::Padding(4);
			this->textBox1->Name = L"textBox1";
			this->textBox1->Size = System::Drawing::Size(181, 22);
			this->textBox1->TabIndex = 3;
			this->textBox1->TextChanged += gcnew System::EventHandler(this, &Lab::textBox1_TextChanged);
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Location = System::Drawing::Point(40, 155);
			this->label2->Margin = System::Windows::Forms::Padding(4, 0, 4, 0);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(46, 17);
			this->label2->TabIndex = 4;
			this->label2->Text = L"label2";
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Location = System::Drawing::Point(264, 25);
			this->label3->Margin = System::Windows::Forms::Padding(4, 0, 4, 0);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(46, 17);
			this->label3->TabIndex = 6;
			this->label3->Text = L"label3";
			// 
			// textBox2
			// 
			this->textBox2->BackColor = System::Drawing::SystemColors::Window;
			this->textBox2->Location = System::Drawing::Point(268, 58);
			this->textBox2->Margin = System::Windows::Forms::Padding(4);
			this->textBox2->Name = L"textBox2";
			this->textBox2->ReadOnly = true;
			this->textBox2->Size = System::Drawing::Size(181, 22);
			this->textBox2->TabIndex = 7;
			// 
			// Lab
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(8, 16);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(479, 502);
			this->Controls->Add(this->textBox2);
			this->Controls->Add(this->label3);
			this->Controls->Add(this->label2);
			this->Controls->Add(this->textBox1);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->listBox1);
			this->Controls->Add(this->button1);
			this->Margin = System::Windows::Forms::Padding(4);
			this->Name = L"Lab";
			this->Text = L"Lab";
			this->Load += gcnew System::EventHandler(this, &Lab::Lab_Load);
			this->Paint += gcnew System::Windows::Forms::PaintEventHandler(this, &Lab::Lab_Paint);
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
		int x, b;
		bool able1 = false, able2 = false;
		char digits(int c)
		{
			switch (c)
			{
			case 0: return '0';
			case 1: return '1';
			case 2: return '2';
			case 3: return '3';
			case 4: return '4';
			case 5: return '5';
			case 6: return '6';
			case 7: return '7';
			case 8: return '8';
			case 9: return '9';
			case 10: return 'A';
			case 11: return 'B';
			case 12: return 'C';
			case 13: return 'D';
			case 14: return 'E';
			case 15: return 'F';
			}
		}
	private: System::Void Lab_Load(System::Object^ sender, System::EventArgs^ e)
	{
		this->Text = "Перевод с/c";
		label1->Text = "Введите число в 10 с/c:";
		label2->Text = "Выберите с/c результата";
		label3->Text = "Результат:";
	}
	private: System::Void button1_Click(System::Object^ sender, System::EventArgs^ e)
	{
		if (able1 && able2)
		{
			char* c = new char[256];
			itoa(b, c, 10);
			int b2 = b;
			if (x != 10)
			{
				char* c3 = new char[256];
				if (b2 == 0) c3 = "0";
				else
				{
					c3[0] = '\0';
					int i = -1;
					while (b2)
					{
						for (int j = i; j > -1; j--)
							c3[j + 1] = c3[j];
						c3[0] = digits(b2 % x);
						i++;
						c3[i + 1] = '\0';
						b2 /= x;
					}
				}
				if (x == 2 && strlen(c3) % 4 != 0 && b != 0)
					while (strlen(c3) % 4)
					{
						int i = strlen(c3);
						for (int j = i - 1; j > -1; j--)
							c3[j + 1] = c3[j];
						c3[i + 1] = '\0';
						c3[0] = '0';
					}
				strcpy(c, c3);
			}
			String^ c2 = gcnew String(c);
			this->textBox2->Text = c2;
		}
	}
	private: System::Void listBox1_SelectedIndexChanged(System::Object^ sender, System::EventArgs^ e)
	{
		x = listBox1->SelectedIndex;
		able1 = true;
		if (!x) x = 2;
		else if (x == 1) x = 8;
		else if (x == 2) x = 10;
		else if (x == 3) x = 16;
		Invalidate();
	}
	private: System::Void textBox1_TextChanged(System::Object^ sender, System::EventArgs^ e)
	{
		setlocale(LC_ALL, "rus");
		String^ a = gcnew String(textBox1->Text);
		if (a != "")
		{
			pin_ptr<const wchar_t> wch;
			wch = PtrToStringChars(a);
			int size;
			size = wcslen(wch) + 1;
			size_t si;
			char* c = new char[256];
			wcstombs_s(&si, c, size, wch, 256);
			if (strspn(c, "0123456789.,") == strlen(c))
			{
				b = atoi(c);
				if (b != 2147483647)
					able2 = true;
				else able2 = false;
			}
			else able2 = false;
		}
		else able2 = false;
		Invalidate();
	}
	private: System::Void Lab_Paint(System::Object^ sender, System::Windows::Forms::PaintEventArgs^ e)
	{
		if (able1 && able2)
		{
			Pen^ pen = gcnew Pen(Color::Lime);
			Brush^ brush = gcnew SolidBrush(Color::Lime);
			e->Graphics->DrawEllipse(pen, 8, 46, 20, 20);
			e->Graphics->FillEllipse(brush, 8, 46, 20, 20);
		}
	}
	};
}
