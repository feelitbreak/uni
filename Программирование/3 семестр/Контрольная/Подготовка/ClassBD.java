import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

interface InterfBD {
    void search(String fileName) throws IOException;
}
public class ClassBD implements InterfBD {
    private final List<Student> studList;
    private final Map<Long, Student> studMap;
    public ClassBD() {
        studList = new ArrayList<>();
        studMap = new TreeMap<>();
    }
    //Ввод в List
    public void inputList(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        while(br.ready()) {
            str = br.readLine();
            studList.add(new Student(str));
        }
        br.close();
    }
    //Ввод в Map
    public void inputMap(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        while(br.ready()) {
            str = br.readLine();
            Student st = new Student(str);
            studMap.put(st.getId(), st);
        }
        br.close();
    }
    /* public void fixMark() {
        for (Student st : studList) {
            String mark = st.getMark();
            st.setMark(mark.replaceAll("[!/\\\\\"№%:?*()+]", ""));
        }
        studMap.forEach((x,y) -> {
            String mark = y.getMark();
            y.setMark(mark.replaceAll("[!/\\\\\"№%:?*()+]", ""));
        });
    } */
    //Удаление некорректных данных в среднем балле
    public void checkMark() {
        Predicate<Student> pred = x -> !x.getMark().matches("[^!/\\\\\"№%:?*()+]*");
        studList.removeIf(pred);
        studMap.values().removeIf(pred);
    }
    /* public void fixName() {
        for (Student st : studList) {
            String sur = st.getSurname();
            st.setSurname(sur.replaceAll("[\\d]+", ""));
            String name = st.getName();
            st.setName(name.replaceAll("[\\d]+", ""));
        }
        studMap.forEach((x, y) -> {
            String sur = y.getSurname();
            y.setSurname(sur.replaceAll("[\\d]+", ""));
            String name = y.getName();
            y.setName(name.replaceAll("[\\d]+", ""));
        });
    } */
    //Удаление некорректных данных в фамилии
    public void checkSurname() {
        Predicate<Student> pred = x -> !x.getSurname().matches("[^\\d]*");
        studList.removeIf(pred);
        studMap.values().removeIf(pred);
    }
    //Поиск студентов с определённым разрешением фото
    @Override
    public void search(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        studList.forEach((x) -> {
            try {
                String photo = x.getPhoto();
                if (photo.matches(".+\\.(bmp|gif|jpg)")) {
                    fw.write(x.toString());
                    fw.write("\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        fw.close();
    }
    //Сортировка List по фамилии и среднему баллу
    public void sort() {
        studList.sort(Student::compareTo);
    }
    //Вывод List в файл
    public void outList(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (Student st : studList) {
            fw.write(st.toString());
            fw.write("\n");
        }
        fw.close();
    }
    //Вывод Map в файл
    public void outMap(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        studMap.forEach((x, y) -> {
            try {
                fw.write(x.toString());
                fw.write(" = ");
                fw.write(y.toString());
                fw.write("\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        fw.close();
    }
    //Вывод List в формате XML
    public void outXML(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        XMLEncoder xe = new XMLEncoder(fos);
        xe.setPersistenceDelegate(Student.class,
                new DefaultPersistenceDelegate(
                        new String[]{
                                "surname",
                                "name",
                                "patronymic",
                                "mark",
                                "id",
                                "photo"
                        }) );
        xe.writeObject(studList);
        xe.close();
        fos.close();
    }
}
