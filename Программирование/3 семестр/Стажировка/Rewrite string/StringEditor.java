import java.io.*;
import java.util.*;

public class StringEditor {
    private String str;
    private Map<Character, Integer> charMap;
    StringEditor() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        str = br.readLine();
        br.close();
    }
    void countCharacters() {
        char[] chars = new char[str.length()];
        str.getChars(0, str.length(), chars, 0);
        charMap = new TreeMap<>();
        for (char c : chars) {
            if (charMap.containsKey(c)) {
                charMap.replace(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }
    }
    void editString() throws IOException {
        try {
            Integer max = charMap.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get().getValue();
            Integer min = charMap.entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .get().getValue();
            if (!Objects.equals(max, min)) {
                charMap.entrySet()
                        .stream()
                        .filter((x) -> x.getValue().equals(max))
                        .forEach((x) -> str = str.replace(x.getKey().toString(), ""));
            }
            FileWriter fw = new FileWriter("output1.txt");
            fw.write(str);
            fw.close();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
    void outMap() throws IOException {
        FileWriter fw = new FileWriter("output2.txt");
        StringBuilder sb = new StringBuilder(str.length() * 4);
        charMap.entrySet()
                .stream()
                .sorted((x, y) -> {
                    if (!Objects.equals(x.getValue(), y.getValue())) {
                        return y.getValue().compareTo(x.getValue());
                    }
                    else {
                        return x.getKey().compareTo(y.getKey());
                    }
                })
                .forEach((x) -> {
                    sb.append(x.getKey());
                    sb.append(':');
                    sb.append(x.getValue());
                    sb.append(' ');
                });
        try {
            fw.write(sb.substring(0, sb.length() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        fw.close();
    }
}
