import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.SimpleFormatter;

public class Text {
    private String text;
    private final List<Sentence> sentList;
    private final Map<String, Sentence> sentMap;
    public Text() {
        sentList = new ArrayList<>();
        sentMap = new TreeMap<>();
    }
    public void read(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        text = br.readLine();
        br.close();
    }
    public void createList() {
        String[] sentences = text.split("[?!]+");
        for (String i : sentences) {
            sentList.add(new Sentence(i));
        }
    }
    public void findBigInt(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (Sentence st : sentList) {
            for (String str : st.getLeks()) {
                try {
                    long a = Long.parseLong(str);
                } catch (NumberFormatException e) {
                    try {
                        BigInteger b = new BigInteger(str);
                        fw.write(str);
                        fw.write('\n');
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        fw.close();
    }
    public void createMap(String fileName) throws IOException {
        for (Sentence st : sentList) {
            sentMap.put(st.getFirstLeks(), st);
        }
        FileWriter fw = new FileWriter(fileName);
        sentMap.forEach((x, y) -> {
            try {
                fw.write(x);
                fw.write(" = ");
                fw.write(y.toString());
                fw.write('\n');
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        fw.close();
    }
    public void sort(String fileName) throws IOException {
        sentList.sort(Sentence::compareTo);
        FileWriter fw = new FileWriter(fileName);
        for (Sentence st : sentList) {
            fw.write(st.toString());
            fw.write('\n');
        }
        fw.close();
    }
    public void findMathProblems(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (Sentence st : sentList) {
            for (String str : st.getLeks()) {
                if (str.matches("[a-z]=\\d+[-+\\\\/%*]\\d+")) {
                    fw.write(str);
                    fw.write('\n');
                }
            }
        }
        fw.close();
    }
    public void outBin(String fileName) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(sentList);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        List<Sentence> sent = (List<Sentence>) ois.readObject();
        System.out.println("Проверка вывода в бинарный файл:");
        sent.forEach(System.out::println);
    }
    public void findDates() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yy");
        for (Sentence st : sentList) {
            for (String str : st.getLeks()) {
                try {
                    Date d = sdf1.parse(str);
                    System.out.println(sdf2.format(d));
                } catch(ParseException ignored) {}
            }
        }
    }
}
