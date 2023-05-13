import java.util.*;

class F {
    public static double getValue(double t, double u) {
        return (Math.pow(u, 2.) * Math.log(t) - u) / t;
    }

    public static double getDuValue(double t, double u) {
        return (2. * u * Math.log(t) - 1) / t;
    }
}

class U {
    public static double getValue(double t) {
        return 1. / (Math.log(t) + 1);
    }
}

class BackwardEulerMethod {
    private final int n;
    private final double tau;
    private final double[] t;
    private final double u0;

    public BackwardEulerMethod(int n, double tau, double[] t, double u0) {
        this.n = n;
        this.tau = tau;
        this.t = t;
        this.u0 = u0;
    }

    public double[] getY() {
        double[] res = new double[this.n + 1];
        res[0] = this.u0;

        for (int i = 1; i <= this.n; i++) {
            res[i] = newtonMethod(res[i - 1], t[i]);
        }

        return res;
    }

    public double newtonMethod(double yJ, double t) {
        final double lteDeg = 2.;
        double eps = Math.pow(this.tau, lteDeg + 1.);

        double nwt1 = yJ;
        double nwt2 = phiNewton(nwt1, yJ, t);
        while (Math.abs(nwt2 - nwt1) > eps) {
            nwt1 = nwt2;
            nwt2 = phiNewton(nwt2, yJ, t);
        }

        return nwt2;
    }

    private double phiNewton(double y, double yJ, double t) {
        return y - (y - yJ - this.tau * F.getValue(t, y)) / (1 - this.tau * F.getDuValue(t, y));
    }
}

class RungeKuttaMethod {
    public static double[] getY(int n, double tau, double[] t, double u0) {
        double[] res = new double[n + 1];
        res[0] = u0;

        double k1, k2;
        for (int i = 1; i <= n; i++) {
            k1 = F.getValue(t[i - 1], res[i - 1]);
            k2 = F.getValue(t[i], res[i - 1] + tau * k1);
            res[i] = res[i - 1] + 0.5 * tau * (k1 + k2);
        }

        return res;
    }
}

class IncreasingAccuracyMethod {
    public static double[] getY(int n, double tau, double[] t, double u0) {
        double[] res = new double[n + 1];
        res[0] = u0;

        double y12;
        for (int i = 1; i <= n; i++) {
            y12 = res[i - 1] + 0.5 * tau * F.getValue(t[i - 1], res[i - 1]);
            res[i] = res[i - 1] + tau * F.getValue(t[i - 1] + 0.5 * tau, y12);
        }

        return res;
    }
}

class MultistepMethod {
    public static double[] getY(int n, double tau, double[] t, double u0) {
        double[] res = new double[n + 1];

        res[0] = u0;
        res[1] = MultistepMethod.getYIncreasingMethod(tau, t[0], res[0]);
        res[2] = MultistepMethod.getYIncreasingMethod(tau, t[1], res[1]);

        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + (tau / 12.) *
                    (23. * F.getValue(t[i - 1], res[i - 1]) -
                            16. * F.getValue(t[i - 2], res[i - 2]) +
                            5. * F.getValue(t[i - 3], res[i - 3]));
        }

        return res;
    }

    private static double getYIncreasingMethod(double tau, double t, double y) {
        double y12;
        double y14;
        double y11;
        double res;

        y14 = y + (tau / 4.) * F.getValue(t, y);
        y12 = y + (tau / 2.) * F.getValue(t + (tau / 4.), y14);
        y11 = y + tau * F.getValue(t + (tau / 2.), y12);
        res = y + (tau / 6.) * (F.getValue(t, y) + 4. * F.getValue(t + (tau / 2.), y12) + F.getValue(t + tau, y11));

        return res;
    }
}

class CauchyProblem {
    private final static int N = 10;
    private final static double A = 1.;
    private final static double B = 2.;
    private final static double U0 = 1.;
    private final double tau;
    private final double[] t;
    private final double[] u;
    private double[] y1;
    private double[] y2;
    private double[] y3;
    private double[] y4;
    private double[] res1;
    private double[] res2;
    private double[] res3;
    private double[] res4;


    public CauchyProblem() {
        this.tau = (B - A) / N;

        this.t = new double[N + 1];
        this.t[0] = A;
        for (int i = 1; i <= N; i++) {
            this.t[i] = this.t[i - 1] + this.tau;
        }

        this.u = new double[N + 1];
        for (int i = 0; i <= N; i++) {
            this.u[i] = U.getValue(this.t[i]);
        }
    }

    public void solve() {
        BackwardEulerMethod bem = new BackwardEulerMethod(N, this.tau, this.t, U0);
        this.y1 = bem.getY();
        this.res1 = this.getResidual(this.y1);

        this.y2 = RungeKuttaMethod.getY(N, this.tau, this.t, U0);
        this.res2 = this.getResidual(this.y2);

        this.y3 = IncreasingAccuracyMethod.getY(N, this.tau, this.t, U0);
        this.res3 = this.getResidual(this.y3);

        this.y4 = MultistepMethod.getY(N, this.tau, this.t, U0);
        this.res4 = this.getResidual(this.y4);
    }

    public void outResult() {
        Formatter fmt = new Formatter();

        fmt.format("Точное решение уравнения:\n");
        for (int i = 0; i <= N; i++) {
            fmt.format("%.7f\n", this.u[i]);
        }

        fmt.format("\nЗадание 1. Неявный метод Эйлера.\n");
        this.outYAndRes(fmt, this.y1, this.res1);

        fmt.format("\nЗадание 2. Метод Рунге-Кутта.\n");
        this.outYAndRes(fmt, this.y2, this.res2);

        fmt.format("\nЗадание 3. Метод последовательного повышения порядка точности.\n");
        this.outYAndRes(fmt, this.y3, this.res3);

        fmt.format("\nЗадание 4. Экстраполяционный метод Адамса.\n");
        this.outYAndRes(fmt, this.y4, this.res4);

        System.out.println(fmt);
    }

    private void outYAndRes(Formatter fmt, double[] y, double[] res) {
        fmt.format("Полученное решение:\n");
        for (int i = 0; i <= N; i++) {
            fmt.format("%.7f\n", y[i]);
        }
        fmt.format("Вектор невязок:\n");
        for (int i = 0; i <= N; i++) {
            fmt.format("%E\n", res[i]);
        }
    }

    private double[] getResidual(double[] y) {
        double[] res = new double[N + 1];
        for (int i = 0; i <= N; i++) {
            res[i] = Math.abs(this.u[i] - y[i]);
        }

        return res;
    }
}

public class Main {

    public static void main(String[] args) {
        CauchyProblem cp = new CauchyProblem();
        cp.solve();
        cp.outResult();
    }
}
