public class Employee {
    private final String surname;
    private final String name;
    private final String skill;
    public Employee(String surname, String name, String skill) {
        this.surname = surname;
        this.name = name;
        this.skill = skill;
    }
    public String getSurname() {
        return surname;
    }
    public String getSkill() {
        return skill;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(surname);
        sb.append(";").append(name);
        return sb.toString();
    }
}
