import java.util.*;

class F {
    public static double getValue(double x) {
        return x * Math.pow(1. + x, 1. / 3.);
    }
}

class RRS {
    public static double approxIntegral(double a, double b, double h) {
        double res = 0.;
        double x = a + h;
        int n = (int)((b - a) / h);

        for (int i = 0; i < n; i++) {
            res += F.getValue(x);
            x += h;
        }

        return res * h;
    }
}

class RungeLaw {
    private final static int STEP_DECREASE = 2;
    private final double integralVal;
    private double hRes;
    private double qRes;

    public RungeLaw(double integralVal) {
        this.integralVal = integralVal;
    }

    public void approxIntegral(double a, double b, double h0, double e) {
        double h1 = h0;

        double h2 = h1 / STEP_DECREASE;

        double q1 = RRS.approxIntegral(a, b, h1);
        double q2 = RRS.approxIntegral(a, b, h2);

        while (Math.abs(this.getResidual(q1, q2)) > e) {
            h1 = h2;
            h2 = h1 / STEP_DECREASE;
            q1 = q2;
            q2 = RRS.approxIntegral(a, b, h2);
        }

        this.hRes = h2;
        this.qRes = q2;
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Величина шага:\n");
        fmt.format("%.8f\n", this.hRes);
        fmt.format("Полученное приближённое значение интеграла:\n");
        fmt.format("%.7f\n", this.qRes);
        fmt.format("Невязка:\n");
        fmt.format("%.7f\n", Math.abs(this.qRes - this.integralVal));
        System.out.println(fmt);
    }

    private double getResidual(double q1, double q2) {
        return q2 - q1;
    }
}

class MRS {
    private final double integralVal;
    private double qRes;

    public MRS(double integralVal) {
        this.integralVal = integralVal;
    }

    public void approxIntegral(double a, double b, int n) {
        double res = 0.;
        double h = (b - a) / n;

        for(double i = 0.; i < n; i++) {
            res += F.getValue(a + (0.5 * h) + (i * h));
        }

        this.qRes = res * h;
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое значение интеграла, формула средних прямоугольников:\n");
        fmt.format("%.7f\n", this.qRes);
        fmt.format("Невязка:\n");
        fmt.format("%.7f\n", Math.abs(this.qRes - this.integralVal));
        System.out.print(fmt);
    }
}

class Simpson {
    private final double integralVal;
    private double qRes;

    public Simpson(double integralVal) {
        this.integralVal = integralVal;
    }

    public void approxIntegral(double a, double b, int n) {
        double res = 0.;
        double h = (b - a) / n;

        res += F.getValue(a);
        res += F.getValue(b);

        double x = a + h;
        for(int i = 1; i < n - 1; i++, x += h) {
            res += 4. * F.getValue(x);
            x += h;
            i++;
            res += 2. * F.getValue(x);
        }
        res += 4. * F.getValue(x);

        this.qRes = (res * h) / 3.;
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое значение интеграла, формула Симпсона:\n");
        fmt.format("%.7f\n", this.qRes);
        fmt.format("Невязка:\n");
        fmt.format("%.7f\n", Math.abs(this.qRes - this.integralVal));
        System.out.println(fmt);
    }
}

class Gauss {
    private static final int N = 4;
    private static final double[] X = { 8.624719, 7.153877, 5., 2.846123, 1.375281 };
    private static final double[] A = { 0.947708, 1.914515, 2.275556, 1.914515, 0.947708 };
    private final double integralVal;
    private double qRes;

    public Gauss(double integralVal) {
        this.integralVal = integralVal;
    }

    public void approxIntegral() {
        double res = 0.;

        for(int i = 0; i <= N; i++) {
            res += A[i] * F.getValue(X[i]);
        }

        this.qRes = res;
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое значение интеграла:\n");
        fmt.format("%.7f\n", this.qRes);
        fmt.format("Невязка:\n");
        fmt.format("%.7f\n", Math.abs(this.qRes - this.integralVal));
        System.out.println(fmt);
    }
}

public class Main {
    private static final double A = 1.;
    private static final double B = 9.;
    private static final double H0 = 1.;
    private static final double E = 0.00001;
    private static final double I = 75.90467203;
    private static final int N1 = 870;
    private static final int N2 = 42;

    public static void main(String[] args) {
        System.out.println("Точное значение интеграла:");
        System.out.println(I);
        System.out.println();

        System.out.println("Задание 1:");

        RungeLaw rl = new RungeLaw(I);
        rl.approxIntegral(A, B, H0, E);
        rl.outRes();

        System.out.println("Задание 2:");

        MRS mrs = new MRS(I);
        mrs.approxIntegral(A, B, N1);
        mrs.outRes();

        Simpson s = new Simpson(I);
        s.approxIntegral(A, B, N2);
        s.outRes();

        System.out.println("Задание 3:");

        Gauss g = new Gauss(I);
        g.approxIntegral();
        g.outRes();
    }
}
