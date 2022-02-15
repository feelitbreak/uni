import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class FileClass {
    private static final Pattern pattern1 = Pattern.compile
            ("(АВ|ВМ|НВ|КН|МР|МС|КВ|PP|SP|DP)\\d{7} ((РУВД (?!(Минск)|(Брест)|(Витебск)|(Могилев)|(Гомель)|(Гродно))[А-Я][а-я]+)|([А-Я][а-я]+ РУВД ((Минск)|(Брест)|(Витебск)|(Могилев)|(Гомель)|(Гродно))))");
    private static final Pattern pattern2 = Pattern.compile
            ("<([\\w/_]+)([ \\w\"=]*)>(\\s*([^<\\s]*))");
    private static final String pattern3 = "[\\d]{2}([.:\\-])[\\d]{2}\\1[\\d]{4}";
    public static List<String> input(String filePath) throws IOException {
        List<String> res = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = br.readLine()) != null) {
            res.add(line);
        }
        br.close();
        return res;
    }
    public static void findPatterned(List<String> list) {
        Matcher m;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            m = pattern1.matcher(it.next());
            if (!m.matches()) {
                it.remove();
            }
        }
    }
    public static void output(List<String> list, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (String s : list) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
    }
    public static void findInHtml(String inFile, String outFile) throws IOException {
        List<String> tags = new ArrayList<>();
        List<String> attr = new ArrayList<>();
        List<String> text = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        String fileText;
        fileText = sb.toString();
        Matcher m = pattern2.matcher(fileText);
        while(m.find()) {
            tags.add(m.group(1));
            if (!m.group(2).equals("")) {
                attr.add(m.group(2));
            }
            if (!m.group(4).equals("")) {
                text.add(m.group(4));
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
        bw.write("Теги:\n");
        for (String s : tags) {
            bw.write(s);
            bw.newLine();
        }
        bw.write("Аттрибуты:\n");
        for (String s : attr) {
            bw.write(s);
            bw.newLine();
        }
        bw.write("Строки между тегами:\n");
        for (String s : text) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
    }
    public static void replaceText(String inFile, String outFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        String fileText;
        fileText = sb.toString();
        String res;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        res = fileText.replaceAll(pattern3, df.format(new Date()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
        bw.write(res);
        bw.close();
    }
}
