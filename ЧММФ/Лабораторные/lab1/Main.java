import java.util.*;

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

class Partition {
    private final int n;
    private final double h;
    private final double[] x;
    private final double[] k;
    private final double[] kDx;
    private final double[] q;
    private final double[] f;

    public Partition(int n, double h) {
        this.n = n;
        this.h = h;

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

    public double[] getK() {
        return k;
    }

    public double[] getKDx() {
        return kDx;
    }

    public double[] getQ() {
        return q;
    }

    public double[] getF() {
        return f;
    }

    private void genX() {
        for (int i = 0; i <= n; i++) {
            x[i] = i * h;
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

record TridiagMatrixDiffAlgorithm(int n, double[] a, double[] c, double[] b, double[] f, double kap1, double nu1,
                                  double kap2, double nu2) {

    public double[] solve() {
        double[] alpha, beta;
        alpha = getAlpha();
        beta = getBeta(alpha);

        double[] y;
        y = getY(alpha, beta);

        return y;
    }

    private double[] getAlpha() {
        double[] res = new double[n];
        res[0] = kap1;

        for (int i = 0; i < n - 1; i++) {
            res[i + 1] = b[i] / (c[i] - (a[i] * res[i]));
        }

        return res;
    }

    private double[] getBeta(double[] alpha) {
        double[] res = new double[n];
        res[0] = nu1;

        for (int i = 0; i < n - 1; i++) {
            res[i + 1] = (f[i] + (res[i] * a[i])) / (c[i] - (a[i] * alpha[i]));
        }

        return res;
    }

    private double[] getY(double[] alpha, double[] beta) {
        double[] res = new double[n + 1];
        res[n] = (nu2 + kap2 * beta[n - 1]) / (1. - (alpha[n - 1] * kap2));

        for (int i = n - 1; i >= 0; i--) {
            res[i] = alpha[i] * res[i + 1] + beta[i];
        }

        return res;
    }
}

record DiffOperatorsMethod(int n, double h, double[] k, double[] kDx, double[] q, double[] f, double kap0, double g0,
                           double kap1, double g1) {

    public double[] getY() {
        double[] a = getA();
        double[] c = getC();
        double[] b = getB();

        double wavyKap0 = getWavyKap0();
        double wavyG0 = getWavyG0();
        double newKap1 = getNewKap1(wavyKap0);
        double nu1 = getNu1(wavyKap0, wavyG0);

        double wavyKap1 = getWavyKap1();
        double wavyG1 = getWavyG1();
        double newKap2 = getNewKap2(wavyKap1);
        double nu2 = getNu2(wavyKap1, wavyG1);

        TridiagMatrixDiffAlgorithm tmda = new TridiagMatrixDiffAlgorithm(n, a, c, b, f, newKap1, nu1, newKap2, nu2);

        return tmda.solve();
    }

    private double[] getA() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = ((k[i + 1] / h) - (kDx[i + 1] / 2.)) / h;
        }

        return res;
    }

    private double[] getC() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = ((2. * k[i + 1]) / Math.pow(h, 2.)) + q[i + 1];
        }

        return res;
    }

    private double[] getB() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = ((k[i + 1] / h) + (kDx[i + 1] / 2.)) / h;
        }

        return res;
    }

    private double getNewKap1(double wavyKap0) {
        return k[0] / ((h * wavyKap0) + k[0]);
    }

    private double getNu1(double wavyKap0, double wavyG0) {
        return (h * wavyG0) / ((h * wavyKap0) + k[0]);
    }

    private double getWavyKap0() {
        return (kap0 * (1 - (h / 2.) * (kDx[0] / k[0]))) + ((h / 2.) * q[0]);
    }

    private double getWavyG0() {
        return (g0 * (1 - (h / 2.) * (kDx[0] / k[0]))) + ((h / 2.) * f[0]);
    }

    private double getNewKap2(double wavyKap1) {
        return k[n] / ((h * wavyKap1) + k[n]);
    }

    private double getNu2(double wavyKap1, double wavyG1) {
        return (h * wavyG1) / ((h * wavyKap1) + k[n]);
    }

    private double getWavyKap1() {
        return (kap1 * (1 + (h / 2.) * (kDx[n] / k[n]))) + ((h / 2.) * q[n]);
    }

    private double getWavyG1() {
        return (g1 * (1 + (h / 2.) * (kDx[n] / k[n]))) + ((h / 2.) * f[n]);
    }
}

class BoundaryValueProblem {
    private static final double KAP0 = 1.;
    private static final double G0 = 0;
    private static final double KAP1 = 1.;
    private static final double G1 = 1.;
    private static final double H = 0.1;
    private static final double H_EXACT = 0.001;
    private final int n;
    private final int nExact;
    private final double[] u;
    private double[] y1;
    private double[] res1;

    public BoundaryValueProblem() {
        n = (int) (1. / H);
        nExact = (int) (1. / H_EXACT);

        Partition exPart = new Partition(nExact, H_EXACT);
        u = getExact(exPart);
    }

    public void solve() {
        Partition p = new Partition(n, H);
        double[] k = p.getK();
        double[] kDx = p.getKDx();
        double[] q = p.getQ();
        double[] f = p.getF();

        DiffOperatorsMethod dom = new DiffOperatorsMethod(n, H, k, kDx, q, f, KAP0, G0, KAP1, G1);
        y1 = dom.getY();
        res1 = getResidual(y1);
    }

    public void outRes() {
        Formatter fmt = new Formatter();

        fmt.format("\nТочное решение:\n");
        outY(fmt, u);

        fmt.format("\nЗадание 1. Аппроксимация разностными операторами.\n");
        fmt.format("Полученное решение:\n");
        outY(fmt, y1);
        fmt.format("Вектор невязок:\n");
        outRes(fmt, res1);

        System.out.println(fmt);
    }

    private void outY(Formatter fmt, double[] y) {
        for (int i = 0; i <= n; i++) {
            fmt.format("%.7f\n", y[i]);
        }
    }

    private void outRes(Formatter fmt, double[] res) {
        for (int i = 0; i <= n; i++) {
            fmt.format("%E\n", res[i]);
        }
    }

    private double[] getResidual(double[] y) {
        double[] res = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = Math.abs(u[i] - y[i]);
        }

        return res;
    }

    private double[] getExact(Partition p) {
        double[] res = new double[n + 1];
        double[] fullRes;

        DiffOperatorsMethod dom = new DiffOperatorsMethod
                (nExact, H_EXACT, p.getK(), p.getKDx(), p.getQ(), p.getF(), KAP0, G0, KAP1, G1);
        fullRes = dom.getY();

        for (int i = 0, j = 0; i <= nExact; i += nExact / n, j++) {
            res[j] = fullRes[i];
        }

        return res;
    }
}

public class Main {

    public static void main(String[] args) {
        BoundaryValueProblem bvp = new BoundaryValueProblem();
        bvp.solve();
        bvp.outRes();
    }
}
