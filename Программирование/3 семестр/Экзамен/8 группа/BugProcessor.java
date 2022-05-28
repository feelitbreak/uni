import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

public class BugProcessor {
    private Map<Integer, Bug> bugs;
    private List<Tester> testers;
    private Map<Integer, Priority> priorities;
    private Map<Integer, Integer> info;
    private Integer testerId;
    public BugProcessor() throws IOException, ParseException {
        inputBugs();
        inputPriorities();
        inputTesters();
        processInfo();
    }
    private void inputBugs() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        bugs = new LinkedHashMap<>();
        while(br.ready()) {
            String[] split = br.readLine().split(";");
            Bug bug = new Bug(split[1], split[2], split[3], split[4]);
            bugs.put(Integer.parseInt(split[0]), bug);
        }
        br.close();
    }
    private void inputTesters() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
        testers = new ArrayList<>();
        while(br.ready()) {
            Tester tester = new Tester(br.readLine());
            testers.add(tester);
        }
        br.close();
    }
    private void processInfo() throws IOException {
        Scanner sc = new Scanner(new File("input3.txt"));
        sc.useDelimiter("[\\s;]+");
        info = new LinkedHashMap<>();
        while(sc.hasNext()) {
            Integer bugId = sc.nextInt();
            Integer testerId = sc.nextInt();
            info.put(bugId, testerId);
        }
        sc.close();
    }
    private void inputPriorities() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input4.txt"));
        sc.useDelimiter("[\\s;]+");
        priorities = new LinkedHashMap<>();
        for(int i = 1; sc.hasNext(); i++) {
            Integer a = sc.nextInt();
            String s = sc.next();
            priorities.put(a, new Priority(s, i));
        }
        sc.close();
    }
    public void out1() throws IOException {
        Scanner sc = new Scanner(new File("input5.txt"));
        String priorName = sc.nextLine();
        sc.close();
        List<Bug> res = bugs.values()
                .stream()
                .filter(x -> {
                    Priority prior = priorities.get(x.getPriority());
                    if (prior != null) {
                        return prior.getName().equals(priorName);
                    } else {
                        return false;
                    }
                })
                .collect(Collectors.toList());
        FileWriter fw = new FileWriter("output1.txt");
        Iterator<Bug> it = res.iterator();
        if(it.hasNext()) {
            fw.write(it.next().getTitle());
        }
        while(it.hasNext()) {
            fw.write(System.lineSeparator());
            fw.write(it.next().getTitle());
        }
        fw.close();
    }
    public void out2() throws IOException {
        StringBuilder sb = new StringBuilder();
        bugs.values()
                .stream()
                .filter(x -> priorities.get(x.getPriority()) != null)
                .sorted(Comparator.comparingInt(x -> priorities.get(x.getPriority()).getPlace()))
                .forEach(x -> {
                    sb.append(x.getTitle());
                    sb.append('\n');
                });
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        FileWriter fw = new FileWriter("output2.txt");
        fw.write(sb.toString());
        fw.close();
    }
    public void out3() throws IOException {
        Scanner sc = new Scanner(new File("input6.txt"));
        testerId = sc.nextInt();
        sc.close();
        StringBuilder sb = new StringBuilder();
        info.entrySet()
                .stream()
                .filter(x -> x.getValue().equals(testerId))
                .forEach(x -> {
                    sb.append(bugs.get(x.getKey()).getTitle());
                    sb.append('\n');
                });
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        FileWriter fw = new FileWriter("output3.txt");
        fw.write(sb.toString());
        fw.close();
    }
    public void out4() throws IOException {
        StringBuilder sb = new StringBuilder();
        info.entrySet()
                .stream()
                .filter(x -> x.getValue().equals(testerId))
                .sorted(Comparator.comparing(x -> bugs.get(x.getKey())))
                .forEach(x -> {
                    sb.append(bugs.get(x.getKey()).getTitle());
                    sb.append('\n');
                });
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        FileWriter fw = new FileWriter("output4.txt");
        fw.write(sb.toString());
        fw.close();
    }
}
