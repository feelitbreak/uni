import java.text.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateClass {
    private final List<Calendar> calendars;
    private static final int MAX_HOUR = 24;
    private static final int MAX_MIN = 60;
    private static final int MAX_SEC = 60;
    private static final int MAX_MILSEC = 999;
    private static final int NEXT_MILSEC = 500;
    private static final int NEXT_HOUR = 10;
    private static final String dateInFormat = "dd.MM.yyyy G";
    private static final String dateOutFormat = "G M F HH mm";
    DateClass() {
        calendars = new ArrayList<>();
    }
    public void getValidDates (String inFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        String line;
        SimpleDateFormat sdf = new SimpleDateFormat(dateInFormat, Locale.ENGLISH);
        sdf.setLenient(false);
        Date date;
        Calendar cal;
        while((line = br.readLine()) != null) {
            try {
                date = sdf.parse(line);
                cal = Calendar.getInstance();
                cal.setTime(date);
                calendars.add(cal);
            } catch (ParseException ignored) {}
        }
        br.close();
    }
    public void outputDates() {
        calendars.forEach(x -> System.out.println(x.getTime()));
    }
    public void setTime() {
        Random r = new Random();
        for(Calendar cal : calendars) {
            cal.set(Calendar.HOUR, r.nextInt(MAX_HOUR));
            cal.set(Calendar.MINUTE, r.nextInt(MAX_MIN));
            cal.set(Calendar.SECOND, r.nextInt(MAX_SEC));
            cal.add(Calendar.MILLISECOND, r.nextInt(MAX_MILSEC) + NEXT_MILSEC);
            cal.roll(Calendar.HOUR, r.nextInt(MAX_HOUR) + NEXT_HOUR);
        }
    }
    public void outputSDF() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateOutFormat, Locale.ENGLISH);
        System.out.println("Эра Мес Нед Час Мин");
        calendars.forEach(x -> System.out.println(sdf.format(x.getTime())));
    }
    public void outputFormatter() {
        Formatter fmt = new Formatter(Locale.ENGLISH);
        calendars.forEach(x -> fmt.format("%tB %tc %tL\n", x, x, x));
        System.out.print(fmt);
        fmt.close();
    }
}
