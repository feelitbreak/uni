import java.util.*;
import java.io.*;
public class HashClass {
    private static final int MAX_TIME = 10;
    private int k;
    private final List<Car> cars;
    private final Hashtable<TimeClass, List<Car>> carHash;
    public HashClass() {
        cars = new ArrayList<>();
        carHash = new Hashtable<>();
    }
    public void inputCars(String inFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        k = Integer.parseInt(br.readLine());
        Car car;
        int i = 0;
        while(br.ready()) {
            car = new Car(br.readLine(), i);
            cars.add(car);
            i++;
        }
    }
    public void findOvertakes() {
        cars.sort((a, b) -> Double.compare(a.getDistance(0.), b.getDistance(0.)));
        ListIterator<Car> it1;
        ListIterator<Car> it2;
        List<Car> overtakers;
        List<Car> overtakes;
        Car c;
        for(double t = 0.1; (t < MAX_TIME) && (carHash.size() < k); t += 0.1) {
            overtakers = new ArrayList<>();
            overtakes = new ArrayList<>();
            it1 = cars.listIterator();
            while(it1.hasNext()) {
                c = it1.next();
                it2 = cars.listIterator(it1.nextIndex());
                while(it2.hasNext()) {
                    if(c.getDistance(t) > it2.next().getDistance(t)) {
                        if (!overtakers.contains(c)) {
                            overtakers.add(c);
                        }
                        if (!overtakes.contains(cars.get(it2.nextIndex() - 1))) {
                            overtakes.add(cars.get(it2.nextIndex() - 1));
                        }
                    }
                }
            }
            if(!overtakers.isEmpty()) {
                carHash.put(new TimeClass(t, overtakers), overtakes);
            }
            double finalT = t;
            cars.sort((a, b) -> Double.compare(a.getDistance(finalT), b.getDistance(finalT)));
        }
    }
    public void showCars() {
        cars.forEach(Car::show);
    }
    public void showHashtable() {
        Formatter fmt = new Formatter();
        carHash.forEach((x, y) -> {
            fmt.format("%.1f - ", x.getTime());
            x.getList().forEach((a) -> fmt.format("%s ", a.getName()));
            fmt.format("- ");
            y.forEach((a) -> fmt.format("%s ", a.getName()));
            fmt.format("- %d\n", x.hashCode());
        });
        System.out.print(fmt);
    }
}
