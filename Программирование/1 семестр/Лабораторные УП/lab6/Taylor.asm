.486
.model flat
.data
a dq 1
b dq 2
d dq 1
g dq 2
y dq 0
.code
Public Taylor
	   Taylor proc C x:qword, e:qword, y2:dword
    finit
	fldz
	fstp y
	fld1 
	fst a
	fst d
	fld1
	fadd
	fst b
	fstp g
		fld e
		fld a
		fabs
		fcompp
		fstsw ax
		sahf
		jb end2
		fld y
		fld a
		fadd
		fstp y
		fld x
		fld b
		fdiv
		fchs
		fstp a
		slagaemoe :
		fld e
			fld a
			fabs
			fcompp
			fstsw ax
			sahf
			jb end2
			fld y
			fld a
			fadd
			fstp y
			fld d
			fld g
			fadd
			fstp d
			fld b
			fld g
			fadd
			fstp b
			fld a
			fld d
			fmul
			fld b
			fdiv
			fld x
			fmul
			fchs
			fstp a
			ffree st
			jmp slagaemoe
			end2 :
	fld y
	mov eax, [y2]
	fstp qword ptr [eax]
	ffree st
    ret
Taylor endp
end