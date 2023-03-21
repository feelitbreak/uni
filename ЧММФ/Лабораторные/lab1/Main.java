class F {
    public static double getValue(double x) {
        return Math.pow(Math.sin(x), 2.);
    }
}

class K {
    public static double getValue(double x) {
        return Math.pow(Math.cos(x), 2.) + 1.;
    }
}

class Q {
    public static double getValue() {
        return 1.;
    }
}

class DiffTridiagMatrixAlgorithm {
    private final double n;
    private final double[] a;
    private final double[] c;
    private final double[] b;
    private final double[] f;
    private final double kap1;
    private final double nu1;
    private final double kap2;
    private final double nu2;

    public DiffTridiagMatrixAlgorithm
            (double n, double[] a, double[] c, double[] b, double[] f,
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
}

class DiffOperatorsMethod {

}

class BoundaryValueProblem {
    private static final double KAP0 = 1.;
    private static final double G0 = 0;
    private static final double KAP1 = 1.;
    private static final double G1 = 1.;
    private double[] y1;
}

public class Main {

    public static void main(String[] args) {
        BoundaryValueProblem bvp = new BoundaryValueProblem();
    }
}
