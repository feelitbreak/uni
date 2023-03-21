class F {
    public static double getValue(double x) {
        return Math.pow(Math.sin(x), 2.);
    }
}

class K {
    public static double getValue(double x) {
        return Math.pow(Math.cos(x), 2.) + 1.;
    }

    public static double getDxValue(double x) {
        return - Math.sin(2. * x);
    }
}

class Q {
    public static double getValue() {
        return 1.;
    }
}

class TridiagMatrixDiffAlgorithm {
    private final int n;
    private final double[] a;
    private final double[] c;
    private final double[] b;
    private final double[] f;
    private final double kap1;
    private final double nu1;
    private final double kap2;
    private final double nu2;

    public TridiagMatrixDiffAlgorithm
            (int n, double[] a, double[] c, double[] b, double[] f,
             double kap1, double nu1, double kap2, double nu2) {
        this.n = n;
        this.a = a;
        this.c = c;
        this.b = b;
        this.f = f;
        this.kap1 = kap1;
        this.nu1 = nu1;
        this.kap2 = kap2;
        this.nu2 = nu2;
    }

    public double[] solve() {
        double[] alpha, beta;
        alpha = genAlpha();
        beta = genBeta(alpha);

        double[] y;
        y = genY(alpha, beta);

        return y;
    }

    private double[] genAlpha() {
        double[] res = new double[n];
        res[0] = kap1;

        for (int i = 0; i < n - 1; i++) {
            res[i + 1] = b[i] / (c[i] - (a[i] * res[i]));
        }

        return res;
    }

    private double[] genBeta(double[] alpha) {
        double[] res = new double[n];
        res[0] = nu1;

        for (int i = 0; i < n - 1; i++) {
            res[i + 1] = (f[i] + (res[i] * a[i])) / (c[i] - (a[i] * alpha[i]));
        }

        return res;
    }

    private double[] genY(double[] alpha, double[] beta) {
        double[] res = new double[n + 1];
        res[n] = (nu2 + kap2 * beta[n - 1]) / (1. - (alpha[n - 1] * kap2));

        for (int i = n - 1; i >= 0; i--) {
            res[i] = alpha[i] * res[i + 1] + beta[i];
        }

        return res;
    }
}

class DiffOperatorsMethod {

}

class BoundaryValueProblem {
    private static final double KAP0 = 1.;
    private static final double G0 = 0;
    private static final double KAP1 = 1.;
    private static final double G1 = 1.;
    private static final double H = 0.1;
    private int n;
    private double[] x;
    private double[] k;
    private double[] kDx;
    private double[] q;
    private double[] f;
    private double[] y1;

    public BoundaryValueProblem() {
        n = (int) (1. / H);

        x = new double[n + 1];
        k = new double[n + 1];
        kDx = new double[n + 1];
        q = new double[n + 1];
        f = new double[n + 1];

        genX();
        genK(x);
        genKDx(x);
        genQ();
        genF(x);
    }

    public void solve() {
        
    }

    private void genX() {
        for (int i = 0; i <= n; i++) {
            x[i] = i * H;
        }
    }

    private void genK(double[] x) {
        for (int i = 0; i <= n; i++) {
            k[i] = K.getValue(x[i]);
        }
    }

    private void genKDx(double[] x) {
        for (int i = 0; i <= n; i++) {
            kDx[i] = K.getDxValue(x[i]);
        }
    }

    private void genQ() {
        for (int i = 0; i <= n; i++) {
            q[i] = Q.getValue();
        }
    }

    private void genF(double[] x) {
        for (int i = 0; i <= n; i++) {
            f[i] = F.getValue(x[i]);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        BoundaryValueProblem bvp = new BoundaryValueProblem();
    }
}
