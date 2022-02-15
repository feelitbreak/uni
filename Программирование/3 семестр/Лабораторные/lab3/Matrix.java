import java.util.Random;
import java.text.DecimalFormat;
import java.util.*;
public class Matrix {
    public static void generate (Double[][] a) {
        Random r = new Random();
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++) {
                a[i][j] = r.nextDouble()*50 - 25;
            }
    }

    public static void output(Double[][] a) {
        Locale locale = new Locale("en", "USA");
        DecimalFormat fmt1 = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        fmt1.setMaximumFractionDigits(16);
        DecimalFormat fmt2 = (DecimalFormat) DecimalFormat.getPercentInstance(locale);
        fmt2.setMaximumFractionDigits(16);
        DecimalFormat fmt3 = (DecimalFormat) DecimalFormat.getInstance(locale);
        fmt3.setMaximumFractionDigits(16);
        Formatter fmt = new Formatter();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (i == 0) {
                    fmt.format("%24s", fmt1.format(a[i][j]));
                } else if (i == 1) {
                    fmt.format("%24s", fmt2.format(a[i][j]));
                } else {
                    fmt.format("%24s", fmt3.format(a[i][j]));
                }
            }
            fmt.format("\n");
        }
        System.out.println(fmt);
    }

    public static void output2(Double[][] a) {
        DecimalFormat fmt1 = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        fmt1.setMaximumFractionDigits(16);
        DecimalFormat fmt2 = (DecimalFormat) DecimalFormat.getPercentInstance();
        fmt2.setMaximumFractionDigits(16);
        DecimalFormat fmt3 = (DecimalFormat) DecimalFormat.getInstance();
        fmt3.setMaximumFractionDigits(16);
        Formatter fmt = new Formatter();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a[i].length - 1; j++) {
                if (i == 0) {
                    fmt.format("%24s", fmt1.format(a[i][j]));
                } else if (i == 1) {
                    fmt.format("%24s", fmt2.format(a[i][j]));
                } else {
                    fmt.format("%24s", fmt3.format(a[i][j]));
                }
            }
            fmt.format("\n");
        }
        System.out.println(fmt);
    }
    public static void findMax(Double[][] a) {
        double max = 0;
        int iMax = 0;
        int jMax = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (Math.abs(a[i][j]) > Math.abs(max)) {
                    max = a[i][j];
                    iMax = i;
                    jMax = j;
                }
            }
        }
        Formatter fmt = new Formatter();
        fmt.format("Максимальный по модулю элемент = %f.", max);
        System.out.println(fmt);
        fmt.close();
        Double[] temp;
        for(int i = iMax; i < a.length - 1; i++) {
            temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
        }

        double temp2;
        for (int i = 0; i < a.length; i++) {
            for (int j = jMax; j < a.length - 1; j++) {
                temp2 = a[i][j];
                a[i][j] = a[i][j + 1];
                a[i][j + 1] = temp2;
            }
        }
    }
}

