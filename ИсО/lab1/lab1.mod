param n, integer, > 0;
param m, integer, > 0;

set foods := 1..n;
set nutrients := 1..m;

param c {foods}, > 0;
param a {nutrients, foods};
param b_max {nutrients};
param b_min {nutrients};

var x {foods}, >= 0;

minimize totalcost: sum{i in foods} c[i] * x[i];

subject to nutrition_min {i in nutrients}: b_min[i] <= sum{j in foods} (a[i, j] * x[j]) <= b_max[i];