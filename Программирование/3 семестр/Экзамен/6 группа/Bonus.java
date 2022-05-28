public class Bonus {
    private final Priority prior;
    private final long days;
    private final int bonus;
    public Bonus(String data) {
        String[] split = data.split(";");
        prior = Priority.valueOf(split[0]);
        days = Long.parseLong(split[1]);
        bonus = Integer.parseInt(split[2]);
    }
    public Priority getPrior() {
        return prior;
    }
    public long getDays() {
        return days;
    }
    public int getBonus() {
        return bonus;
    }
}
