import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.*;

public class Employee {
    private final String surname;
    private final String name;
    private final Date startDate;
    private final Date endDate;
    private final int vacation;
    private final int compensatory;
    public Employee(String surname, String name, Date startDate, Date endDate, int vacation, int compensatory) {
        this.surname = surname;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vacation = vacation;
        this.compensatory = compensatory;
    }
    public int getVacation() {
        return vacation;
    }
    public String getSurname() {
        return surname;
    }
    public long getDaysDiff() {
        long diffTime = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
    }
    public int getMonthsDiff() {
        /* Calendar end = new GregorianCalendar();
        Calendar today = new GregorianCalendar();
        end.setTime(endDate);
        today.setTime(new Date());
        long yearsDiff = end.get(Calendar.YEAR) - today.get(Calendar.YEAR);
        long monthsDiff = end.get(Calendar.MONTH) - today.get(Calendar.MONTH) - 1;
        monthsDiff += yearsDiff * 12;
        if(today.get(Calendar.DAY_OF_MONTH) <= end.get(Calendar.DAY_OF_MONTH)) {
            monthsDiff++;
        }
        return monthsDiff; */
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        Period age = Period.between(today, end);
        return age.getMonths() + age.getYears() * 12;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(surname);
        sb.append(";").append(name);
        return sb.toString();
    }
}
