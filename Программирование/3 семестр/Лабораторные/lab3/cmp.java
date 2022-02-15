import java.util.Comparator;
public class cmp implements Comparator {
    public int compare(Object obj1, Object obj2) {
        if (obj1 instanceof Double && obj2 instanceof Double) {
            double o1 = (double)obj1;
            double o2 = (double)obj2;
            if (o1 < o2)return 1;
            if (o1 > o2)return -1;
            else return 0;
        }
        return -1;
    }
}