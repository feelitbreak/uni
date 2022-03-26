import java.util.*;
public class Matrix {
    private final int[][] a;
    private final int[] x;
    private final int[] f;
    private final double[] x0;
    private double[] xK;
    private final double e = 0.0000001;
    private final int kMax = 5000;
    private final static int V = 4;
    Matrix(int n) {
        //Заполнение матрицы A
        Random r = new Random();
        a = new int[n][n];
        //Нижняя треугольная часть
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                a[i][j] = r.nextInt(201) - 100;
            }
        }
        //Верхняя треугольная часть
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                a[i][j] = a[j][i];
            }
        }
        //Диагональ
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                sum += Math.abs(a[i][j]);
            }
            for (int j = i + 1; j < n; j++) {
                sum += Math.abs(a[i][j]);
            }
            a[i][i] = r.nextInt(9 * V) + sum;
            sum = 0;
        }


        //Заполнение векторов x, f и x0
        x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = i + 1;
        }
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = 0;
            for (int j = 0; j < n; j++) {
                f[i] += a[i][j] * x[j];
            }
        }
        x0 = new double[n];
        for (int i = 0; i < n; i++) {
            x0[i] = 1;
        }
    }
    public int gradDescent() {
        //Метод градиентного спуска
        int q = 0;
        double[] r = new double[a.length];
        double t;
        double tNum;
        double tDen;
        double aR;
        double norm;

        xK = x0.clone();

        while (q <= kMax) {
            //Невязка
            for (int i = 0; i < a.length; i++) {
                r[i] = 0;
                for (int j = 0; j < a[i].length; j++) {
                    r[i] += a[i][j] * xK[j];
                }
                r[i] -= f[i];
            }

            //Параметр
            tNum = 0;
            tDen = 0;
            for (int i = 0; i < a.length; i++) {
                aR = 0;
                for (int j = 0; j < a[i].length; j++) {
                    aR += a[i][j] * r[j];
                }
                tNum += r[i] * r[i];
                tDen += aR * r[i];
            }
            t = tNum/tDen;

            //Подсчёт xK
            for (int i = 0; i < xK.length; i++) {
                xK[i] -= t * r[i];
            }

            q++;

            //Проверка на окончание процесса
            norm = 0;
            for (double i : r) {
                norm += Math.pow(i, 2);
            }
            norm = Math.sqrt(norm);
            if (norm < e) {
                return q;
            }
        }
        return 0;
    }
    public int sor(double w) {
        //Метод релаксации
        int q = 0;
        double p;
        xK = x0.clone();

        while(q <= kMax) {
            for (int i = 0; i < xK.length; i++) {
                xK[i] = (1 - w) * xK[i];
                p = f[i];
                for (int j = 0; j < i; j++) {
                    p -= a[i][j] * xK[j];
                }
                for (int j = i + 1; j < xK.length; j++) {
                    p -= a[i][j] * xK[j];
                }
                xK[i] += (w/a[i][i]) * p;
            }

            q++;

            //Проверка на окончание процесса
            if (getResidual() < e) {
                return q;
            }
        }
        return q;
    }
    public void outA() {
        //Вывод матрицы A
        Formatter fmt = new Formatter();
        for (int[] row : a) {
            for (int i : row) {
                fmt.format("% 4d ", i);
            }
            fmt.format("\n");
        }
        System.out.print(fmt);
    }
    public void outX() {
        //Вывод вектора x
        for (int i : x) {
            System.out.println(i);
        }
    }
    public void outF() {
        //Вывод вектора f
        for (int i : f) {
            System.out.println(i);
        }
    }
    public void outX0() {
        //Вывод вектора x0
        for (double i : x0) {
            System.out.println(i);
        }
    }
    public void outXK() {
        //Вывод вектора xK
        Formatter fmt = new Formatter();
        for (double i : xK) {
            fmt.format("%.12f\n", i);
        }
        System.out.print(fmt);
        fmt.close();
    }
    public double getResidual() {
        //Норма невязки
        double norm = 0;
        double r;
        for (int i = 0; i < a.length; i++) {
            r = 0;
            for (int j = 0; j < a[i].length; j++) {
                r += a[i][j] * xK[j];
            }
            r -= f[i];
            norm += Math.pow(r, 2);
        }
        norm = Math.sqrt(norm);
        return norm;
    }
    public double getError() {
        //Абсолютная погрешность
        double norm = 0;
        for (int i = 0; i < xK.length; i++) {
            norm += Math.pow(x[i] - xK[i], 2);
        }
        norm = Math.sqrt(norm);
        return norm;
    }
}
