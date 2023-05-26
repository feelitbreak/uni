param n, integer, > 0;
param m, integer, > 0;
set I := 1..n;
set J := 1..m;
param A {I, J};

var P {I}, >=0;
var v;

maximize lp: v;

subject to p_subject {j in J}: (sum{i in I} A[i, j] * P[i]) - v >= 0;
subject to probability: (sum{i in I} P[i]) = 1;