public class Tester {
    private final Integer id;
    private final String surname;
    private final String name;
    public Tester(String data) {
        String[] strings = data.split(";");
        id = Integer.parseInt(strings[0]);
        surname = strings[1];
        name = strings[2];
    }
}
