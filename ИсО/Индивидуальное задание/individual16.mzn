include "globals.mzn";

par int: n = 10;
array[1..n] of var 1..n: Numbers;

constraint all_different(Numbers);
constraint Numbers[1] = abs(Numbers[2] - Numbers[3]);
constraint Numbers[2] = abs(Numbers[4] - Numbers[5]);
constraint Numbers[3] = abs(Numbers[5] - Numbers[6]);
constraint Numbers[4] = abs(Numbers[7] - Numbers[8]);
constraint Numbers[5] = abs(Numbers[8] - Numbers[9]);
constraint Numbers[6] = abs(Numbers[9] - Numbers[10]);

solve satisfy;

output ["   ", show(Numbers[1]), "\n",
        "  ", show(Numbers[2]), " ", show(Numbers[3]), "\n",
        " ", show(Numbers[4]), " ", show(Numbers[5]), " ", show(Numbers[6]), "\n",
        show(Numbers[7]), " ", show(Numbers[8]), " ", show(Numbers[9]), " ", show(Numbers[10]), "\n"];
        