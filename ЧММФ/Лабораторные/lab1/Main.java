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
    public static double getValue(double x) {
        return 1.;
    }
}

class Partition {
    private final int n;
    private final double h;
    private final double[] x;

    public Partition(int n, double h) {
        this.n = n;
        this.h = h;

        x = new double[n + 1];
        genX();
    }

    public double[] getX() {
        return x;
    }

    private void genX() {
        for (int i = 0; i <= n; i++) {
            x[i] = i * h;
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

class IntegralApprox {
    public static double TrapezoidalRule (double a, double b, double fA, double fB) {
        return (b - a) * ((fA + fB) / 2.);
    }

    public static double MidpointRule (double a, double b, double fAb2) {
        return (b - a) * fAb2;
    }
}

interface Method {
    default double[] getY(int n) {
        double[] a = getA();
        double[] c = getC();
        double[] b = getB();
        double[] f = getF();

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

    double[] getA();
    double[] getC();
    double[] getB();
    double[] getF();
    double getWavyKap0();
    double getWavyG0();
    double getNewKap1(double wavyKap0);
    double getNu1(double wavyKap0, double wavyG0);
    double getWavyKap1();
    double getWavyG1();
    double getNewKap2(double wavyKap1);
    double getNu2(double wavyKap1, double wavyG1);
}

record DiffOperatorsMethod (int n, double h, double[] x, double kap0, double g0,
                           double kap1, double g1) implements Method {

    @Override
    public double[] getA() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = ((K.getValue(x[i + 1]) / h) - (K.getDxValue(x[i + 1]) / 2.)) / h;
        }

        return res;
    }

    @Override
    public double[] getC() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = ((2. * K.getValue(x[i + 1])) / Math.pow(h, 2.)) + Q.getValue(x[i + 1]);
        }

        return res;
    }

    @Override
    public double[] getB() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = ((K.getValue(x[i + 1]) / h) + (K.getDxValue(x[i + 1]) / 2.)) / h;
        }

        return res;
    }

    @Override
    public double[] getF() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = F.getValue(x[i + 1]);
        }

        return res;
    }

    @Override
    public double getNewKap1(double wavyKap0) {
        return K.getValue(x[0]) / ((h * wavyKap0) +  K.getValue(x[0]));
    }

    @Override
    public double getNu1(double wavyKap0, double wavyG0) {
        return (h * wavyG0) / ((h * wavyKap0) +  K.getValue(x[0]));
    }

    @Override
    public double getWavyKap0() {
        return (kap0 * (1 - (h / 2.) * ( K.getDxValue(x[0]) /  K.getValue(x[0])))) + ((h / 2.) *  Q.getValue(x[0]));
    }

    @Override
    public double getWavyG0() {
        return (g0 * (1 - (h / 2.) * ( K.getDxValue(x[0]) /  K.getValue(x[0])))) + ((h / 2.) *  F.getValue(x[0]));
    }

    @Override
    public double getNewKap2(double wavyKap1) {
        return  K.getValue(x[n]) / ((h * wavyKap1) + K.getValue(x[n]));
    }

    @Override
    public double getNu2(double wavyKap1, double wavyG1) {
        return (h * wavyG1) / ((h * wavyKap1) + K.getValue(x[n]));
    }

    @Override
    public double getWavyKap1() {
        return (kap1 * (1 + (h / 2.) * (K.getDxValue(x[n]) / K.getValue(x[n])))) + ((h / 2.) * Q.getValue(x[n]));
    }

    @Override
    public double getWavyG1() {
        return (g1 * (1 + (h / 2.) * (K.getDxValue(x[n]) / K.getValue(x[n])))) + ((h / 2.) * F.getValue(x[n]));
    }
}

abstract class BalanceAndRitzMethod implements Method {
    protected final int n;
    protected final double h;
    protected final double[] x;
    protected final double kap0;
    protected final double g0;
    protected final double kap1;
    protected final double g1;

    public BalanceAndRitzMethod(int n, double h, double[] x, double kap0, double g0, double kap1, double g1) {
        this.n = n;
        this.h = h;
        this.x = x;
        this.kap0 = kap0;
        this.g0 = g0;
        this.kap1 = kap1;
        this.g1 = g1;
    }

    @Override
    public double[] getA() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = getAI(i + 1) / (h * h);
        }

        return res;
    }

    @Override
    public double[] getC() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = (getAI(i + 2) + getAI(i + 1)) / (h * h);
            res[i] += getDI(i + 1);
        }

        return res;
    }

    @Override
    public double[] getB() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = getAI(i + 2) / (h * h);
        }

        return res;
    }

    @Override
    public double[] getF() {
        double[] res = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            res[i] = getPhiI(i + 1);
        }

        return res;
    }

    @Override
    public double getNewKap1(double wavyKap0) {
        return getAI(1) / ((h * wavyKap0) +  getAI(1));
    }

    @Override
    public double getNu1(double wavyKap0, double wavyG0) {
        return (h * wavyG0) / ((h * wavyKap0) +  getAI(1));
    }

    @Override
    public double getWavyKap0() {
        return kap0 + ((h / 2.) * getD0());
    }

    @Override
    public double getWavyG0() {
        return g0 + ((h / 2.) * getPhi0());
    }

    @Override
    public double getNewKap2(double wavyKap1) {
        return getAI(n) / ((h * wavyKap1) +  getAI(n));
    }

    @Override
    public double getNu2(double wavyKap1, double wavyG1) {
        return (h * wavyG1) / ((h * wavyKap1) +  getAI(n));
    }

    @Override
    public double getWavyKap1() {
        return kap1 + ((h / 2.) * getDN());
    }

    @Override
    public double getWavyG1() {
        return g1 + ((h / 2.) * getPhiN());
    }

    protected abstract double getAI(int i);
    protected abstract double getDI(int i);
    protected abstract double getPhiI(int i);
    protected abstract double getD0();
    protected abstract double getPhi0();
    protected abstract double getDN();
    protected abstract double getPhiN();
}

class BalanceMethod extends BalanceAndRitzMethod {

    public BalanceMethod(int n, double h, double[] x, double kap0, double g0, double kap1, double g1) {
        super(n, h, x, kap0, g0, kap1, g1);
    }

    @Override
    protected double getAI(int i) {
        double kA = 1 / K.getValue(x[i - 1]);
        double kB = 1 / K.getValue(x[i]);
        return h / IntegralApprox.TrapezoidalRule(x[i - 1], x[i], kA, kB);
    }

    @Override
    protected double getDI(int i) {
        double a = x[i] - (h / 2.);
        double b = x[i] + (h / 2.);
        double qA = Q.getValue(a);
        double qB = Q.getValue(b);
        return IntegralApprox.TrapezoidalRule(a, b, qA, qB) / h;
    }

    @Override
    protected double getPhiI(int i) {
        double a = x[i] - (h / 2.);
        double b = x[i] + (h / 2.);
        double fA = F.getValue(a);
        double fB = F.getValue(b);
        return IntegralApprox.TrapezoidalRule(a, b, fA, fB) / h;
    }

    @Override
    protected double getD0() {
        double qA = Q.getValue(0);
        double qB = Q.getValue(h / 2.);
        return (2 * IntegralApprox.TrapezoidalRule(0, h / 2., qA, qB)) / h;
    }

    @Override
    protected double getPhi0() {
        double fA = F.getValue(0);
        double fB = F.getValue(h / 2.);
        return (2 * IntegralApprox.TrapezoidalRule(0, h / 2., fA, fB)) / h;
    }

    @Override
    protected double getDN() {
        double a = 1 - (h / 2.);
        double b = 1.;
        double qA = Q.getValue(a);
        double qB = Q.getValue(b);
        return (2 * IntegralApprox.TrapezoidalRule(a, b, qA, qB)) / h;
    }

    @Override
    protected double getPhiN() {
        double a = 1 - (h / 2.);
        double b = 1.;
        double fA = F.getValue(a);
        double fB = F.getValue(b);
        return (2 * IntegralApprox.TrapezoidalRule(a, b, fA, fB)) / h;
    }
}

class RitzMethod extends BalanceAndRitzMethod {

    public RitzMethod(int n, double h, double[] x, double kap0, double g0, double kap1, double g1) {
        super(n, h, x, kap0, g0, kap1, g1);
    }

    @Override
    protected double getAI(int i) {
        double midpoint;
        double fValue1, fValue2;
        double iValue1, iValue2;

        midpoint = (x[i - 1] + x[i]) / 2.;

        fValue1 = K.getValue(midpoint);
        iValue1 = IntegralApprox.MidpointRule(x[i - 1], x[i], fValue1);

        fValue2 = Q.getValue(midpoint) * (midpoint - x[i - 1]) * (x[i] - midpoint);
        iValue2 = IntegralApprox.MidpointRule(x[i - 1], x[i], fValue2);

        return (iValue1 - iValue2) / h;
    }

    @Override
    protected double getDI(int i) {
        double midpoint1, midpoint2;
        double fValue1, fValue2;
        double iValue1, iValue2;

        midpoint1 = (x[i - 1] + x[i]) / 2.;
        midpoint2 = (x[i] + x[i + 1]) / 2.;

        fValue1 = Q.getValue(midpoint1) * (midpoint1 - x[i - 1]);
        iValue1 = IntegralApprox.MidpointRule(x[i - 1], x[i], fValue1);

        fValue2 = Q.getValue(midpoint2) * (x[i + 1] - midpoint2);
        iValue2 = IntegralApprox.MidpointRule(x[i], x[i + 1], fValue2);

        return (iValue1 - iValue2) / (h * h);
    }

    @Override
    protected double getPhiI(int i) {
        double midpoint1, midpoint2;
        double fValue1, fValue2;
        double iValue1, iValue2;

        midpoint1 = (x[i - 1] + x[i]) / 2.;
        midpoint2 = (x[i] + x[i + 1]) / 2.;

        fValue1 = F.getValue(midpoint1) * (midpoint1 - x[i - 1]);
        iValue1 = IntegralApprox.MidpointRule(x[i - 1], x[i], fValue1);

        fValue2 = F.getValue(midpoint2) * (x[i + 1] - midpoint2);
        iValue2 = IntegralApprox.MidpointRule(x[i], x[i + 1], fValue2);

        return (iValue1 - iValue2) / (h * h);
    }

    @Override
    protected double getD0() {
        double midpoint;
        double fValue, iValue;

        midpoint = h / 2.;
        fValue = Q.getValue(midpoint) * (h - midpoint);
        iValue = IntegralApprox.MidpointRule(0, h, fValue);

        return (2. * iValue) / (h * h);
    }

    @Override
    protected double getPhi0() {
        double midpoint;
        double fValue, iValue;

        midpoint = h / 2.;
        fValue = F.getValue(midpoint) * (h - midpoint);
        iValue = IntegralApprox.MidpointRule(0, h, fValue);

        return (2. * iValue) / (h * h);
    }

    @Override
    protected double getDN() {
        double midpoint;
        double fValue, iValue;

        midpoint = (1. - h + 1.) / 2.;
        fValue = Q.getValue(midpoint) * (midpoint - 1. + h);
        iValue = IntegralApprox.MidpointRule(1. - h, h, fValue);

        return (2. * iValue) / (h * h);
    }

    @Override
    protected double getPhiN() {
        double midpoint;
        double fValue, iValue;

        midpoint = (1. - h + 1.) / 2.;
        fValue = F.getValue(midpoint) * (midpoint - 1. + h);
        iValue = IntegralApprox.MidpointRule(1. - h, h, fValue);

        return (2. * iValue) / (h * h);
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
    private double[] y2;
    private double[] res2;
    private double[] y3;
    private double[] res3;

    public BoundaryValueProblem() {
        n = (int) (1. / H);
        nExact = (int) (1. / H_EXACT);

        Partition exPart = new Partition(nExact, H_EXACT);
        u = getExact(exPart);
    }

    public void solve() {
        Partition p = new Partition(n, H);
        double[] x = p.getX();

        DiffOperatorsMethod dom = new DiffOperatorsMethod(n, H, x, KAP0, G0, KAP1, G1);
        y1 = dom.getY(n);
        res1 = getResidual(y1);

        BalanceMethod bm = new BalanceMethod(n, H, x, KAP0, G0, KAP1, G1);
        y2 = bm.getY(n);
        res2 = getResidual(y2);

        RitzMethod rm = new RitzMethod(n, H, x, KAP0, G0, KAP1, G1);
        y3 = rm.getY(n);
        res3 = getResidual(y3);
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

        fmt.format("\nЗадание 2. Метод баланса.\n");
        fmt.format("Полученное решение:\n");
        outY(fmt, y2);
        fmt.format("Вектор невязок:\n");
        outRes(fmt, res2);

        fmt.format("\nЗадание 3. Метод Ритца.\n");
        fmt.format("Полученное решение:\n");
        outY(fmt, y3);
        fmt.format("Вектор невязок:\n");
        outRes(fmt, res3);

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
                (nExact, H_EXACT, p.getX(), KAP0, G0, KAP1, G1);
        fullRes = dom.getY(nExact);

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
