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

class MMQ {
    private final static int N = 10;
    private final double a;
    private final double lambda;
    private final double[] akFred;
    private final double[] akVolt;
    private final double[] xk;
    private final double[] fk;
    private final double[] ykFred;
    private double[] ykVolt;
    private final double h;

    public MMQ(double a, double b, double lambda) {
        this.a = a;
        this.lambda = lambda;
        this.akFred = new double[N + 1];
        this.akVolt = new double[N + 1];
        this.xk = new double[N + 1];
        this.fk = new double[N + 1];
        this.ykFred = new double[N + 1];

        this.h = (b - this.a) / N;
        this.setXk();
        this.setFk();
    }

    public void LRSFredholm() {
        this.setAkFred();

        double[][] systemMatrix;
        systemMatrix = new double[N][N];
        this.setSystemMatrix(systemMatrix);

        double[] fkCopy = Arrays.copyOf(fk, fk.length);

        double[] yk1 = Gauss.solve(systemMatrix, fkCopy);
        System.arraycopy(yk1, 0, this.ykFred, 0, yk1.length);
        this.ykFred[N] = this.getYkNFred();
    }

    public void RRSVolterra() {
        this.setAkVolt();
        this.ykVolt = this.getYkVolt();
    }

    public void outRes() {
        Formatter fmt = new Formatter();
        fmt.format("Полученное приближённое решение yk для уравнения Фредгольма:\n");
        for (int i = 0; i < N + 1; i++) {
            fmt.format("%.7f\n", this.ykFred[i]);
        }
        fmt.format("Полученное приближённое решение yk для уравнения Вольтерра:\n");
        for (int i = 0; i < N + 1; i++) {
            fmt.format("%.7f\n", this.ykVolt[i]);
        }
        System.out.println(fmt);
    }

    private void setXk() {
        for (int i = 0; i < N + 1; i++) {
            this.xk[i] = this.a + i * this.h;
        }
    }

    private void setAkFred() {
        for (int i = 0; i < N; i++) {
            this.akFred[i] = this.h;
        }

        this.akFred[N] = 0.;
    }

    private void setAkVolt() {
        this.akVolt[0] = 0.;

        for (int i = 1; i < N + 1; i++) {
            this.akVolt[i] = this.h;
        }
    }

    private void setFk() {
        for (int i = 0; i < N + 1; i++) {
            this.fk[i] = F.getValue(this.xk[i]);
        }
    }

    private void setSystemMatrix(double[][] matrix) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = - this.lambda * this.akFred[j] * K.getValue(this.xk[i], this.xk[j]);
                if (i == j) {
                    matrix[i][j]++;
                }
            }
        }
    }

    private double getYkNFred() {
        double res = 0;
        for (int i = 0; i < N; i++) {
            res += this.akFred[i] * K.getValue(this.xk[N], xk[i]) * this.ykFred[i];
        }

        res *= this.lambda;
        res += this.fk[N];
        return res;
    }

    private double[] getYkVolt() {
        double[] res = new double[N + 1];

        res[0] = this.fk[0];

        for (int i = 1; i < N + 1; i++) {
            for (int k = 1; k < i; k++) {
                res[i] += this.akVolt[k] * K.getValue(this.xk[i], this.xk[k]) * res[k];
            }

            res[i] *= this.lambda;
            res[i] += this.fk[i];
            res[i] /= 1. - (this.lambda * this.akVolt[i] * K.getValue(this.xk[i], this.xk[i]));
        }

        return res;
    }
}

public class Main {
    private final static double A = 0.;
    private final static double B = 1.;
    private final static double LAMBDA = 1.;

    public static void main(String[] args) {
        System.out.println("Задание 1:");

        MMQ mmqFred = new MMQ(A, B, LAMBDA);
        mmqFred.LRSFredholm();
        mmqFred.RRSVolterra();
        mmqFred.outRes();
    }
}
