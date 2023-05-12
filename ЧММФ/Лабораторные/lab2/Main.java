import java.util.*;

class U {
    public static double getValue(double x, double t) {
        return (100. * Math.pow(t, 2.)) + Math.pow(x, 2.) + (20. * x * t);
    }
}

class Mu0 {
    public static double getValue(double t) {
        return 100. * Math.pow(t, 2.);
    }
}

class U0 {
    public static double getValue(double x) {
        return Math.pow(x, 2.);
    }
}

record ConvectionDiffusionEquationSolver(int n1, int n2, double h, double tau, double[] x, double[] t) {
    private static final double A = -10.;

    public double[][] getY(double sigma) {
        double[][] res;

        if (sigma == 0.) {
            res = getYSigma0();
        } else {
            res = getYSigmaNot0(sigma);
        }

        return res;
    }

    private double[][] getYSigma0() {
        double[][] res = new double[n2 + 1][n1 + 1];
        setBoundaryValues(res);

        double gamma = (A * tau) / h;
        for (int j = 0; j <= n2 - 1; j++) {
            for (int i = 1; i <= n1 - 1; i++) {
                res[j + 1][i] = ((1. + gamma) * res[j][i]) - (gamma * res[j][i + 1]);
            }
        }

        return res;
    }

    private double[][] getYSigmaNot0(double sigma) {
        double[][] res = new double[n2 + 1][n1 + 1];
        setBoundaryValues(res);

        double gamma = (A * tau) / h;
        for (int j = 0; j <= n2 - 1; j++) {
            for (int i = 1; i <= n1 - 1; i++) {
                res[j + 1][i + 1] = (1. - (gamma * sigma)) * res[j + 1][i];
                res[j + 1][i + 1] -= (1. + (gamma * (1. - sigma))) * res[j][i];
                res[j + 1][i + 1] += gamma * (1. - sigma) * res[j][i + 1];
                res[j + 1][i + 1] /= -(gamma * sigma);
            }
        }

        return res;
    }

    private void setBoundaryValues(double[][] y) {
        for (int j = 0; j <= n2; j++) {
            y[j][0] = Mu0.getValue(t[j]);
        }

        for (int i = 1; i <= n1; i++) {
            y[0][i] = U0.getValue(x[i]);
        }
    }
}

class BoundaryValueProblem {
    private static final double H = 0.1;
    private static final double TAU = 0.005;
    private final int n1;
    private final int n2;
    private final double[] x;
    private final double[] t;
    private final double[][] u;
    private double[][] y1;
    private double[][] res1;
    private static final double SIGMA1 = 0.;
    private double[][] y2;
    private double[][] res2;
    private static final double SIGMA2 = 1.;

    public BoundaryValueProblem() {
        n1 = (int) (1. / H);
        n2 = (int) (1. / TAU);

        x = getX();
        t = getT();
        u = getExact(x, t);
    }

    public void solve() {
        ConvectionDiffusionEquationSolver cdeSolver = new ConvectionDiffusionEquationSolver(n1, n2, H, TAU, x, t);
        y1 = cdeSolver.getY(SIGMA1);
        res1 = getResidual(y1);

        y2 = cdeSolver.getY(SIGMA2);
        res2 = getResidual(y2);
    }

    public void out() {

    }

    private double[][] getResidual(double[][] y) {
        double[][] res = new double[n2 + 1][n1 + 1];

        for (int j = 0; j <= n2; j++) {
            for (int i = 0; i <= n1; i++) {
                res[j][i] = Math.abs(u[j][i] - y[j][i]);
            }
        }

        return res;
    }

    private double[] getX() {
        double[] res = new double[n1 + 1];
        for (int i = 0; i <= n1; i++) {
            res[i] = i * H;
        }

        return res;
    }

    private double[] getT() {
        double[] res = new double[n2 + 1];
        for (int j = 0; j <= n2; j++) {
            res[j] = j * TAU;
        }

        return res;
    }

    private double[][] getExact(double[] x, double[] t) {
        double[][] res = new double[n2 + 1][n1 + 1];

        for (int j = 0; j <= n2; j++) {
            for (int i = 0; i <= n1; i++) {
                res[j][i] = U.getValue(x[i], t[j]);
            }
        }

        return res;
    }
}

public class Main {

    public static void main(String[] args) {
        BoundaryValueProblem bvp = new BoundaryValueProblem();
        bvp.solve();
        bvp.out();
    }
}
