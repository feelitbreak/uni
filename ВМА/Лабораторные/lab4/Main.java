public class Main {
    private static final int N = 10;
    public static void main(String[] args) {
        Matrix myMatrix = new Matrix(N);
        System.out.println("Матрица A:");
        myMatrix.showA();
        System.out.println("Задание 1).");
        int q;
        q = myMatrix.powerIt();
        System.out.println("Начальный вектор y0:");
        myMatrix.showY0();
        System.out.println("Номер итерации q:");
        if (q != 0) {
            System.out.println(q);
        } else {
            System.out.println("Итерационный процесс расходится или было достигнуто максимальное количество итераций.");
        }
        System.out.println("Полученное наибольшее по модулю собственное значение λ1:");
        myMatrix.showLambda1();
        System.out.println("Соответствующий ему нормированный собственный вектор x1:");
        myMatrix.showX1();
        System.out.println("Вектор Au(q) - λ1u(q):");
        myMatrix.showAul();
        System.out.println("Евклидова норма ||Au(q) - λ1u(q)||:");
        myMatrix.showEuAul();
        System.out.println("Задание 2).");
        q = myMatrix.rotateIt();
        System.out.println("Номер итерации q:");
        if (q != 0) {
            System.out.println(q);
        } else {
            System.out.println("Итерационный процесс расходится.");
        }
        System.out.println("Полученные приближённые собственные значения:");
        myMatrix.showLambda();
        System.out.println("Соответствующие им собственные векторы:");
        myMatrix.showT();
        System.out.println("Векторы r = Ax - λx:");
        myMatrix.showResiduals();
    }
}