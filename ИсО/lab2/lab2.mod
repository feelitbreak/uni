param n, integer, > 0;

set cars := 1..n;

param guests {cars}, > 0;
param max_cost, > 0;
param c {cars}, > 0;
param car_min, integer, >= 0;

param bugatti, integer, > 0, <= n;
param cadillac, integer, > 0, <= n;
param cobra, integer, > 0, <= n;
param corvette, integer, > 0, <= n;

var x {cars}, binary;

maximize guests_overall: sum{i in cars} guests[i] * x[i];
minimize min_total_cost: sum{i in cars} c[i] * x[i];
maximize max_total_cost: sum{i in cars} c[i] * x[i];

subject to cost: (sum{i in cars} c[i] * x[i]) <= max_cost;
subject to min_cars: (sum{i in cars} x[i]) >= car_min;
subject to implication1: x[cobra] >= x[corvette];
subject to implication2: (1 - x[bugatti]) <= x[cadillac];