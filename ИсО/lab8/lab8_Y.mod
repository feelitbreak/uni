param n, integer, > 0;
param m, integer, > 0;
set I := 1..n;
set J := 1..m;
param A {I, J};

var Y {I}, >=0;

minimize dual_lp_y: sum{i in I} Y[i];

subject to y_subject {j in J}: (sum{i in I} A[i, j] * Y[i]) >= 1;