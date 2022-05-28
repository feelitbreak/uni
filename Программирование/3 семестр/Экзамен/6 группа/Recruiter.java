public class Recruiter {
    private final String surname;
    private final String name;
    public Recruiter(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(surname);
        sb.append(";").append(name);
        return sb.toString();
    }
}
