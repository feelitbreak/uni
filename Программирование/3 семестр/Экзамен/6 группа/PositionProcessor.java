import java.text.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class PositionProcessor {
    private Map<Integer, Position> positions;
    private List<PositionDate> positionDates;
    private Map<Integer, Recruiter> recruiters;
    private List<Bonus> bonuses;
    public PositionProcessor() throws IOException, ParseException {
        inputPositions();
        inputDates();
        inputRecruiters();
        inputBonuses();
    }
    private void inputPositions() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        positions = new LinkedHashMap<>();
        while(br.ready()) {
            String[] split = br.readLine().split(";");
            Integer id = Integer.parseInt(split[0]);
            Position pos = new Position(id, split[1], split[2], Priority.valueOf(split[3]));
            positions.put(id, pos);
        }
    }
    private void inputDates() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("input4.txt"));
        positionDates = new ArrayList<>();
        while(br.ready()) {
            PositionDate pd = new PositionDate(br.readLine());
            positionDates.add(pd);
        }
    }
    private void inputRecruiters() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
        recruiters = new LinkedHashMap<>();
        while(br.ready()) {
            String[] split = br.readLine().split(";");
            Recruiter rec = new Recruiter(split[1], split[2]);
            recruiters.put(Integer.parseInt(split[0]), rec);
        }
        br.close();
    }
    private void inputBonuses() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
        bonuses = new ArrayList<>();
        while(br.ready()) {
            Bonus bonus = new Bonus(br.readLine());
            bonuses.add(bonus);
        }
    }
    public void out1() throws IOException {
        List<Position> res = positions.values()
                .stream()
                .sorted(Comparator.comparing(Position::getPrior))
                .collect(Collectors.toList());
        FileWriter fw = new FileWriter("output1.txt");
        Iterator<Position> it = res.iterator();
        if(it.hasNext()) {
            fw.write(it.next().toString());
        }
        while(it.hasNext()) {
            fw.write(System.lineSeparator());
            fw.write(it.next().toString());
        }
        fw.close();
    }
    public void out2() throws IOException {
        List<PositionDate> res = positionDates.stream()
                .sorted((x, y) -> y.getId().compareTo(x.getId()))
                .filter((x) -> {
                    if(x.getCloseDate() != null) {
                        return false;
                    }
                    long diffTime = new Date().getTime() - x.getOpenDate().getTime();
                    return TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS) >= 90;
                })
                .collect(Collectors.toList());
        FileWriter fw = new FileWriter("output2.txt");
        Iterator<PositionDate> it = res.iterator();
        if(it.hasNext()) {
            fw.write(it.next().toString());
        }
        while(it.hasNext()) {
            fw.write(System.lineSeparator());
            fw.write(it.next().toString());
        }
        fw.close();
    }
    public void out3() throws IOException {
        StringBuilder sb = new StringBuilder();
        recruiters.entrySet()
                .stream()
                .filter(x -> positionDates.stream()
                        .anyMatch(y -> y.getRecId().equals(x.getKey()) && y.getCloseDate() == null))
                .sorted(Map.Entry.comparingByKey())
                .forEach(x -> {
                    sb.append(x.getValue());
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
        try {
            Scanner sc = new Scanner(new File("input5.txt"));
            Integer recId = sc.nextInt();
            sc.close();
            int sum = 0;
            List<PositionDate> res = positionDates.stream()
                    .filter(x -> x.getCloseDate() != null && x.getRecId().equals(recId))
                    .collect(Collectors.toList());
            for (PositionDate x : res) {
                Priority prior = positions.get(x.getId()).getPrior();
                long diffTime = x.getCloseDate().getTime() - x.getOpenDate().getTime();
                diffTime = TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
                for (Bonus y : bonuses) {
                    if (y.getPrior().equals(prior) && diffTime <= y.getDays()) {
                        sum += y.getBonus();
                    }
                }
            }
            FileWriter fw = new FileWriter("output4.txt");
            fw.write(String.valueOf(sum));
            fw.close();
        } catch (NoSuchElementException e) {
            FileWriter fw = new FileWriter("output4.txt");
            fw.write("0");
            fw.close();
        }
    }
}
