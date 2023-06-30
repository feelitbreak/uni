include "globals.mzn";

enum People = { Pat, Quincy, Ray };
array[People] of var bool: x;

constraint exists(x);
% constraint sum(k in People) (x[k]) >= 1;
constraint x[Pat] /\ not x[Ray] -> x[Quincy];
constraint x[Ray] <-> x[Quincy];

solve satisfy;

output ["Результат:", "\n",
        "Pat: ", show(x[Pat]), "\n",
        "Quincy: ", show(x[Quincy]), "\n",
        "Ray: ", show(x[Ray]), "\n"];
        