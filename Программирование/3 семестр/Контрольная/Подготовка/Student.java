public class Student implements Comparable<Student> {
    private final String surname;
    private final String name;
    private final String patronymic;
    private final String mark;
    private final Long id;
    private final String photo;
    public Student(String surname, String name, String patronymic, String mark, Long id, String photo) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.mark = mark;
        this.id = id;
        this.photo = photo;
    }
    //Конструктор, разделяющий строку на нужные лексемы
    public Student(String str) {
        String[] strings = str.split("[ _;]");
        surname = strings[0];
        name = strings[1];
        patronymic = strings[2];
        mark = strings[3];
        id = Long.parseLong(strings[4]);
        photo = strings[5];
    }
    public String getMark() {
        return mark;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public Long getId() {
        return id;
    }
    public String getPhoto() {
        return photo;
    }
    //Перегруженный toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(72);
        sb.append(surname);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(patronymic);
        sb.append(" ");
        sb.append(mark);
        sb.append(" ");
        sb.append(id);
        sb.append(" ");
        sb.append(photo);
        return sb.toString();
    }
    //Перегруженный compareTo
    @Override
    public int compareTo(Student st) {
        int a = surname.compareTo(st.surname);
        if (a != 0) {
            return a;
        } else {
            return Double.compare(Double.parseDouble(mark), Double.parseDouble(st.mark));
        }
    }
}
