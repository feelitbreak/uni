import java.util.*;

class Gauss {
    public static double[] solve(double[][] a, double[] f) throws NumberFormatException {
        //Метод Гаусса
        double max;
        int kMax;
        double[] temp;
        double temp2;

        //Прямой ход
        for (int i = 0; i < a.length; i++) {
            //Поиск максимального по модулю элемента (выбор главного элемента по столбцу)
            kMax = i;
            max = 0;
            for (int k = i; k < a.length; k++) {
                if (Math.abs(a[k][i]) > Math.abs(max)) {
                    max = a[k][i];
                    kMax = k;
                }
            }
            //Проверка на невозможность применения
            if (max == Double.MIN_VALUE) {
                System.out.println("Ошибка. Деление на ноль.");
                throw new NumberFormatException();
            }
            //Перестановка строк
            if (max != a[i][i]) {
                temp = a[i];
                a[i] = a[kMax];
                a[kMax] = temp;
                temp2 = f[i];
                f[i] = f[kMax];
                f[kMax] = temp2;
            }

            //Деление на главный элемент
            for (int j = i + 1; j < a.length; j++) {
                a[i][j] /= a[i][i];
            }
            f[i] /= a[i][i];

            //Обнуление столбца
            for (int j = i + 1; j < a.length; j++) {
                for (int k = i + 1; k < a.length; k++) {
                    a[j][k] -= a[j][i] * a[i][k];
                }
                f[j] -= a[j][i] * f[i];
            }
        }

        //Обратный ход
        double[] x = new double[a.length];
        x[a.length - 1] = f[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            x[i] = f[i];
            for (int j = a.length - 1; j > i; j--) {
                x[i] -= a[i][j] * x[j];
            }
        }

        return x;
    }
}

class F {
    public static double getValue(double x) {
        return 1 + x;
    }
}

class K {
    public static double getValue(double x, double s) {
        return 1 / (x + s + 1);
    }
}

class IntegralApprox {
    public final static int N = 10;
    private final double a;
    private final double[] akLRS;
    private final double[] akRRS;
    private final double[] xk;
    private final double[] fk;
    private final double h;

    public IntegralApprox(double a, double b) {
        this.a = a;
        this.akLRS = new double[N + 1];
        this.akRRS = new double[N + 1];
        this.xk = new double[N + 1];
        this.fk = new double[N + 1];

        this.h = (b - this.a) / N;
        this.setXk();
        this.setFk();
        this.setAkRRS();
        this.setAkLRS();
    }

    public double[] getAkLRS() {
        return akLRS;
    }

    public double[] getAkRRS() {
        return akRRS;
    }

    public double[] getXk() {
        return xk;
    }

    public double[] getFk() {
        return fk;
    }

    private void setXk() {
        for (int i = 0; i < N + 1; i++) {
            this.xk[i] = this.a + i * this.h;
        }
    }

    private void setAkLRS() {
        for (int i = 0; i < N; i++) {
            this.akLRS[i] = this.h;
        }

        this.akLRS[N] = 0.;
    }

    private void setAkRRS() {
        this.akRRS[0] = 0.;

        for (int i = 1; i < N + 1; i++) {
            this.akRRS[i] = this.h;
        }
    }

    private void setFk() {
        for (int i = 0; i < N + 1; i++) {
            this.fk[i] = F.getValue(this.xk[i]);
        }
    }
}

class MMQ {
    private final int iN;
    private final double lambda;
    private final double[] akFred;
    private final double[] akVolt;
    private final double[] xk;
    private final double[] fk;
    private final double[] ykFred;
    private final double[] ykVolt;

    public MMQ(IntegralApprox ia, double lambda) {
        this.akFred = ia.getAkRRS();
        this.akVolt = ia.getAkLRS();
        this.xk = ia.getXk();
        this.fk = ia.getFk();
        this.iN = IntegralApprox.N;

        this.lambda = lambda;

        this.ykFred = new double[this.iN + 1];
        this.ykVolt = new double[this.iN + 1];
    }

    public void Fredholm() {
        double[][] systemMatrix;
        systemMatrix = new double[this.iN][this.iN];
        this.setSystemMatrix(systemMatrix);

        double[] fkCopy = new double[this.iN];
        System.arraycopy(fk, 1, fkCopy, 0, fkCopy.length);

        double[] yk1 = Gauss.solve(systemMatrix, fkCopy);
        System.arraycopy(yk1, 0, this.ykFred, 1, yk1.length);
        this.ykFred[0] = this.getYk0Fred();
    }

    public void Volterra() {
        for (int i = 0; i < this.iN + 1; i++) {
            for (int k = 0; k < i; k++) {
                this.ykVolt[i] += this.akVolt[k] * K.getValue(this.xk[i], this.xk[k]) * this.ykVolt[k];
            }

            this.ykVolt[i] *= this.lambda;
            this.ykVolt[i] += this.fk[i];

            if (i != this.iN) {
                this.ykVolt[i] /= 1. - (this.lambda * this.akVolt[i] * K.getValue(this.xk[i], this.xk[i]));
            }
        }
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое решение yk для уравнения Фредгольма:\n");
        for (int i = 0; i < this.iN + 1; i++) {
            fmt.format("%.7f\n", this.ykFred[i]);
        }
        fmt.format("Полученное приближённое решение yk для уравнения Вольтерра:\n");
        for (int i = 0; i < this.iN + 1; i++) {
            fmt.format("%.7f\n", this.ykVolt[i]);
        }
        System.out.println(fmt);
    }

    private void setSystemMatrix(double[][] matrix) {
        for (int i = 0; i < this.iN; i++) {
            for (int j = 0; j < this.iN; j++) {
                matrix[i][j] = - this.lambda * this.akFred[j + 1] * K.getValue(this.xk[i + 1], this.xk[j + 1]);
                if (i == j) {
                    matrix[i][j]++;
                }
            }
        }
    }

    private double getYk0Fred() {
        double res = 0;
        for (int i = 1; i < this.iN + 1; i++) {
            res += this.akFred[i] * K.getValue(this.xk[0], xk[i]) * this.ykFred[i];
        }

        res *= this.lambda;
        res += this.fk[0];
        return res;
    }
}

class FPI {
    private final static int N = 5;
    private final int iN;
    private final double lambda;
    private final double[] akFred;
    private final double[] akVolt;
    private final double[] xk;
    private final double[] fk;
    private double[] ykFred;
    private double[] ykVolt;

    public FPI(IntegralApprox ia, double lambda) {
        this.akFred = ia.getAkRRS();
        this.akVolt = ia.getAkLRS();
        this.xk = ia.getXk();
        this.fk = ia.getFk();
        this.iN = IntegralApprox.N;

        this.lambda = lambda;
    }

    public void Fredholm() {
        double[] y1 = new double[this.iN + 1];
        double[] y2 = new double[this.iN + 1];

        System.arraycopy(fk, 0, y1, 0, fk.length);

        for (int i = 0; i < N; i++) {
            this.calcY2Fred(y1, y2);
            System.arraycopy(y2, 0, y1, 0, y2.length);
        }

        ykFred = y2;
    }

    public void Volterra() {

    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое решение yk для уравнения Фредгольма:\n");
        for (int i = 0; i < this.iN + 1; i++) {
            fmt.format("%.7f\n", this.ykFred[i]);
        }
        System.out.println(fmt);
    }

    private void calcY2Fred(double[] y1, double[] y2) {
        for (int j = 0; j < this.iN + 1; j++) {
            y2[j] = 0.;
            for (int k = 1; k < this.iN + 1; k++) {
                y2[j] += this.akFred[k] * K.getValue(this.xk[j], this.xk[k]) * y1[k];
            }

            y2[j] *= this.lambda;
            y2[j] += fk[j];
        }
    }
}

public class Main {
    private final static double A = 0.;
    private final static double B = 1.;
    private final static double LAMBDA = 1.;

    public static void main(String[] args) {
        IntegralApprox ia = new IntegralApprox(A, B);

        System.out.println("Задание 1:");

        MMQ mmq = new MMQ(ia, LAMBDA);
        mmq.Fredholm();
        mmq.Volterra();
        mmq.outRes();

        System.out.println("Задание 2:");

        FPI fpi = new FPI(ia, LAMBDA);
        fpi.Fredholm();
        fpi.Volterra();
        fpi.outRes();
    }
}
