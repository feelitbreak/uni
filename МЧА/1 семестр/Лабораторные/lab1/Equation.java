import java.util.*;

public class Equation {
    private double a = - Math.PI / 2;
    private double b = 0;
    private final double e1 = 0.0000001;
    private final double e2 = 0.1;
    private double x0;

    public Equation() {

    }

    public void bisection() {
        Formatter fmt = new Formatter();
        fmt.format("%3s %8s %8s %8s %8s %14s %8s\n", "k", "ak", "bk", "f(ak)", "f(bk)", "(ak + bk) / 2", "bk - ak");
        double xK;
        int k = 0;
        while(Math.abs(b - a) > 2 * e2) {
            xK = (a + b) / 2;
            fmt.format("%3d % 8.5f % 8.5f % 8.5f % 8.5f % 11.5f    % 8.5f\n", k, a, b, f(a), f(b), xK, b - a);
            if(f(xK) * f(a) < 0) {
                b = xK;
            } else {
                a = xK;
            }
        k++;
        }
        x0 = (a + b) / 2;
        fmt.format("%3d % 8.5f % 8.5f %8c %8c % 11.5f    % 8.5f\n", k, a, b, ' ', ' ', x0, b - a);
        System.out.print(fmt);
        fmt.close();
    }

    private double f(double x) {
        return Math.cos(x) + 0.25 * x - 0.5;
    }

    public void fpi() {
        double x1 = x0;
        double x2 = phi(x0);
        while(Math.abs(x2 - x1) >= e1) {
            x1 = x2;
            x2 = phi(x2);
        }
    }

    private double phi(double x) {
        return x - 0.9 * f(x);
    }

}
