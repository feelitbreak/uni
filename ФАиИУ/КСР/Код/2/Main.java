import java.util.*;

class Equation {
    private final double[][] c;
    private final double[] d;
    private double[] xk;
    private int k;
    private final static double E = 0.001;
    private final static double ALPHA = 0.8;

    public Equation() {
        c = new double[4][4];
        for(int i = 0; i < 4; i++) {
            c[i][i] = 0.;
        }
        c[0][1] = 5. / 16.;
        c[0][2] = 2. / 16.;
        c[0][3] = - 1. / 16.;
        c[1][0] = 1. / 4.;
        c[1][2] = 0.;
        c[1][3] = 2. / 4.;
        c[2][0] = 1. / 5.;
        c[2][1] = 2. / 5.;
        c[2][3] = - 1. / 5.;
        c[3][0] = - 2. / 25.;
        c[3][1] = 6. / 25.;
        c[3][2] = - 5. / 25.;

        d = new double[4];
        d[0] = 55. / 16.;
        d[1] = 14. / 4.;
        d[2] = - 28. / 5.;
        d[3] = - 144. / 25.;
    }

    public void algorithm() {
        double[] x1 = {0., 0., 0., 0.};
        double[] x2 = f(x1);
        k = 1;
        double c = ALPHA / (1 - ALPHA);
        while(c * norm(diff(x1, x2)) > E) {
            x1 = x2;
            x2 = f(x1);
            k++;
        }
        xk = x2;
    }

    public void showRes() {
        System.out.print("Решение на последней итерации: ");
        System.out.println(Arrays.toString(xk));
        System.out.print("Номер итерации: ");
        System.out.println(k);
    }

    private double[] f(double[] x) {
        double[] res = Arrays.copyOf(d, d.length);
        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x.length; j++) {
                res[i] += c[i][j] * x[j];
            }
        }
        return res;
    }

    private double[] diff(double[] x1, double[] x2) {
        double[] res = new double[x1.length];
        for(int i = 0; i < res.length; i++) {
            res[i] = x1[i] - x2[i];
        }
        return res;
    }
    private double norm(double[] x) {
        double max = 0.;
        for (double i : x) {
            if (Math.abs(i) > max) {
                max = Math.abs(i);
            }
        }
        return max;
    }
}

public class Main {

    public static void main(String[] args) {
        Equation myEquation = new Equation();
        myEquation.algorithm();
        myEquation.showRes();
    }
}