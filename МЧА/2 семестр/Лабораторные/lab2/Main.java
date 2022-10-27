import java.util.Formatter;

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

class MMQFredholm {
    private final static int N = 10;
    private final double a;
    private final double b;
    private final double lambda;
    private final double[] ak;
    private final double[] xk;
    private final double[] fk;
    private final double[] yk;

    public MMQFredholm(double a, double b, double lambda) {
        this.a = a;
        this.b = b;
        this.lambda = lambda;
        ak = new double[N + 1];
        xk = new double[N + 1];
        fk = new double[N + 1];
        yk = new double[N + 1];
    }

    public void LRS() {
        double h = (this.b - this.a) / N;
        this.setXk(h);
        this.setAk(h);
        this.setFk();

        double[][] systemMatrix;
        systemMatrix = new double[N][N];
        this.setSystemMatrix(systemMatrix);

        double[] yk1 = Gauss.solve(systemMatrix, fk);
        System.arraycopy(yk1, 0, this.yk, 0, yk1.length);
        this.yk[N] = this.getYkN();
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое решение yk:\n");
        for (int i = 0; i < N + 1; i++) {
            fmt.format("%.7f\n", this.yk[i]);
        }
        System.out.println(fmt);
    }

    private void setXk(double h) {
        for (int i = 0; i < N + 1; i++) {
            xk[i] = a + i * h;
        }
    }

    private void setAk(double h) {
        for (int i = 0; i < N; i++) {
            ak[i] = h;
        }

        ak[N] = 0.;
    }

    private void setFk() {
        for (int i = 0; i < N + 1; i++) {
            fk[i] = F.getValue(xk[i]);
        }
    }

    private void setSystemMatrix(double[][] matrix) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = - lambda * ak[j] * K.getValue(xk[i], xk[j]);
                if (i == j) {
                    matrix[i][j]++;
                }
            }
        }
    }

    private double getYkN() {
        double res = 0;
        for (int i = 0; i < N; i++) {
            res += ak[i] * K.getValue(xk[N], xk[i]) * yk[i];
        }

        res *= lambda;
        res += fk[N];
        return res;
    }
}

public class Main {
    private final static double A = 0.;
    private final static double B = 1.;
    private final static double LAMBDA = 1.;

    public static void main(String[] args) {
        System.out.println("Задание 1:");

        MMQFredholm mmqFred = new MMQFredholm(A, B, LAMBDA);
        mmqFred.LRS();
        mmqFred.outRes();
    }
}
