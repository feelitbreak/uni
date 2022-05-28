public class Request {
    private final int id;
    private final int empId;
    private final int daysId;
    private final int days;
    public Request(String data) {
        String[] split = data.split(";");
        id = Integer.parseInt(split[0]);
        empId = Integer.parseInt(split[1]);
        daysId = Integer.parseInt(split[2]);
        days = Integer.parseInt(split[3]);
    }
    public int getEmpId() {
        return empId;
    }
    public int getDays() {
        return days;
    }
    public int getDaysId() {
        return daysId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(";").append(empId);
        sb.append(";").append(daysId);
        sb.append(";").append(days);
        return sb.toString();
    }
}
