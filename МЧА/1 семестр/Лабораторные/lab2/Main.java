public class Main {

    public static void main(String[] args) {
        Equation myEquation = new Equation();
        System.out.println("Таблица 1:");
        myEquation.methods();
        System.out.print("Метод Ньютона, ||f(xn)||: ");
        myEquation.outNwt();
        System.out.print("Метод секущих, ||f(xn)||: ");
        myEquation.outSec();
        System.out.println("Таблица 2:");
        myEquation.gaussSeidel();
    }
}
