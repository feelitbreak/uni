import java.util.*;

class Functions {
    public static double f(double x, double y) {
        return Math.abs((x * x) - (y * y));
    }

    public static double psi1(double y) {
        return 1 - Math.pow(y, 2.);
    }

    public static double psi2(double y) {
        return Math.exp(y) * (1 - Math.pow(y, 2.));
    }

    public static double psi3(double x) {
        return 1 - Math.pow(x, 2.);
    }

    public static double psi4(double x) {
        return 1 - Math.pow(x, 2.);
    }
}

class PoissonEquationSolver {
    private final int n1;
    private final int n2;
    private final double h1;
    private final double h2;
    private final double eps;
    private final double[] x1;
    private final double[] x2;
    private double[][] y;
    private int k;

    public PoissonEquationSolver(int n1, int n2, double h1, double h2, double eps, double[] x1, double[] x2) {
        this.n1 = n1;
        this.n2 = n2;
        this.h1 = h1;
        this.h2 = h2;
        this.eps = eps;
        this.x1 = x1;
        this.x2 = x2;

        genY();
    }

    public double[][] getY() {
        return y;
    }

    public int getK() {
        return k;
    }

    private void genY() {
        double[][] res = new double[n1 + 1][n2 + 1];
        setBoundaryValues(res);
        setInitialValues(res);

        k = 0;
        double checkStop;
        do {
            checkStop = 0;

            for (int i = 1; i <= n1 - 1; i++) {
                for (int j = 1; j <= n2 - 1; j++) {
                    double old = res[i][j];

                    res[i][j] = ((res[i + 1][j] + res[i - 1][j]) / Math.pow(h1, 2.));
                    res[i][j] += ((res[i][j + 1] + res[i][j - 1]) / Math.pow(h2, 2.));
                    res[i][j] += Functions.f(x1[i], x2[j]);
                    res[i][j] /= (2. / Math.pow(h1, 2.)) + (2. / Math.pow(h2, 2.));

                    if (Math.abs(res[i][j] - old) > checkStop) {
                        checkStop = Math.abs(res[i][j] - old);
                    }
                }
            }

            k++;
        } while(checkStop > eps);

        y = res;
    }

    private void setBoundaryValues(double[][] y) {
        for (int j = 0; j <= n2; j++) {
            y[0][j] = Functions.psi1(x2[j]);
        }

        for (int j = 0; j <= n2; j++) {
            y[n1][j] = Functions.psi2(x2[j]);
        }

        for (int i = 0; i <= n1; i++) {
            y[i][0] = Functions.psi3(x1[i]);
        }

        for (int i = 0; i <= n1; i++) {
            y[i][n2] = Functions.psi4(x1[i]);
        }
    }

    private void setInitialValues(double[][] y) {
        for (int i = 1; i <= n1 - 1; i++) {
            for (int j = 1; j <= n2 - 1; j++) {
                y[i][j] = Functions.f(x1[i], x2[j]);
            }
        }
    }
}

class DirichletProblem {
    private static final double A = -1.;
    private static final double B = 1.;
    private static final double C = -1.;
    private static final double D = 1.;
    private static final double H1 = 0.05;
    private static final double H2 = 0.1;
    private static final double EPS = Math.max(Math.pow(H1, 3.), Math.pow(H2, 3.));
    private static final double H1_EXACT = 0.005;
    private static final double H2_EXACT = 0.01;
    private static final double EPS_EXACT = Math.max(Math.pow(H1_EXACT, 3.), Math.pow(H2_EXACT, 3.));
    private final int n1;
    private final int n2;
    private final int n1Exact;
    private final int n2Exact;
    private final double[] x1;
    private final double[] x2;
    private final double[] x1Exact;
    private final double[] x2Exact;
    private final double[][] u;
    private int kExact;
    private double[][] y;
    private int k;
    private double[][] res;

    public DirichletProblem() {
        n1 = (int) ((B - A) / H1);
        n2 = (int) ((D - C) / H2);
        n1Exact = (int) ((B - A) / H1_EXACT);
        n2Exact = (int) ((D - C) / H2_EXACT);

        x1 = getX(H1, n1, A);
        x2 = getX(H2, n2, C);
        x1Exact = getX(H1_EXACT, n1Exact, A);
        x2Exact = getX(H2_EXACT, n2Exact, C);

        u = getExact();
    }

    public void solve() {
        PoissonEquationSolver peSolver = new PoissonEquationSolver(n1, n2, H1, H2, EPS, x1, x2);
        y = peSolver.getY();
        k = peSolver.getK();
        res = getResidual(y);
    }

    public void out() {
        Formatter fmt = new Formatter();

        fmt.format("\n\"Точное\" решение (этот же метод с шагами 0.005 и 0.01):\n");
        outY(fmt, u);
        fmt.format("Количество итераций метода Зейделя: %d.\n", kExact);

        fmt.format("\nПолученное решение:\n");
        outY(fmt, y);
        fmt.format("Количество итераций метода Зейделя: %d.\n", k);
        fmt.format("Вектор невязок:\n");
        outRes(fmt, res);

        System.out.println(fmt);
    }

    private void outY(Formatter fmt, double[][] y) {
        for (int j = 0; j <= n2; j++) {
            for (int i = n1; i >= 0; i--) {
                fmt.format("% 11.7f", y[i][j]);
            }

            fmt.format("\n");
        }
    }

    private void outRes(Formatter fmt, double[][] res) {
        for (int j = 0; j <= n2; j++) {
            for (int i = n1; i >= 0; i--) {
                fmt.format("% 11.3E", res[i][j]);
            }

            fmt.format("\n");
        }
    }

    private double[][] getResidual(double[][] y) {
        double[][] res = new double[n1 + 1][n2 + 1];

        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                res[i][j] = Math.abs(u[i][j] - y[i][j]);
            }
        }

        return res;
    }

    private double[][] getExact() {
        double[][] res = new double[n1 + 1][n2 + 1];
        double[][] fullRes;

        PoissonEquationSolver peSolver = new PoissonEquationSolver
                (n1Exact, n2Exact, H1_EXACT, H2_EXACT, EPS_EXACT, x1Exact, x2Exact);
        fullRes = peSolver.getY();
        kExact = peSolver.getK();

        for (int i = 0, k = 0; i <= n1Exact; i += n1Exact / n1, k++) {
            for (int j = 0, s = 0; j <= n2Exact; j += n2Exact / n2, s++) {
                res[k][s] = fullRes[i][j];
            }
        }

        return res;
    }

    private double[] getX(double h, int n, double a) {
        double[] res = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = a + (i * h);
        }

        return res;
    }
}

public class Main {

    public static void main(String[] args) {
        DirichletProblem dp = new DirichletProblem();
        dp.solve();
        dp.out();
    }
}
