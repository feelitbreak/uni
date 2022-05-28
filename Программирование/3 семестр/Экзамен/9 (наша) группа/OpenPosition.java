import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OpenPosition {
    private final int projId;
    private final int posId;
    private final Date openDate;
    public OpenPosition(String data) throws ParseException {
        String[] split = data.split(";");
        projId = Integer.parseInt(split[0]);
        posId = Integer.parseInt(split[1]);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        openDate = sdf.parse(split[2]);
    }

    public int getProjId() {
        return projId;
    }
    public boolean isInInterval(Date date1, Date date2) {
        return (openDate.after(date1) || openDate.equals(date1)) && (openDate.before(date2) || openDate.equals(date2));
    }
}
