import java.io.Serializable;
import java.util.*;

public class Sentence implements Comparable<Sentence>, Serializable {
    private final List<String> leks;
    public Sentence() {
        leks = new ArrayList<>();
    }
    public Sentence(String sentence) {
        leks = new ArrayList<>();
        String[] strings = sentence.split("[, ;_]+");
        Collections.addAll(leks, strings);
    }

    public String getFirstLeks() {
        return leks.get(0);
    }
    public List<String> getLeks() {
        return Collections.unmodifiableList(leks);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String i : leks) {
            sb.append(i);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    @Override
    public int compareTo(Sentence st) {
        return leks.get(0).compareTo(st.leks.get(0));
    }
}
