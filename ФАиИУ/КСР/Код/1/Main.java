class Equation {
    private double xk;
    private int k;
    private final static double E = 0.0001;
    private final static double ALPHA = 0.1352;

    public void algorithm() {
        double x1 = 0.;
        double x2 = f(x1);
        k = 1;
        double c = ALPHA / (1 - ALPHA);
        while(c * Math.abs(x2 - x1) > E) {
            x1 = x2;
            x2 = f(x1);
            k++;
        }
        xk = x2;
    }

    public void showRes() {
        System.out.print("Решение на последней итерации: ");
        System.out.println(xk);
        System.out.print("Номер итерации: ");
        System.out.println(k);
    }
    private double f(double x) {
        return x - 0.268 * g(x);
    }
    private double g(double x) {
        return x * Math.sqrt(5.) + Math.sin(x) - Math.sqrt(3.) * Math.cos(x) - Math.sqrt(5.);
    }
}

public class Main {

    public static void main(String[] args) {
        Equation myEquation = new Equation();
        myEquation.algorithm();
        myEquation.showRes();
    }
}