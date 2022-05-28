import java.io.*;
import java.util.*;

public class FileProcessor {
    private String text;
    private final Map<Integer, String> fragments;
    private List<String> remFragments;
    public FileProcessor(String fileName, String fileWithFragName) throws IOException {
        FileInputStream fr = new FileInputStream(fileName);
        text = new String(fr.readAllBytes());
        fr.close();
        Scanner sc = new Scanner(new File(fileWithFragName));
        fragments = new TreeMap<>();
        sc.useDelimiter("[\n\r\\[\\]]+");
        while(sc.hasNext()) {
            Integer a = sc.nextInt();
            String s = sc.next();
            fragments.put(a, s);
        }
        sc.close();
    }
    public void processText() {
        remFragments = new ArrayList<>();
        fragments.forEach((x, y) -> {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append(x);
            sb.append(']');
            String i = sb.toString();
            if(text.contains(i)) {
                text = text.replace(i, y);
            } else {
                remFragments.add(i + y);
            }
        });
    }
    public void outText(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(text);
        fw.close();
    }
    public void outFragments(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        Iterator<String> it = remFragments.iterator();
        while (it.hasNext()) {
            fw.write(it.next());
            if (it.hasNext()) {
                fw.write('\n');
            }
        }
        fw.close();
    }
    public void outNumbers(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        StringBuilder sb = new StringBuilder();
        fragments.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach((x) -> {
                    sb.append(x.getKey());
                    sb.append("\n");
                });
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        fw.write(sb.toString());
        fw.close();
    }
}
