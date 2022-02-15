import java.util.*;
public class TimeClass {
    private final double t;
    private final List<Car> carList;
    public TimeClass(double t, List<Car> carList) {
        this.t = t;
        this.carList = carList;
    }
    public double getTime() {
        return t;
    }
    public List<Car> getList() {
        return carList;
    }
    @Override
    public int hashCode() {
        return (int) (t * 6) + carList.get(0).getIndex() * 5;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this)  {
            return true;
        }
        if(!(obj instanceof TimeClass)) {
            return false;
        }
        TimeClass objCar = (TimeClass) obj;
        return t == objCar.t;
    }
}
