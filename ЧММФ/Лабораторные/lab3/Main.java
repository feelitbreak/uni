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

record PoissonEquationSolver(int n1, int n2, double h1, double h2, double eps, double[] x1, double[] x2) {
    public double[][] getY() {
        double[][] res = new double[n1 + 1][n2 + 1];
        setBoundaryValues(res);
        setInitialValues(res);

        int k = 0;
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

        return res;
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
    private final int n1;
    private final int n2;
    private final double[] x1;
    private final double[] x2;
    private double[][] y;
    private double[][] res;

    public DirichletProblem() {
        n1 = (int) ((B - A) / H1);
        n2 = (int) ((D - C) / H2);

        x1 = getX(H1, n1, A);
        x2 = getX(H2, n2, C);
    }

    public void solve() {
        PoissonEquationSolver peSolver = new PoissonEquationSolver(n1, n2, H1, H2, EPS, x1, x2);
        y = peSolver.getY();
        //res = getResidual(y);
    }

    public void out() {
        Formatter fmt = new Formatter();

        /*
        fmt.format("\nТочное решение:\n");
        outY(fmt, u);
         */

        fmt.format("\nПолученное решение:\n");
        outY(fmt, y);
        /*
        fmt.format("Вектор невязок:\n");
        outRes(fmt, res1);
         */

        System.out.println(fmt);
    }

    private void outY(Formatter fmt, double[][] y) {
        for (int j = 0; j <= n2; j++) {
            for (int i = n1; i >= 0; i--) {
                fmt.format("% 14.7f", y[i][j]);
            }

            fmt.format("\n");
        }
    }

    private void outRes(Formatter fmt, double[][] res) {
        for (int j = n2; j >= 0; j--) {
            for (int i = 0; i <= n1; i++) {
                fmt.format("% 14.7E", res[j][i]);
            }

            fmt.format("\n");
        }
    }

    /*
    private double[][] getResidual(double[][] y) {
        double[][] res = new double[n2 + 1][n1 + 1];

        for (int j = 0; j <= n2; j++) {
            for (int i = 0; i <= n1; i++) {
                res[j][i] = Math.abs(u[j][i] - y[j][i]);
            }
        }

        return res;
    }
     */

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
