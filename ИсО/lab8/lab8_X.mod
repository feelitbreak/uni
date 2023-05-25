param n, integer, > 0;
param m, integer, > 0;
set I := 1..n;
set J := 1..m;
param A {I, J};

var X {J}, >=0;

maximize dual_lp_x: sum{j in J} X[j];

subject to x_subject {i in I}: (sum{j in J} A[i, j] * X[j]) <= 1;