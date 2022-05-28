import java.text.*;
import java.util.*;

public class Bug implements Comparable<Bug> {
    private final int page;
    private final String title;
    private final int priority;
    private final Date date;

    public Bug(String page, String title, String priority, String date) throws ParseException {
        this.page = Integer.parseInt(page);
        this.title = title;
        this.priority = Integer.parseInt(priority);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.date = sdf.parse(date);
    }

    public String getTitle() {
        return title;
    }
    public Integer getPriority() {
        return priority;
    }
    public Date getDate() {
        return date;
    }
    @Override
    public int compareTo(Bug o) {
        return o.getDate().compareTo(date);
    }
}
