import java.io.Serializable;
import java.util.*;

public class Driver implements Serializable {
    String surname;
    String category;
    int experience;
    String street;
    String birthDate;
    public Driver() {
        this("NoSurname", "0", 0, "NoStreet", "00.00.0000");
    }
    public Driver(String sur, String cat, int exp, String str, String birth) {
        surname = sur;
        category = cat;
        experience = exp;
        street = str;
        birthDate = birth;
    }
    public void show () {
        Formatter fmt = new Formatter();
        fmt.format("%-20s %-3s %-2d %-17s %-10s", surname, category, experience, street, birthDate);
        System.out.println(fmt);
    }
    public String getSurname() {
        return surname;
    }
    public String getCategory() {
        return category;
    }
    public int getExperience() {
        return experience;
    }
    public String getStreet() {
        return street;
    }
    public String getBirthDate() {
        return birthDate;
    }
}