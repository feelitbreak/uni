.486
.model flat
.data
	e dd 0
.code
Public _Udalenie
	   _Udalenie proc
	        push ebp
			mov ebp, esp
			mov esi, [ebp+8]
			mov edi, [ebp+12]
			mov ecx, [ebp+16]
			mov ebx, [ebp+20]
			mov edx, [ebp+24]
			push ds
			pop es
			cld
			push ebx
			sub ebx, edx
			inc ebx
			sub ecx, ebx
			inc ecx
			pop ebx
			;lea esi, str1
			;lea edi, str4
			m3 : 
			inc e
			cmp e, edx
			jl prodolzhaem
			cmp e, ebx
			jg prodolzhaem
			lodsb
			jmp m3
			prodolzhaem:
			lodsb
			stosb
			loop m3
			pop ebp
			ret
		_Udalenie endp
		end