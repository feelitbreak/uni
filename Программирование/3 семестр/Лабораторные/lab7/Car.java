import java.util.*;
public class Car {
    private final String name;
    private final int position;
    private final int speed;
    private final int index;
    public Car (String inStr, int index) {
        Scanner sc = new Scanner(inStr);
        sc.useDelimiter(" ");
        name = sc.next();
        position = sc.nextInt();
        speed = sc.nextInt();
        sc.close();
        this.index = index;
    }
    public double getDistance(double time) {
        return position + time * speed;
    }
    public String getName () {
        return name;
    }
    public void show() {
        Formatter fmt = new Formatter();
        fmt.format("%s %d %d", name, position, speed);
        System.out.println(fmt);
        fmt.close();
    }
    public int getIndex() {
        return index;
    }
}
