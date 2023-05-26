param n, integer, > 0;
param m, integer, > 0;
set I := 1..n;
set J := 1..m;
param A {I, J};

var Q {J}, >=0;
var v;

minimize lp: v;

subject to q_subject {i in I}: (sum{j in J} A[i, j] * Q[j]) - v <= 0;
subject to probability: (sum{j in J} Q[j]) = 1;